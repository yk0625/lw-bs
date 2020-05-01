package com.xtt.shopboss.user;

import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.account.service.AccountService;
import com.xtt.shoprpchander.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "u")
public class BUserController
{


    @Autowired(required = false)
    private AccountService accountService;

    /***
     * 删除用户 返回map形式
     *
     */
    @PostMapping(value = "/remove_account")//删除用户
    @ResponseBody
    public Map<String, String> removeUsers(@RequestParam("id") Integer id, HttpSession session)
    {
        Map<String, String> result = new HashMap<>();
        if (((User) session.getAttribute("user")).getUserId().equals(id)) {
            result.put("msg", "违法操作！不能删除自己！");
            return result;
        }
        try {
            accountService.delete(id);
            result.put("success", "true");
            System.out.println("删除Id: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "Some errors occured.");
        }
        return result;
    }

    @PostMapping(value = "/update")//保存新增用户和修改的用户信息
    @ResponseBody
    public Map<String, String> update(Account account)
    {

        Map<String, String> map = new HashMap<>();
//        User user = new User();
//        user.setUserId(Integer.parseInt(id));
//        user.setPassword(password);
//        user.setUserName(userName);
//        user.setPhone(phone);
        try {
            accountService.update(account);
            map.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "请核对信息确保登录名唯一");
        }
        return map;
    }
}
