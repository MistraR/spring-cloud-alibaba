package com.sample.mall.common.util;

import java.util.ArrayList;
import java.util.List;

import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;

public class ObjectTransformer {

    public static <T> T transform(Object source, Class<T> t) {
        return transform(source, t, null);
    }

    public static <T> T transform(Object source, Class<T> t, ObjectTransformAfter<T> after) {
        T target;
        try {
            target = t.newInstance();
            BeanUtils.copyProperties(source, target);
            if (after != null) {
                after.after(source, target);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new BusinessException(ResponseEnum.TRANSFORM_EXCEPTION);
        }
        return target;
    }

    public static <T> List<T> transform(List<?> list, Class<T> t) {
        if (null == list) {
            return new ArrayList<>();
        }
        List<T> resultList = new ArrayList<>(list.size());
        for (Object element : list) {
            resultList.add(transform(element, t));
        }
        return resultList;
    }

    public static <T> List<T> transform(List<?> list, Class<T> t, ObjectTransformAfter<T> after) {
        if (null == list) {
            return new ArrayList<>();
        }
        List<T> resultList = new ArrayList<>(list.size());
        for (Object element : list) {
            resultList.add(transform(element, t, after));
        }
        return resultList;
    }

}