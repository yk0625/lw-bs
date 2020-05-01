package com.xtt.shopboss.controller;


import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.account.service.AccountService;
import com.xtt.shoprpchander.user.entity.User;
import com.xtt.shoprpchander.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    static final int pageSize = 10;
    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user)
    {
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(HttpServletRequest request, HttpServletResponse response)
    {
        String pageNum = request.getParameter("pageNum");
        int pageNumber = 0;
        if (null == pageNum) {
            pageNumber = 1;
        }

        return userService.findAllUser(pageNumber, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteUser(@PathVariable("key") int key)
    {
        return userService.delete(key);
    }

    //修改用户
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(User user)
    {
        return userService.update(user);
    }


    //用户信息管理
    @RequestMapping(value = "/userinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String userinfomation(HttpSession session)
    {
        return "/common/information";
    }

    /**
     * 用户信息列表
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/userinforlist")
    @ResponseBody
    public Map userinforlist(HttpServletRequest request)
    {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord = (page - 1) * pageSzie + 1;
        int total = accountService.getUsernumber();
        List<Account> userinforlist = accountService.findAllUser(startRecord, pageSzie);
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", userinforlist);
        return resultMap;
    }

    /*
    进入用户信息界面
    */
    @GetMapping(value = "/info")
    public String stuinfor()
    {
        return "/common/information";
    }


    @RequestMapping(value = "/updateMyInfo", method = {RequestMethod.POST, RequestMethod.GET})//保存新增用户和修改的用户信息
    @ResponseBody
    public ModelAndView updateMyInfo(@RequestParam("userId") String userId, @RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("phone") String phone, HttpSession session)
    {
        ModelAndView model = new ModelAndView();
        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setPassword(password);
        user.setUserName(userName);
        user.setPhone(phone);
        try {
            userService.update(user);
            model.addObject("messag", "修改成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("messag", "修改失败");
        model.setViewName("/common/error");
        return model;
    }

    @PostMapping(value = "/save_users")//保存新增用户和修改的用户信息
    @ResponseBody
    public Map<String, String> saveUsers(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("phone") String phone, HttpSession session)
    {

        Map<String, String> map = new HashMap<>();
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        user.setPhone(phone);
        try {
            userService.addUser(user);
            map.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 个人信息修改
     */
    @RequestMapping(value = "myInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView myInfo(HttpSession session)
    {
        ModelAndView model = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Integer id = user.getUserId();
        user.setUserId(id);
        User logUser = userService.getUserById(user);
        session.setAttribute("user", logUser);
        model.addObject("user", logUser);
        model.setViewName("/common/myInfo");
        return model;
    }

    /***
     * 这个方法写得 不好  之后在整理思路
     */
    @RequestMapping(value = "/modifypassword", method = {RequestMethod.POST, RequestMethod.GET})//保存新增用户和修改的用户信息
    @ResponseBody
    public ModelAndView modifypassword(@RequestParam("userId") String userId, @RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword1") String newpassword1, @RequestParam("newpassword2") String newpassword2, HttpSession session)
    {
        ModelAndView model = new ModelAndView();
        User Loginuser = (User) session.getAttribute("user");
        if (oldpassword == null || "".equals(oldpassword)) {
            model.addObject("messag", "初始密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword1 == null || "".equals(newpassword1)) {
            model.addObject("messag", "新密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword2 == null || "".equals(newpassword2)) {
            model.addObject("messag", "确认密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (!newpassword2.equals(newpassword1)) {
            model.addObject("messag", "两次密码不一致");
            model.setViewName("/common/success");
            return model;
        } else if (!Loginuser.getPassword().equals(oldpassword)) {
            model.addObject("messag", "初始密码不正确");
            model.setViewName("/common/success");
            return model;
        }

        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setPassword(newpassword2);
        try {
            userService.update(user);
            model.addObject("messag", "修改成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("messag", "修改失败");
        model.setViewName("/common/success");
        return model;
    }

    @RequestMapping(value = "/exit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView exit(HttpSession session)
    {
        session.removeAttribute("user");
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/login/");
        return model;
    }

}
