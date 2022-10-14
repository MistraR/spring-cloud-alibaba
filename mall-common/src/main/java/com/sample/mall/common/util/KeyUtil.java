package com.sample.mall.common.util;

import java.util.UUID;


public class KeyUtil {

    /**
     * 生成唯一的主键
     */
    public static synchronized String genUniqueKey() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }


    public static Integer getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return orderId;
    }
}
