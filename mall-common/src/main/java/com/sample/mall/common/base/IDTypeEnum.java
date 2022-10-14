package com.sample.mall.common.base;

/**
 * ID类型枚举类型
 * code - 编码
 * redisCounter - 计数器名称，Redis Key
 */
public enum IDTypeEnum {

    USER  (1, "USER-ID"),
    GOODS (2, "GOODS-ID"),
    ORDER (3, "ORDER-ID"),
    COUPON(4, "COUPON-ID");

    IDTypeEnum(int code, String redisCounter) {
        this.code = code;
        this.redisCounter = redisCounter;
    }

    private int code;

    private String redisCounter;

    public int getCode() {
        return code;
    }

    public String getRedisCounter() {
        return redisCounter;
    }
}
