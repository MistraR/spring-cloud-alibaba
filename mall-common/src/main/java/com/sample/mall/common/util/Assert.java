package com.sample.mall.common.util;

import com.sample.mall.common.base.BaseResponse;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.exception.BusinessException;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Hexagon
 */
public class Assert<T> {

    /**
     * 数据实体不为空
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> boolean notNull(T data) {
        if (data == null) {
            throw new BusinessException(ResponseEnum.ENTITY_NOT_FOUND);
        }
        return true;
    }

    /**
     * 只新增或更新一行数据
     *
     * @param result
     * @return
     */
    public static boolean singleRowAffected(int result) {
        if (result == 0) {
            throw new BusinessException(ResponseEnum.NO_ROWS_AFFECTED);
        } else if (result > 1) {
            throw new BusinessException(ResponseEnum.TOO_MANY_ROWS_AFFECTED);
        }
        return true;
    }

    /**
     * 数据操作不全
     *
     * @param result
     * @param list
     * @return
     */
    public static boolean totalRowsAffected(int result, List list) {
        if (CollectionUtils.isEmpty(list) || list.size() != result) {
            throw new BusinessException(ResponseEnum.NOT_TOTAL_ROWS_AFFECTED);
        }
        return true;
    }

    /**
     * 判断远程调用是否成功 <br/>
     * 如果不成功，需要把响应消息传递出去
     *
     * @param response
     * @return
     */
    public static void successResponse(BaseResponse response) {
        if(! ResponseEnum.SUCCESS.getCode().equals(response.getCode())) {
            throw new BusinessException(response.getCode(), response.getMessage());
        }
    }
}
