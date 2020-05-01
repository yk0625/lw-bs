package com.xtt.shopcommon.bean;

import com.xtt.shopcommon.enums.CommonEnums;
import com.xtt.shopcommon.exception.BusinessException;
import com.xtt.shopcommon.utils.LoggerUtils;
import com.xtt.shopcommon.utils.ServletsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Introduce
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/19</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
@Slf4j
public abstract class GenericController
{

    protected static final Logger logger = LoggerFactory.getLogger(GenericController.class);

    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView handleException(Exception ex)
    {
        ModelAndView mav = new ModelAndView();
        HttpServletResponse response = ServletsUtils.getResponse();
        if (StringUtils.isNotBlank(response.getHeader("csrf"))) {// 将CSRF放到返回对象中
            mav.addObject("csrf", response.getHeader("csrf"));
        }
        if (ex instanceof BusinessException) { // 自定义业务异常
            BusinessException bex = (BusinessException) ex;
            JsonMessage json = getJsonMessage(bex.getErrorCode());
            mav.addObject("code", json.getCode());
            mav.addObject("message", json.getMessage());
            mav.addObject("object", bex.getObject());
            LoggerUtils.logError(logger, "handleException BusinessException:{}", ex.getLocalizedMessage());
        } else {
            JsonMessage json = getJsonMessage(CommonEnums.FAIL);
            mav.addObject("code", json.getCode());
            mav.addObject("message", json.getMessage());
            mav.addObject("object", "Business Exception");
            // 将运行异常记录到日志文件
            LoggerUtils.logError(logger, "handleException RuntimeException:{}", ex.getLocalizedMessage());
            ex.printStackTrace();
            StringWriter errorsWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(errorsWriter));
            logger.error("handleException StackTrace:" + errorsWriter.toString());
        }
        return mav;
    }

    /**
     * 服务端参数有效性验证
     *
     * @param jsonMessage 错误信息（不能为null）
     * @param object      验证的实体对象
     * @param groups      验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 jsonMessage rows 中
     */
    protected boolean beanValidator(JsonMessage jsonMessage, Object object, Class<?>... groups)
    {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            jsonMessage.setCode(CommonEnums.FAIL.getCode());
            jsonMessage.setMessage(CommonEnums.FAIL.getMessage());
            jsonMessage.setObject(list);
            return false;
        }
        return true;
    }

    /**
     * 接口处理结果反馈 : ：API接口返回数据或交易处理结果的JSON处理
     *
     * @param describable 异常代码描述
     * @param object      单结果返回对象
     * @return
     * @author xtt
     */
    protected JsonMessage getJsonMessage(EnumDescribable describable, Object object)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(describable.getCode());
        jsonMessage.setMessage(describable.getMessage());
        jsonMessage.setObject(object);
        return jsonMessage;
    }

    /**
     * 接口处理结果反馈 : ：API接口返回数据或交易处理结果的JSON处理
     *
     * @param describable 异常代码描述
     * @return JsonMessage JsonMessage
     * @author xtt
     */
    public JsonMessage getJsonMessage(EnumDescribable describable)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(describable.getCode());
        jsonMessage.setMessage(describable.getMessage());
        return jsonMessage;
    }

    /**
     * 接口处理结果反馈 : ：API接口返回数据或交易处理结果的JSON处理
     *
     * @param message 异常代码描述
     * @return JsonMessage JsonMessage
     * @author xtt
     */
    public JsonMessage getJsonMessage(Integer code, String message)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(code);
        jsonMessage.setMessage(message);
        return jsonMessage;
    }
}
