package com.xtt.shopboss.controller;

import com.xtt.shoprpchander.user.entity.User;
import com.xtt.shoprpchander.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "")
public class LoginController
{

    @Autowired(required = false)
    private UserService userService;

    /**
     * 默认导航
     *
     * @return
     */
    @RequestMapping(value = "login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login()
    {
        return "/login/login";
    }

    /**
     * 默认导航
     *
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public String defaultLogin()
    {
        return "/login/login";
    }

    /***
     * 这是一种返回形式 以ModelAndView 视图返回数据的形式返回 和返回map一样 调用方式不一样
     * */
    @RequestMapping(value = "/userLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, String userName)
    {
        ModelAndView model = new ModelAndView();
//        String id = request.getParameter("id");
        String password = request.getParameter("password");
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        User loginUser = userService.getUserById(user);
        if (null != loginUser) {
            session.setAttribute("user", loginUser);
            model.setViewName("login/index");
        } else {
            model.addObject("MSG", "用户名或密码错误");
            model.setViewName("/login/login");
        }
        return model;
    }

    /**
     * 登录成功 加载欢迎页面  返回String y页面的路径和名称
     */
    @RequestMapping(value = "welcome", method = {RequestMethod.POST, RequestMethod.GET})
    public String welcome()
    {
        return "/login/welcome";
    }
}
