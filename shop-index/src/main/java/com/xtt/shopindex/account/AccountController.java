package com.xtt.shopindex.account;

import com.xtt.shopcommon.bean.GenericController;
import com.xtt.shopcommon.bean.JsonMessage;
import com.xtt.shopcommon.enums.CommonEnums;
import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.account.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Introduce
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/18</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
@Controller
@RequestMapping(value = "account")
public class AccountController extends GenericController
{

    @Autowired(required = false)
    private AccountService accountService;

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/accountInfo")
    @ResponseBody
    public JsonMessage getAccountInfo(HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null) return getJsonMessage(CommonEnums.USER_NOT_LOGIN);

        jsonMessage.setObject(account.getAccountName());
        return jsonMessage;
    }

    /**
     * 用户注册
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public JsonMessage register(Account account, HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if (!beanValidator(jsonMessage, account)) return jsonMessage;
        // 校验手机号格式
        String telRegex = "[1][3578]\\d{9}";
        if(!account.getPhone().matches(telRegex))
        {
            return getJsonMessage(CommonEnums.VALIDATE_PHONE_FORMAT);
        }
        Account tempAccount = accountService.findByName(account.getPhone());
        if (tempAccount != null) return getJsonMessage(CommonEnums.ERROR_ACCOUNT_EXIST);
        account.setState("normal");
        if (StringUtils.isBlank(account.getAccountName())) {
            account.setAccountName(account.getPhone());
        }
        account.setRole("consumer");
        Integer accountId = Integer.valueOf(String.valueOf(System.currentTimeMillis()).substring(4));
        account.setId(accountId);
        accountService.addAccount(account);
        jsonMessage.setObject(account.getAccountName());
        // 讲用户信息存放到session中
        request.getSession().setAttribute("account", account);
        return jsonMessage;
    }

    /**
     * 用户登录
     *
     * @param accountName
     * @param passWord
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public JsonMessage login(String accountName, String passWord, HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if (StringUtils.isAnyBlank(accountName, passWord)) {
            return getJsonMessage(CommonEnums.PARAMS_VALID_ERR);
        }
        Account account = accountService.findByName(accountName);
        if(account == null) return getJsonMessage(CommonEnums.ERROR_LOGIN_ACCOUNT);
        if (!account.getPassword().equals(passWord)) {
            return getJsonMessage(CommonEnums.ERROR_LOGIN_PASSWORD);
        }
        if(account.getState().equals("abnormal "))
        {
            return getJsonMessage(1000, "用户被冻结, 请联系管理员！");
        }
        // 设置用户信息到session中
        request.getSession().setAttribute("account", account);
        jsonMessage.setObject(account.getAccountName());
        return jsonMessage;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/exit")
    @ResponseBody
    public JsonMessage exitLogin(HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        request.getSession().removeAttribute("account");
        return jsonMessage;
    }


}
