package com.sample.mall.goods.exception;

import com.sample.mall.common.base.BaseResponse;
import com.sample.mall.common.base.ResponseEnum;
import com.sample.mall.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hexagon
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse exceptionHandler(HttpServletRequest request, Exception exception) {

        if (exception instanceof BusinessException) {
            log.info("BusinessException:", exception);

            BusinessException e = ((BusinessException) exception);
            ResponseEnum responseEnum = e.getResponseEnum();
            if (responseEnum == null) {
                return new BaseResponse(e.getCode(), e.getMessage());
            } else {
                return new BaseResponse(responseEnum);
            }
        } else {
            log.error("系统异常", exception);
            return BaseResponse.error();
        }
    }
}
