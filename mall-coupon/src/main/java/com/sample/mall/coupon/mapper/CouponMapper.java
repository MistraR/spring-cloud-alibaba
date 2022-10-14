package com.sample.mall.coupon.mapper;

import com.sample.mall.coupon.model.CouponDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 优惠券Mapper
 */
@Mapper
public interface CouponMapper {

    /**
     * 新增优惠券
     *
     * @param couponDO
     * @return
     */
    int insertCoupon(CouponDO couponDO);

    /**
     * 查找优惠券
     *
     * @param id
     * @return
     */
    CouponDO selectCouponById(Long id);

    /**
     * 更新优惠券已领取数量
     *
     * @param couponDO
     * @return
     */
    int updateCouponTakeCount(CouponDO couponDO);

    /**
     * 更新优惠券已使用数量
     * @param couponDO
     * @return
     */
    int updateCouponUsedCount(CouponDO couponDO);
}
