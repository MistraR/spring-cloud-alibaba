package com.sample.mall.order.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sample.mall.common.base.BaseResponse;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.dto.OrderDTO;
import com.sample.mall.order.model.OrderDO;
import com.sample.mall.order.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单服务Controller
 */
@RestController
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * 创建订单接口
     *
     * @param orderDTO
     * @return
     */
    @PostMapping("/order")
    BaseResponse createOrder(@RequestBody OrderDTO orderDTO) {
        //TODO 对 DTO 进行前置校验
        orderService.createOrder(orderDTO);
        return BaseResponse.success();
    }

    /**
     * 获取订单
     *
     * @return
     */
    @GetMapping("/order/{orderNo}")
    BaseResponse<OrderDTO> getOrder(@PathVariable(value = "orderNo") Long orderNo){
        try (Entry entry = SphU.entry("mall/order")) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderNo(orderNo);
            return BaseResponse.success(orderDTO);
        } catch (BlockException e) {
            return new BaseResponse<>(ResponseEnum.SYSTEM_BUSY);
        }
    }

    @SentinelResource(value = "mall/order", fallback = "fallback")
    @GetMapping("/order2/{orderNo}")
    BaseResponse<OrderDTO> getOrder2(@PathVariable(value = "orderNo") Long orderNo){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderNo(orderNo);
            return BaseResponse.success(orderDTO);
    }

    public BaseResponse fallback(Long orderNo, Throwable e) {
        return new BaseResponse<>(ResponseEnum.SYSTEM_BUSY);
    }

}
