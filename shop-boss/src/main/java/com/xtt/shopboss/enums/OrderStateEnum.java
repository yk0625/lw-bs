package com.xtt.shopboss.enums;

/**
 * Introduce
 * <p>File：</p>
 * <p>Description: 订单状态枚举值</p>
 * <p>Copyright: Copyright (c) 2019/5/9 00:55 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public enum OrderStateEnum
{
    WAIT("wait", "等待支付"),
    CANCELED("canceled", "已取消"),
    PAYMENTED("paymented", "已付款"),
    REJECTED("rejected", "拒绝发货"),
    SHIPMENTED("shipMented", "已发货"),
    RECEIVED("received", "已收货"),
    ;

    private String enState;

    private String cnState;

    OrderStateEnum(String enState, String cnState)
    {
        this.enState = enState;
        this.cnState = cnState;
    }

    public String getEnState()
    {
        return enState;
    }

    public String getCnState()
    {
        return cnState;
    }

    public static String getENStateByCN(String cnState)
    {
        for (OrderStateEnum ele : values())
        {
            if (ele.getCnState().equals(cnState)) return ele.getEnState();
        }
        return null;
    }

    public static String getCNStateByEN(String enState)
    {
        for (OrderStateEnum ele : values())
        {
            if (ele.getEnState().equals(enState)) return ele.getCnState();
        }
        return null;
    }
}
