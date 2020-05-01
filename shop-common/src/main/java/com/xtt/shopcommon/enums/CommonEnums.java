package com.xtt.shopcommon.enums;

import com.xtt.shopcommon.bean.EnumDescribable;

/**
 * Introduce
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: 公共错误枚举类</p>
 * <p>Copyright: Copyright (c) 2019/4/18</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public enum CommonEnums implements EnumDescribable
{
    SUCCESS(200, "操作成功"),//操作成功
    FAIL(1000, "操作失败"), //操作失败
    PARAMS_VALID_ERR(1001, "参数验证错误"), // 参数验证错误
    USER_NOT_LOGIN(1002, "用户未登录"), // 用户未登录
    ERROR_LOGIN_ACCOUNT(1003, "用户不存在"), // 用户不存在
    ERROR_LOGIN_PASSWORD(1004, "密码错误"), // 密码错误
    ERROR_LOGIN_ACCOUNTPASSWORD(1005, "用户名或密码错误"), // 用户名或密码错误
    ERROR_ACCOUNT_EXIST(1006, "账户已存在"), // 账户已存在
    ERROR_GOODS_EXIST(1007, "商品不存在存在"), // 商品不存在存在
    ERROR_NUM_EXIST(1008, "商品数量不能为空"), // 商品数量不能为空
    ERROR_ORDER_EXIST(1009, "订单不存在"), // 订单不存在
    VALIDATE_PHONE_FORMAT(1010, "手机号格式不对"), // 手机号格式不对
    ;

    private Integer code;

    private String message;

    CommonEnums(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode()
    {
        return code;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
