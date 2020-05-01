package com.xtt.shopboss.order;

import com.xtt.shopcommon.excel.ExcelField;

import java.io.Serializable;

/**
 * Introduce
 * <p>File：</p>
 * <p>Description: 订单结算打印模板</p>
 * <p>Copyright: Copyright (c) 2019/5/16 01:53 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public class OrderExcelModel implements Serializable
{

    /**
     * orderNumber     订单编号
     * accountName     收货人姓名
     * phone           手机号
     * address         收货地址
     * totalAmount     总共支付金额
     */
    private static final long serialVersionUID = 510651812374876629L;

    @ExcelField(title = "订单编号")
    private String orderNumber;

    @ExcelField(title = "收货人姓名")
    private String accountName;

    @ExcelField(title = "手机号")
    private String phone;

    @ExcelField(title = "收货地址")
    private String address;

    @ExcelField(title = "总共支付金额")
    private String totalAmount;

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount)
    {
        this.totalAmount = totalAmount;
    }
}
