package com.sample.mall.coupon.service;


import com.sample.mall.common.dto.CouponRecordDTO;
import com.sample.mall.coupon.model.CouponRecordDO;

/**
 * 优惠券记录Service
 */
public interface ICouponRecordService {

    /**
     * 领取优惠券
     *
     * @param couponRecordDTO
     * @return
     */
    boolean receiveCoupon(CouponRecordDTO couponRecordDTO);

    /**
     * 使用优惠券
     *
     * @param couponRecordDTO
     * @return
     */
    boolean useCoupon(CouponRecordDTO couponRecordDTO);

    void checkCouponRecordStatus(CouponRecordDO couponRecordDO);

}
