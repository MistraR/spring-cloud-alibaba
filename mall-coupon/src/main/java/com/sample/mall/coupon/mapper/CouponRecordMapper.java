package com.sample.mall.coupon.mapper;

import com.sample.mall.coupon.model.CouponRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券记录Mapper
 */
@Mapper
public interface CouponRecordMapper {

    /**
     * 新增优惠券记录，用于用户领取优惠券场景
     *
     * @param couponRecordDO
     * @return
     */
    int insertCouponRecord(CouponRecordDO couponRecordDO);

    /**
     * 根据用户ID和优惠券ID查询优惠券
     *
     * @param couponRecordDO
     * @return
     */
    CouponRecordDO selectCouponRecord(CouponRecordDO couponRecordDO);

    /**
     * 查询用户名下的优惠券
     *
     * @param userId
     * @return
     */
    List<CouponRecordDO> selectCouponRecordByUserId(Long userId);

    /**
     * 更新优惠券状态，比如从【未使用】更新为【已使用】
     *
     * @param couponRecordDO
     * @return
     */
    int updateCouponRecordStatus(CouponRecordDO couponRecordDO);
}
