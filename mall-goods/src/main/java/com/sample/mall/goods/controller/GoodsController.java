package com.sample.mall.goods.controller;

import com.sample.mall.common.base.BaseResponse;
import com.sample.mall.common.dto.GoodsDTO;
import com.sample.mall.common.dto.OrderItemDTO;
import com.sample.mall.goods.service.IGoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class GoodsController {

    @Resource
    private IGoodsService goodsService;

    @PostMapping("/goods")
    BaseResponse addGoods(@RequestBody GoodsDTO goodsDTO) {
        goodsService.addGoods(goodsDTO);
        return BaseResponse.success();
    }

    @GetMapping("/goods/{id}")
    BaseResponse<GoodsDTO> getGoods(@PathVariable("id") Long id) {
        GoodsDTO goods = goodsService.getGoods(id);
        return BaseResponse.success(goods);
    }

    @GetMapping("/goods/v2/{id}")
    BaseResponse<GoodsDTO> getGoodsNameAndPrice(@PathVariable("id") Long id) {
        GoodsDTO goods = goodsService.getGoodsNameAndPrice(id);
        return BaseResponse.success(goods);
    }

    @PutMapping("/goods/stock")
    BaseResponse checkAndDecreaseStock(@RequestBody List<OrderItemDTO> orderItemDTOList) {
        goodsService.decreaseStock(orderItemDTOList);
        return BaseResponse.success();
    }

}
