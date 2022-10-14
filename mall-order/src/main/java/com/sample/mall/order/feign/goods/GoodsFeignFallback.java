package com.sample.mall.order.feign.goods;

import com.sample.mall.common.base.BaseResponse;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.dto.OrderItemDTO;
import com.sample.mall.common.exception.BusinessException;

import java.util.List;

public class GoodsFeignFallback implements GoodsFeign {

    @Override
    public BaseResponse checkAndDecreaseStock(List<OrderItemDTO> orderItemDTOList) {
        throw new BusinessException(ResponseEnum.FEIGN_CALL_EXCEPTION);
    }
}
