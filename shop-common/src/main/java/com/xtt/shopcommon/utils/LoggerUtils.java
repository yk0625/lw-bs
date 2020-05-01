package com.xtt.shopcommon.utils;

import com.alibaba.fastjson.JSON;
import com.xtt.shopcommon.bean.EnumDescribable;
import com.xtt.shopcommon.bean.JsonMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * Introduce
 * <p>Title: slf4j.Logger日志处理工具类</p>
 * <p>File：${File}</p>
 * <p>Description: 统一日志输出规范</p>
 * <p>Copyright: Copyright (c) 2019/4/19</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public class LoggerUtils
{
    private LoggerUtils()
    {
        super();
    }

    /**
     * 输出info日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param enumDescribable EnumDescribable
     */
    public static void logInfo(Logger logger, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isInfoEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.info(JSON.toJSONString(jsonMessage));
        }
    }

    /**
     * 输出info日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param ste             StackTraceElement
     * @param enumDescribable EnumDescribable
     */
    public static void logInfo(Logger logger, StackTraceElement ste, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isInfoEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.info(JSON.toJSONString(jsonMessage));
            if (null != ste) logger.info("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * 输出error日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param enumDescribable EnumDescribable
     */
    public static void logError(Logger logger, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isErrorEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.error(JSON.toJSONString(jsonMessage));
        }
    }

    /**
     * 输出error日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param ste             StackTraceElement
     * @param enumDescribable EnumDescribable
     */
    public static void logError(Logger logger, StackTraceElement ste, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isErrorEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.error(JSON.toJSONString(jsonMessage));
            if (null != ste) logger.error("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * 输出warn日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param enumDescribable EnumDescribable
     */
    public static void logWarn(Logger logger, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isWarnEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.warn(JSON.toJSONString(jsonMessage));
        }
    }

    /**
     * 输出warn日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param ste             StackTraceElement
     * @param enumDescribable EnumDescribable
     */
    public static void logWarn(Logger logger, StackTraceElement ste, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isWarnEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.warn(JSON.toJSONString(jsonMessage));
            if (null != ste) logger.warn("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * 输出debug日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param enumDescribable EnumDescribable
     */
    public static void logDebug(Logger logger, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isDebugEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.debug(JSON.toJSONString(jsonMessage));
        }
    }

    /**
     * 输出debug日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param ste             StackTraceElement
     * @param enumDescribable EnumDescribable
     */
    public static void logDebug(Logger logger, StackTraceElement ste, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isDebugEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.debug(JSON.toJSONString(jsonMessage));
            if (null != ste) logger.debug("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * 输出trace日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param enumDescribable EnumDescribable
     */
    public static void logTrace(Logger logger, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isTraceEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.trace(JSON.toJSONString(jsonMessage));
        }
    }

    /**
     * 输出trace日志，格式为JsonMessage
     *
     * @param logger          Logger
     * @param ste             StackTraceElement
     * @param enumDescribable EnumDescribable
     */
    public static void logTrace(Logger logger, StackTraceElement ste, EnumDescribable enumDescribable)
    {
        if (null != logger && logger.isTraceEnabled() && null != enumDescribable) {
            JsonMessage jsonMessage = new JsonMessage(enumDescribable);
            logger.trace(JSON.toJSONString(jsonMessage));
            if (null != ste) logger.trace("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logInfo
     *
     * @param logger Logger
     * @param string String
     */
    public static void logInfo(Logger logger, String string, StackTraceElement ste)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isInfoEnabled()) {
            logger.info(string);
            if (null != ste) logger.info("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logInfo
     *
     * @param logger Logger
     * @param string String
     */
    public static void logInfo(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isInfoEnabled()) {
            logger.info(string);
        }
    }

    /**
     * logError
     *
     * @param logger Logger
     * @param string String
     * @param ste    StackTraceElement
     */
    public static void logError(Logger logger, String string, StackTraceElement ste)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isErrorEnabled()) {
            logger.error(string);
            if (null != ste) logger.error("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logError
     *
     * @param logger Logger
     * @param string String
     */
    public static void logError(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isErrorEnabled()) {
            logger.error(string);
        }
    }

    /**
     * logWarn
     *
     * @param logger Logger
     * @param string String
     */
    public static void logWarn(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isWarnEnabled()) {
            logger.warn(string);
        }
    }

    /**
     * logWarn
     *
     * @param logger Logger
     * @param string String
     * @param ste    StackTraceElement
     */
    public static void logWarn(Logger logger, String string, StackTraceElement ste)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isWarnEnabled()) {
            logger.warn(string);
            if (null != ste) logger.warn("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logDebug
     *
     * @param logger Logger
     * @param string String
     */
    public static void logDebug(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isDebugEnabled()) {
            logger.debug(string);
        }
    }

    /**
     * logDebug
     *
     * @param logger Logger
     * @param string String
     * @param ste    StackTraceElement
     */
    public static void logDebug(Logger logger, String string, StackTraceElement ste)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isDebugEnabled()) {
            logger.debug(string);
            if (null != ste) logger.debug("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logTrace
     *
     * @param logger Logger
     * @param string String
     */
    public static void logTrace(Logger logger, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isTraceEnabled()) {
            logger.trace(string);
        }
    }

    /**
     * logTrace
     *
     * @param logger Logger
     * @param ste    StackTraceElement
     * @param string String
     */
    public static void logTrace(Logger logger, StackTraceElement ste, String string)
    {
        // 这里不能加判断StringUtils.isNotBlank(string)，否则string为null或空时不输出
        if (null != logger && logger.isTraceEnabled()) {
            logger.trace(string);
            if (null != ste) logger.trace("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logInfo
     *
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logInfo(Logger logger, String string, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isInfoEnabled() && StringUtils.isNotBlank(string)) {
            logger.info(string, object);
        }
    }

    /**
     * logInfo
     *
     * @param logger Logger
     * @param string String
     * @param ste    StackTraceElement
     * @param object Object
     */
    public static void logInfo(Logger logger, String string, StackTraceElement ste, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isInfoEnabled() && StringUtils.isNotBlank(string)) {
            logger.info(string, object);
            if (null != ste) logger.info("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logError
     *
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logError(Logger logger, String string, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isErrorEnabled() && StringUtils.isNotBlank(string)) {
            logger.error(string, object);
        }
    }

    /**
     * logError
     *
     * @param logger Logger
     * @param string String
     * @param ste    StackTraceElement
     * @param object Object
     */
    public static void logError(Logger logger, String string, StackTraceElement ste, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isErrorEnabled() && StringUtils.isNotBlank(string)) {
            logger.error(string, object);
            if (null != ste) logger.error("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logWarn
     *
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logWarn(Logger logger, String string, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isWarnEnabled() && StringUtils.isNotBlank(string)) {
            logger.warn(string, object);
        }
    }

    /**
     * logWarn并输出文件名称所在行数
     *
     * @param logger Logger
     * @param string String
     * @param ste    StackTraceElement
     * @param object Object
     */
    public static void logWarn(Logger logger, String string, StackTraceElement ste, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isWarnEnabled() && StringUtils.isNotBlank(string)) {
            logger.warn(string, object);
            if (null != ste) logger.warn("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logDebug并输出文件名称所在行数
     *
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logDebug(Logger logger, String string, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isDebugEnabled() && StringUtils.isNotBlank(string)) {
            logger.debug(string, object);
        }
    }

    /**
     * logDebug并输出文件名称所在行数
     *
     * @param logger Logger
     * @param string String
     * @param ste    StackTraceElement
     * @param object Object
     */
    public static void logDebug(Logger logger, String string, StackTraceElement ste, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isDebugEnabled() && StringUtils.isNotBlank(string)) {
            logger.debug(string, object);
            if (null != ste) logger.debug("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }

    /**
     * logTrace
     *
     * @param logger Logger
     * @param string String
     * @param object Object
     */
    public static void logTrace(Logger logger, String string, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isTraceEnabled() && StringUtils.isNotBlank(string)) {
            logger.trace(string, object);
        }
    }

    /**
     * logTrace并输出文件名称，所在行数
     *
     * @param logger Logger
     * @param string String
     * @param ste    StackTraceElement
     * @param object Object
     */
    public static void logTrace(Logger logger, String string, StackTraceElement ste, Object... object)
    {
        // object为占位符格式的日志输出
        if (null != logger && logger.isTraceEnabled() && StringUtils.isNotBlank(string)) {
            logger.trace(string, object);
            if (null != ste) logger.trace("文件名称：" + ste.getFileName() + "，所在行数：" + ste.getLineNumber());
        }
    }
}
