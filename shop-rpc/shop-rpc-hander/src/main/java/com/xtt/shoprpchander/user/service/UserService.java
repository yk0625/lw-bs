package com.xtt.shoprpchander.user.service;

import com.xtt.shoprpchander.user.entity.User;

import java.util.List;

public interface UserService
{
    /****
     * 添加用户
     */

    int addUser(User user);

    /**
     * 分页查询
     */
    List<User> findAllUser(int pageNum, int pageSize);

    /**
     * 删除用户
     */
    int delete(int key);

    /***
     * 修改用户
     * */
    int update(User user);

    /**
     * 用户登录查询
     */
    User getUserById(User user);

    int getUsernumber();

}

