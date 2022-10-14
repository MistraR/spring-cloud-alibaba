package com.sample.mall.common.dto;

import com.sample.mall.common.base.BaseBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单信息DTO
 */
public class OrderDTO extends BaseBean {

    /** 订单号 **/
    private Long orderNo;

    /** 订单金额 **/
    private BigDecimal amount;

    /** 订单状态 0-初始化 1-提交 2-完成 3-撤销 **/
    private Integer status;

    /** 用户ID **/
    private Long userId;

    /** 优惠券ID **/
    private Long couponRecordId;

    /** 订单明细列表 **/
    private List<OrderItemDTO> orderItemDTOList;

    public OrderDTO() { }

    private OrderDTO(Builder builder) {
        this.orderNo = builder.orderNo;
        this.amount = builder.amount;
        this.status = builder.status;
        this.userId = builder.userId;
        this.couponRecordId = builder.couponRecordId;
        this.orderItemDTOList = builder.orderItemDTOList;
    }

    public static class Builder {
        private Long orderNo;
        private BigDecimal amount;
        private Integer status;
        private Long userId;
        private Long couponRecordId;
        private List<OrderItemDTO> orderItemDTOList;

        public OrderDTO.Builder orderNo(Long orderNo){
            this.orderNo = orderNo;
            return this;
        }

        public OrderDTO.Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public OrderDTO.Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public OrderDTO.Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public OrderDTO.Builder couponRecordId(Long couponRecordId){
            this.couponRecordId = couponRecordId;
            return this;
        }

        public OrderDTO.Builder orderItemDTOList(List<OrderItemDTO> orderItemDTOList) {
            this.orderItemDTOList = orderItemDTOList;
            return this;
        }

        public OrderDTO build() {
            return new OrderDTO(this);
        }
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

    public List<OrderItemDTO> getOrderItemDTOList() {
        return orderItemDTOList;
    }

    public void setOrderItemDTOList(List<OrderItemDTO> orderItemDTOList) {
        this.orderItemDTOList = orderItemDTOList;
    }
}
