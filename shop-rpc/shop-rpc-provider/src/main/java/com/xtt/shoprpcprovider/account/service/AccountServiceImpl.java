package com.xtt.shoprpcprovider.account.service;

import com.github.pagehelper.PageHelper;
import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.account.service.AccountService;
import com.xtt.shoprpcprovider.account.mapper.AccountMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class AccountServiceImpl implements AccountService
{
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAllUser(int pageNum, int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);
        return accountMapper.selectAllUser();
    }

    @Override
    public int getUsernumber()
    {
        return accountMapper.getUsernumber();
    }

    @Override
    public int delete(Integer id)
    {
        return accountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Account user)
    {
        return accountMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int addAccount(Account account)
    {
        return accountMapper.insert(account);
    }

    @Override
    public Account findByName(String accountName)
    {
        if(StringUtils.isBlank(accountName)) return null;
        return accountMapper.findByName(accountName);
    }


}
