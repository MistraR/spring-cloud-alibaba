package com.sample.mall.common.dto;


import com.sample.mall.common.base.BaseBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息DTO
 */
public class GoodsDTO extends BaseBean {

    /** 商品ID **/
    private Long id;

    /** 商品名称 **/
    private String name;

    /** 商品描述 **/
    private String description;

    /** 商品类型 **/
    private Integer type;

    /** 商品价格 **/
    private BigDecimal price;

    /** 商品库存 **/
    private Long stock;

    public GoodsDTO() {
    }

    private GoodsDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.type = builder.type;
        this.price = builder.price;
        this.stock = builder.stock;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String description;
        private Integer type;
        private BigDecimal price;
        private Long stock;

        public Builder() {
        }

        public GoodsDTO.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public GoodsDTO.Builder name(String name) {
            this.name = name;
            return this;
        }

        public GoodsDTO.Builder description(String description) {
            this.description = description;
            return this;
        }

        public GoodsDTO.Builder type(Integer type) {
            this.type = type;
            return this;
        }

        public GoodsDTO.Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public GoodsDTO.Builder stock(Long stock) {
            this.stock = stock;
            return this;
        }

        public GoodsDTO build() {
            return new GoodsDTO(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
