package com.sample.mall.order.feign.goods;


import com.sample.mall.common.base.BaseResponse;
import com.sample.mall.common.dto.OrderItemDTO;
import com.sample.mall.order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
    name = "mall-goods",
    fallback = GoodsFeignFallback.class,
    configuration = FeignConfig.class
)
public interface GoodsFeign {

    @PutMapping("/goods/stock")
    BaseResponse checkAndDecreaseStock(@RequestBody List<OrderItemDTO> orderItemDTOList);

}
