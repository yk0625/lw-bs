package com.xtt.shoprpcprovider.products.mapper;

import com.xtt.shoprpchander.products.entity.Products;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductsMapper
{
    int insert(Products record);

    int insertSelective(Products record);

    int getProductsNumber(@Param("typeName") String typeName);

    List<Products> selectAllProducts();

    int updateByPrimaryKeySelective(Products products);

    int delete(Integer id);

    Products selectById(Integer id);

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     * @param entity
     * @return
     */
    List<Products> findList(Products entity);

    List<Products> findListByIds(@Param("goodIds") List<Integer> goodIds);
}