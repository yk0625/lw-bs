package com.xtt.shopcommon.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Introduce
 * <p>File：PaginateResult.java</p>
 * <p>Description: 分页结果集</p>
 * <p>Copyright: Copyright (c) 2019/4/23 23:28 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public class PaginateResult<T> implements Serializable
{
    private static final long serialVersionUID = -6066538592553602748L;

    @ApiModelProperty(value = "分页对象")
    private Pagination  page;

    @ApiModelProperty(value = "数据列表")
    private List<T>     list;

    public PaginateResult()
    {
    }

    public PaginateResult(Pagination page, List<T> list)
    {
        this.page = page;
        this.list = list;
    }

    public Pagination getPage()
    {
        return page;
    }

    public void setPage(Pagination page)
    {
        this.page = page;
    }

    public List<T> getList()
    {
        return list;
    }

    public void setList(List<T> list)
    {
        this.list = list;
    }
}
