package com.xtt.shoprpcprovider.address.service;

import com.xtt.shopcommon.exception.BusinessException;
import com.xtt.shoprpchander.address.entity.Address;
import com.xtt.shoprpchander.address.service.AddressService;
import com.xtt.shoprpcprovider.address.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Introduce
 * <p>File：</p>
 * <p>Description: service impl</p>
 * <p>Copyright: Copyright (c) 2019/5/3 21:41 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
@Service
public class AddressServiceImpl implements AddressService
{

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public int save(Address entity) throws BusinessException
    {
        if (null == entity.getId())
        {
            return addressMapper.insert(entity);
        }
        else
        {
            return addressMapper.updateByPrimaryKeySelective(entity);
        }
    }

    @Override
    public int delete(Integer id) throws BusinessException
    {
        return addressMapper.delete(id);
    }

    @Override
    public Address findByPrimaryKey(Long id) throws BusinessException
    {
        return addressMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Address> findList(Address entity) throws BusinessException
    {
        return addressMapper.findList(entity);
    }
}
