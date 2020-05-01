package com.xtt.shoprpcprovider.products.service;

import com.github.pagehelper.PageHelper;
import com.xtt.shopcommon.bean.PaginateResult;
import com.xtt.shopcommon.bean.Pagination;
import com.xtt.shopcommon.exception.BusinessException;
import com.xtt.shoprpchander.products.entity.Products;
import com.xtt.shoprpchander.products.service.ProductsService;
import com.xtt.shoprpcprovider.products.mapper.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class ProductsServiceImpl implements ProductsService
{

    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public int getProductsNumber(String typeName)
    {
        return productsMapper.getProductsNumber(typeName);
    }

    @Override
    public List<Products> findAllProducts(int page, int rows)
    {
        PageHelper.startPage(page, rows);
        return productsMapper.selectAllProducts();
    }

    @Override
    public int addProducts(Products products)
    {
        return productsMapper.insertSelective(products);
    }

    @Override
    public int updateByPrimaryKeySelective(Products products)
    {
        return productsMapper.updateByPrimaryKeySelective(products);
    }

    @Override
    public int deleteProducts(Integer id)
    {
        return productsMapper.delete(id);
    }

    @Override
    public Products findById(Integer id)
    {
        return productsMapper.selectById(id);
    }

    @Override
    public PaginateResult<Products> search(Pagination pagin, Products entity) throws BusinessException
    {
        if (null == pagin) pagin = new Pagination();
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        List<Products> data = findList(entity);
        Integer total = getProductsNumber(entity.getTypeName());
        if (total != null)
            pagin.init(Long.valueOf(total));
        pagin.setHasNextPage(pagin.getTotalPage() != pagin.getPage());
        pagin.setHasPreviousPage(pagin.getPage() != 1);
        return new PaginateResult<>(pagin, data);
    }

    public List<Products> findList(Products entity)
    {
        return productsMapper.findList(entity);
    }

    @Override
    public List<Products> findListByIds(List<Integer> goodIds) throws BusinessException
    {
        if(goodIds.isEmpty()) return null;
        return productsMapper.findListByIds(goodIds);
    }
}
