package com.xtt.shoprpcprovider.productstype.service;

import com.github.pagehelper.PageHelper;
import com.xtt.shoprpchander.productstype.entity.ProductsType;
import com.xtt.shoprpchander.productstype.service.ProductsTypeService;
import com.xtt.shoprpcprovider.productstype.mapper.ProductsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class ProductsTypeImpl implements ProductsTypeService
{

    @Autowired
    private ProductsTypeMapper productsTypeMapper;

    @Override
    public List<ProductsType> findAllProductsType(int pageNum, int rows)
    {
        PageHelper.startPage(pageNum, rows);
        return productsTypeMapper.selectAllProducts();
    }

    @Override
    public int getTypeNumber()
    {
        return productsTypeMapper.getTypeNumber();
    }

    @Override
    public int addProductsType(ProductsType productsType)
    {
        return productsTypeMapper.insertSelective(productsType);
    }

    @Override
    public int delete(Integer id)
    {
        return productsTypeMapper.delete(id);
    }

    @Override
    public int update(ProductsType user)
    {
        return productsTypeMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public ProductsType findById(Integer id)
    {
        return productsTypeMapper.selectById(id);
    }

    @Override
    public List<ProductsType> findByProductsType(ProductsType productsType)
    {
        return productsTypeMapper.selectByProductsType(productsType);
    }
}
