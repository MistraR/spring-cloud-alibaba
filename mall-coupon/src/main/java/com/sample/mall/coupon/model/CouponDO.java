package com.sample.mall.coupon.model;


import com.sample.mall.common.base.BaseBean;

import java.math.BigDecimal;
import java.util.Date;


public class CouponDO extends BaseBean {

    private Long id;

    private String title;

    private BigDecimal withAmount;

    private BigDecimal usedAmount;

    private Long quota;

    private Long takeCount;

    private Long usedCount;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getWithAmount() {
        return withAmount;
    }

    public void setWithAmount(BigDecimal withAmount) {
        this.withAmount = withAmount;
    }

    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public Long getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Long takeCount) {
        this.takeCount = takeCount;
    }

    public Long getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Long usedCount) {
        this.usedCount = usedCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
