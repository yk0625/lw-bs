package com.xtt.shopindex.order;

/**
 * Introduce
 * <p>File：</p>
 * <p>Description: 订单界面查询条件bean</p>
 * <p>Copyright: Copyright (c) 2019/5/8 00:43 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public class SearchRecord
{

    private boolean flag = false;

    private String orderNum;

    private String status;

    private String sort;

    private String startTime;

    private String endTime;

    public boolean getFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
}
