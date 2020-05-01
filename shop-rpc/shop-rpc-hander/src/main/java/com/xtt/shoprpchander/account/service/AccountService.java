package com.xtt.shoprpchander.account.service;

import com.xtt.shoprpchander.account.entity.Account;

import java.util.List;

/**
 * Introduce
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/14</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public interface AccountService
{

    /**
     * 分页查询
     */
    List<Account> findAllUser(int pageNum, int pageSize);

    int getUsernumber();

    int delete(Integer id);

    int update(Account user);

    /**
     * 用户注册
     * @param account
     * @return
     */
    int addAccount(Account account);

    Account findByName(String accountName);
}
