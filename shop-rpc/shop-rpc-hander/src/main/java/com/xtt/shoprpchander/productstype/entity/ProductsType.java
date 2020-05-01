package com.xtt.shoprpchander.productstype.entity;

import java.io.Serializable;

public class ProductsType implements Serializable
{
    private static final long serialVersionUID = 7104775038884175635L;
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