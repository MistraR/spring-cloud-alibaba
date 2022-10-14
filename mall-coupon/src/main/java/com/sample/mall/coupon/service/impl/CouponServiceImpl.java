package com.sample.mall.coupon.service.impl;


import com.sample.mall.common.dto.CouponDTO;
import com.sample.mall.common.util.Assert;
import com.sample.mall.common.util.ObjectTransformer;
import com.sample.mall.coupon.mapper.CouponMapper;
import com.sample.mall.coupon.model.CouponDO;
import com.sample.mall.coupon.service.ICouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CouponServiceImpl implements ICouponService {

    @Resource
    CouponMapper couponMapper;

    @Override
    public boolean createCoupon(CouponDTO couponDTO) {
        CouponDO couponDO = ObjectTransformer.transform(couponDTO, CouponDO.class);
        int result = couponMapper.insertCoupon(couponDO);
        return Assert.singleRowAffected(result);
    }

}
