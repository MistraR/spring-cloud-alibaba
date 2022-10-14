package com.sample.mall.order.model;


import com.sample.mall.common.base.BaseBean;

import java.math.BigDecimal;
import java.util.Date;


public class OrderDO extends BaseBean {

    private Long id;

    private Long orderNo;

    private BigDecimal amount;

    private Integer status;

    private Long userId;

    private Long couponRecordId;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCouponRecordId() {
        return couponRecordId;
    }

    public void setCouponRecordId(Long couponRecordId) {
        this.couponRecordId = couponRecordId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
