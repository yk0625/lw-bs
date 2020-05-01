package com.xtt.shoprpcprovider.address.mapper;

import com.xtt.shoprpchander.address.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AddressMapper
{
    int insert(Address record);

    int insertSelective(Address record);

    int updateByPrimaryKeySelective(Address entity);

    int delete(Integer id);

    Address selectByPrimaryKey(Long id);

    List<Address> findList(Address entity);
}