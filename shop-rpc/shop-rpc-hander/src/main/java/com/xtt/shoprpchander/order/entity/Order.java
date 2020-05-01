package com.xtt.shoprpchander.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable
{
    private static final long serialVersionUID = 5027970514612269213L;
    private Integer id;

    private Integer accountId;

    private Integer goodsId;

    private Integer addressId;

    private String imageUrl;

    private String goodsName;

    private BigDecimal price;

    private Integer num;

    private BigDecimal totalPrice;

    private String orderNumber;

    private String paymentDate;

    //订单状态(wait 等待支付,canceled 已取消, paymented 已付款, rejected 拒绝发货, shipMented 已发货, received 已收货)
    private String state;

    // 增加返回字段

    private BigDecimal purchasePrice;

    //收货人
    private String consignee;

    // 联系方式
    private String telephone;

    // 收货地址
    private String addressInfo;

    // 查询条件
    private String startDate;

    private String endDate;

    // 按日期排序方式
    private String sort;

    // 单笔订单所有金额
    private BigDecimal allAmount;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Integer accountId)
    {
        this.accountId = accountId;
    }

    public Integer getGoodsId()
    {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId)
    {
        this.goodsId = goodsId;
    }

    public Integer getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Integer addressId)
    {
        this.addressId = addressId;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Integer getNum()
    {
        return num;
    }

    public void setNum(Integer num)
    {
        this.num = num;
    }

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public BigDecimal getPurchasePrice()
    {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    public String getConsignee()
    {
        return consignee;
    }

    public void setConsignee(String consignee)
    {
        this.consignee = consignee;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getAddressInfo()
    {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo)
    {
        this.addressInfo = addressInfo;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public BigDecimal getAllAmount()
    {
        return allAmount;
    }

    public void setAllAmount(BigDecimal allAmount)
    {
        this.allAmount = allAmount;
    }
}