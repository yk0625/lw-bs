package com.xtt.shopcommon.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlets 辅助工具
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/19</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public class ServletsUtils
{
    /**
     * 获取当前请求对象
     *
     * @return
     */
    public static HttpServletRequest getRequest()
    {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前请求对象
     *
     * @return
     */
    public static HttpServletResponse getResponse()
    {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        } catch (Exception e) {
            return null;
        }
    }
}
