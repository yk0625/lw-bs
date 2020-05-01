package com.xtt.shoprpcprovider.productstype.mapper;

import com.xtt.shoprpchander.productstype.entity.ProductsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductsTypeMapper
{
    int insert(ProductsType record);

    int insertSelective(ProductsType record);

    List<ProductsType> selectAllProducts();

    int getTypeNumber();

    int delete(Integer id);

    int updateByPrimaryKeySelective(ProductsType user);

    ProductsType selectById(Integer id);

    List<ProductsType> selectByProductsType(ProductsType productsType);
}