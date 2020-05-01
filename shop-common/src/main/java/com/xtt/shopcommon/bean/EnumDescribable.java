package com.xtt.shopcommon.bean;

import java.io.Serializable;

/**
 * 错误码描述接口
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/18</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public interface EnumDescribable extends Serializable
{

    /**
     * 获取错误代码
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取错误信息
     *
     * @return
     */
    String getMessage();
}
