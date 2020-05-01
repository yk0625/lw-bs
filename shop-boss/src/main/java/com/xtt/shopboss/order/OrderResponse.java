package com.xtt.shopboss.order;

import java.math.BigDecimal;

/**
 * Introduce
 * <p>File：</p>
 * <p>Description: 订单统计返回对象</p>
 * <p>Copyright: Copyright (c) 2019/5/10 02:25 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public class OrderResponse
{

    private String orderNum;

    private String paymentDate;

    // 订单成本价
    private BigDecimal costPrice;

    // 订单总收款
    private BigDecimal totalSum;

    //订单盈利金额
    private BigDecimal profit;

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getCostPrice()
    {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice)
    {
        this.costPrice = costPrice;
    }

    public BigDecimal getTotalSum()
    {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum)
    {
        this.totalSum = totalSum;
    }

    public BigDecimal getProfit()
    {
        return profit;
    }

    public void setProfit(BigDecimal profit)
    {
        this.profit = profit;
    }
}
