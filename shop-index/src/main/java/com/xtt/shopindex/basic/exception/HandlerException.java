package com.xtt.shopindex.basic.exception;

import com.xtt.shopcommon.bean.JsonMessage;
import com.xtt.shopcommon.enums.CommonEnums;
import com.xtt.shopcommon.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : xtt
 * @version : 1.0
 * @program : com.blocain.bitms.basic.interceptor
 * @discription : 异常处理类
 * @create : 2018-07-27-09
 **/
@ControllerAdvice
public class HandlerException
{

    private static Log logger = LogFactory.getLog(HandlerException.class);

    @ExceptionHandler(BusinessException.class)
    public @ResponseBody
    JsonMessage handlerBusinessException(BusinessException businessException)
    {
        JsonMessage jsonMessage = new JsonMessage(CommonEnums.SUCCESS);
        if (businessException.getErrorCode() == null) {
            jsonMessage.setCode(CommonEnums.FAIL.getCode());
            jsonMessage.setMessage(businessException.getMessage());
            return jsonMessage;
        }
        jsonMessage.setCode(businessException.getErrorCode().getCode());
        jsonMessage.setMessage(businessException.getErrorCode().getMessage());
        jsonMessage.setObject(businessException.getObject());
        return jsonMessage;
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    JsonMessage handlerBusinessException(Exception e)
    {
        JsonMessage jsonMessage = new JsonMessage(CommonEnums.SUCCESS);
        jsonMessage.setCode(CommonEnums.FAIL.getCode());
        jsonMessage.setMessage(CommonEnums.FAIL.getMessage());
        jsonMessage.setObject("Business Exception");
        logger.info(e.getLocalizedMessage());
        return jsonMessage;
    }

}
