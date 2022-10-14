package com.sample.mall.common.dto;

import com.sample.mall.common.base.BaseBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单明细信息DTO
 */
public class OrderItemDTO extends BaseBean {

    /** 订单明细ID **/
    private Long id;

    /** 订单号 **/
    private Long orderNo;

    /** 商品ID **/
    private Long goodsId;

    /** 数量 **/
    private Long number;

    /** 金额 **/
    private BigDecimal amount;

    public OrderItemDTO() {
    }

    private OrderItemDTO(Builder builder) {
        this.id = builder.id;
        this.orderNo = builder.orderNo;
        this.goodsId = builder.goodsId;
        this.number = builder.number;
        this.amount = builder.amount;
    }

    public static class Builder {
        private Long id;
        private Long orderNo;
        private Long goodsId;
        private Long number;
        private BigDecimal amount;

        public Builder() {
        }

        public OrderItemDTO.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderItemDTO.Builder orderNo(Long orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        public OrderItemDTO.Builder goodsId(Long goodsId) {
            this.goodsId = goodsId;
            return this;
        }

        public OrderItemDTO.Builder number(Long number) {
            this.number = number;
            return this;
        }

        public OrderItemDTO.Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public OrderItemDTO build() {
            return new OrderItemDTO(this);
        }
    }

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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
