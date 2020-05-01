package com.xtt.shoprpcprovider.account.mapper;

import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AccountMapper
{
    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectAllUser();

    int getUsernumber();

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account user);

    Account findByName(String accountName);
}