package com.xtt.shoprpchander.products.entity;

import java.io.Serializable;

/**
 * Introduce
 * <p>File：products type</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2020/5/4 11:43 </p>
 * <p>Company: bc</p>
 *
 * @author yukai
 * @version 1.0
 */
public class ProductType implements Serializable
{
    private static final long serialVersionUID = 8381329537763589671L;

    private Integer id;

    private String typeNumber;

    private String typeName;

    private String typeOrder;

    private String remark;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTypeNumber()
    {
        return typeNumber;
    }

    public void setTypeNumber(String typeNumber)
    {
        this.typeNumber = typeNumber;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeOrder()
    {
        return typeOrder;
    }

    public void setTypeOrder(String typeOrder)
    {
        this.typeOrder = typeOrder;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
