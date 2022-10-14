package com.sample.mall.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.exception.BusinessException;
import org.springframework.util.StringUtils;

/**
 * JSON转换工具类
 */
public class JSONUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    /**
     * 将JSON转为对象
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        if(StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try{
            return mapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new BusinessException(ResponseEnum.TRANSFORM_EXCEPTION);
        }
    }

    /**
     * 将对象转为JSON
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toJSONString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new BusinessException(ResponseEnum.TRANSFORM_EXCEPTION);
        }
    }
}
