package com.xtt.shopindex.shopping;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Introduce
 * <p>File：</p>
 * <p>Description: Shop dto</p>
 * <p>Copyright: Copyright (c) 2019/4/29 00:23 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public class ShopEntity implements Serializable
{

    private static final long serialVersionUID = 4083086476102923227L;
    @NotNull
    private Integer goodsId;

    private String imageUrl;

    private String goodsName;

    private BigDecimal price;

    @NotNull(message = "数量不能为空")
    private Integer num;

    private BigDecimal totalPrice;

    public ShopEntity(@NotNull Integer goodsId, String imageUrl, String goodsName, BigDecimal price, @NotNull(message = "数量不能为空") Integer num, BigDecimal totalPrice)
    {
        this.goodsId = goodsId;
        this.imageUrl = imageUrl;
        this.goodsName = goodsName;
        this.price = price;
        this.num = num;
        this.totalPrice = totalPrice;
    }

    public Integer getGoodsId()
    {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId)
    {
        this.goodsId = goodsId;
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
}
