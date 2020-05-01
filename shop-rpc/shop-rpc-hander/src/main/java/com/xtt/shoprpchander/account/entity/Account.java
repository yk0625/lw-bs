package com.xtt.shoprpchander.account.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Account implements Serializable
{
    private static final long serialVersionUID = 4218171428832131211L;
    private Integer id;

    private String accountName;

    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 8, message = "密码必须是6-8位")
    private String password;

    @NotNull(message = "真实姓名不能为空")
    private String realName;

    @NotNull(message = "手机号不能为空")
    private String phone;

    private String role;

    private String state;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role == null ? null : role.trim();
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state == null ? null : state.trim();
    }
}