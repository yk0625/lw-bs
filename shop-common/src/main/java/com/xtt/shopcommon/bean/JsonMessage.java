package com.xtt.shopcommon.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * JSON返回对象
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/19</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
@ApiModel(value = "JSON返回对象")
public class JsonMessage implements Serializable
{
    private static final long serialVersionUID = 4127484843498503002L;

    public JsonMessage()
    {
    }

    public JsonMessage(EnumDescribable enumDescribable)
    {
        this.code = enumDescribable.getCode();
        this.message = enumDescribable.getMessage();
    }

    public JsonMessage(EnumDescribable enumDescribable, Object object)
    {
        this.object = object;
        this.code = enumDescribable.getCode();
        this.message = enumDescribable.getMessage();
    }

    public JsonMessage(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    @ApiModelProperty(value = "编码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "对象")
    private Object object;

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }
}
