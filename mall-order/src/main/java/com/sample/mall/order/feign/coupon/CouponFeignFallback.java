package com.sample.mall.order.feign.coupon;

import com.sample.mall.common.base.BaseResponse;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.dto.CouponRecordDTO;
import com.sample.mall.common.exception.BusinessException;

public class CouponFeignFallback implements CouponFeign {

    @Override
    public BaseResponse receiveCoupon(CouponRecordDTO couponRecordDTO) {
        throw new BusinessException(ResponseEnum.FEIGN_CALL_EXCEPTION);
    }

    @Override
    public BaseResponse useCoupon(CouponRecordDTO couponRecordDTO) {
        throw new BusinessException(ResponseEnum.FEIGN_CALL_EXCEPTION);
    }
}
