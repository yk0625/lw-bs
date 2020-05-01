package com.xtt.shoprpchander.productstype.service;

import com.xtt.shoprpchander.productstype.entity.ProductsType;

import java.util.List;

/**
 * Introduce
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/14</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public interface ProductsTypeService
{

    /**
     * 查询所有的商品类型
     *
     * @return
     */
    List<ProductsType> findAllProductsType(int pageNum, int rows);

    int getTypeNumber();

    int addProductsType(ProductsType productsType);

    int delete(Integer id);

    int update(ProductsType user);

    /**
     * 根据商品类型ID查询商品类型信息
     *
     * @param id
     * @return
     */
    ProductsType findById(Integer id);

    /**
     * 根据条件查询商品类型列表
     *
     * @param productsType
     * @return
     */
    List<ProductsType> findByProductsType(ProductsType productsType);
}
