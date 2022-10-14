package com.sample.mall.common.base;

/**
 * @author Hexagon
 */
public enum ResponseEnum {

    /** 基础返回码 **/
    SUCCESS("0000", "交易成功"),

    /** 数据库操作异常 **/
    ENTITY_NOT_FOUND("1000", "数据不存在"),
    NO_ROWS_AFFECTED("1000", "数据未更新成功"),
    TOO_MANY_ROWS_AFFECTED("1001","更新到其他数据"),
    NOT_TOTAL_ROWS_AFFECTED("1002","数据查询或者更新不全"),

    /** 用户服务 2xxx **/

    /** 商品服务 3xxx **/
    GOODS_STOCK_NOT_ENOUGH("3001","商品库存不足"),

    /** 订单服务 4xxx **/

    /** 优惠券服务 5xxx **/
    COUPON_NOT_ENOUGH("5001", "优惠券已被领取完"),
    COUPON_USED("5002", "优惠券已被使用"),
    COUPON_EXPIRED("5003", "优惠券已过期"),

    SYSTEM_BUSY("8888","系统繁忙"),

    /** 程序异常 **/
    FEIGN_CALL_EXCEPTION("9997","远程调用失败"),
    TRANSFORM_EXCEPTION("9998", "对象转换异常"),
    ERROR("9999", "系统异常");

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /** 返回码 **/
    private String code;

    /** 返回消息 **/
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
