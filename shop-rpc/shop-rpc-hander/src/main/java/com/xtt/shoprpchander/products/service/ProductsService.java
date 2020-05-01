package com.xtt.shoprpchander.products.service;

import com.xtt.shopcommon.bean.PaginateResult;
import com.xtt.shopcommon.bean.Pagination;
import com.xtt.shopcommon.exception.BusinessException;
import com.xtt.shoprpchander.products.entity.Products;

import java.util.List;

/**
 * Introduce
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/15</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public interface ProductsService
{
    int getProductsNumber(String typeName);

    List<Products> findAllProducts(int page, int rows);

    int addProducts(Products products);

    int updateByPrimaryKeySelective(Products products);

    int deleteProducts(Integer id);

    Products findById(Integer id);

    /**
     * 分页查询
     *
     * @param pagin
     * @param entity
     * @return {@link PaginateResult}
     * @throws BusinessException
     */
    PaginateResult<Products> search(Pagination pagin, Products entity) throws BusinessException;

    /**
     * 查询商品列表接口
     *
     * @param entity
     * @return
     * @throws BusinessException
     */
    List<Products> findList(Products entity) throws BusinessException;

    /**
     * 查询商品列表接口
     *
     * @param goodIds
     * @return
     * @throws BusinessException
     */
    List<Products> findListByIds(List<Integer> goodIds) throws BusinessException;
}
