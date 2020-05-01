package com.xtt.shopcommon.bean;

import com.xtt.shopcommon.consts.SystemConst;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Introduce
 * <p>File：分页对象</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019/4/23 23:18 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public class Pagination implements Serializable
{

    private static final long serialVersionUID = 1896585579940785430L;
    // 是否有上一页
    @ApiModelProperty(value = "是否有上一页", hidden = true)
    protected Boolean         hasPreviousPage = false;

    // 是否有下一页
    @ApiModelProperty(value = "是否有下一页", hidden = true)
    protected Boolean         hasNextPage     = false;

    // 每页的记录数
    @ApiModelProperty(value = "分页大小")
    protected Integer         rows             = SystemConst.DEFAULT_PAGE_SIZE;

    // 当前是第几页
    @ApiModelProperty(value = "当前页数")
    protected Integer         page             = SystemConst.DEFAULT_CURRENT_PAGE;

    // 记录的总数量
    @ApiModelProperty(value = "记录的总数量", hidden = true)
    protected Long            totalRows;

    // 记录的总页数
    @ApiModelProperty(value = "记录的总页数", hidden = true)
    protected Integer         totalPage;

    public void init(Long totalRows)
    {
        // 通过totalRows计算总页数
        if(totalRows == null) return;
        this.setTotalRows(totalRows);
        totalPage = Math.toIntExact((totalRows + (rows - 1)) / rows);
        if(totalPage > 1 && page < totalPage) hasNextPage = true;
        if(totalPage > 1 && page > 1) hasNextPage = true;
    }

    public Boolean getHasPreviousPage()
    {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage)
    {
        this.hasPreviousPage = hasPreviousPage;
    }

    public Boolean getHasNextPage()
    {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage)
    {
        this.hasNextPage = hasNextPage;
    }

    public Integer getRows()
    {
        return rows;
    }

    public void setRows(Integer rows)
    {
        this.rows = rows;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Long getTotalRows()
    {
        return totalRows;
    }

    public void setTotalRows(Long totalRows)
    {
        this.totalRows = totalRows;
    }

    public Integer getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage)
    {
        this.totalPage = totalPage;
    }
}
