package com.sample.mall.coupon.service;

import com.sample.mall.common.dto.CouponRecordDTO;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 描述
 *
 * @author mistra@future.com
 * @date 2022/10/16
 */
@LocalTCC
public interface ICouponRecordTCCService {

    /**
     * 准备阶段
     * commitMethod 二阶段提交方法
     * rollbackMethod 二阶段回滚方法
     * BusinessActionContextParameter 把参数传递到二阶段方法
     *
     * @param context
     * @return
     */
    @TwoPhaseBusinessAction(name = "freezeCoupon", commitMethod = "deductCoupon", rollbackMethod = "unfreezeCoupon")
    boolean freezeCoupon(BusinessActionContext context, @BusinessActionContextParameter(paramName = "couponRecordDTO") CouponRecordDTO couponRecordDTO);

    /**
     * 提交方法
     *
     * @param context
     * @return
     */
    boolean deductCoupon(BusinessActionContext context);

    /**
     * 回滚方法
     *
     * @param context
     * @return
     */
    boolean unfreezeCoupon(BusinessActionContext context);
}
