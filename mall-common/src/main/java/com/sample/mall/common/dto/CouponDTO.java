package com.sample.mall.common.dto;


import com.sample.mall.common.base.BaseBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券信息DTO
 */
public class CouponDTO extends BaseBean {

    /** 优惠券ID **/
    private Long id;

    /** 优惠券标题 **/
    private String title;

    /** 满减金额 **/
    private BigDecimal withAmount;

    /** 优惠金额 **/
    private BigDecimal usedAmount;

    /** 发放数量 **/
    private Long quota;

    /** 已领取数量 **/
    private Long takeCount;

    /** 已使用数量 **/
    private Long usedCount;

    /** 状态 1-生效 2-失效 **/
    private Integer status;

    public CouponDTO() {
    }

    private CouponDTO(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.withAmount = builder.withAmount;
        this.usedAmount = builder.usedAmount;
        this.quota = builder.quota;
        this.takeCount = builder.takeCount;
        this.usedCount = builder.usedCount;
        this.status = builder.status;
    }

    public static class Builder {
        private Long id;
        private String title;
        private BigDecimal withAmount;
        private BigDecimal usedAmount;
        private Long quota;
        private Long takeCount;
        private Long usedCount;
        private Integer status;

        public Builder() {
        }

        public CouponDTO.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public CouponDTO.Builder title(String title) {
            this.title = title;
            return this;
        }

        public CouponDTO.Builder withAmount(BigDecimal withAmount) {
            this.withAmount = withAmount;
            return this;
        }

        public CouponDTO.Builder usedAmount(BigDecimal usedAmount) {
            this.usedAmount = usedAmount;
            return this;
        }

        public CouponDTO.Builder quota(Long quota) {
            this.quota = quota;
            return this;
        }

        public CouponDTO.Builder takeCount(Long takeCount) {
            this.takeCount = takeCount;
            return this;
        }

        public CouponDTO.Builder usedCount(Long usedCount) {
            this.usedCount = usedCount;
            return this;
        }

        public CouponDTO.Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public CouponDTO build() {
            return new CouponDTO(this);
        }
    }

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
}
