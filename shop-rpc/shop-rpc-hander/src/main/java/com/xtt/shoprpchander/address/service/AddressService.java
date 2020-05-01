package com.xtt.shoprpchander.address.service;

import com.xtt.shopcommon.exception.BusinessException;
import com.xtt.shoprpchander.address.entity.Address;

import java.util.List;

/**
 * Introduce
 * <p>File：</p>
 * <p>Description: address interface</p>
 * <p>Copyright: Copyright (c) 2019/5/3 21:41 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public interface AddressService
{
    /**
     * 插入或修改数据
     * @param entity
     * @return {@link Integer}
     * @throws BusinessException
     */
    int save(Address entity) throws BusinessException;

    /**
     * 逻辑删除
     * @param id
     * @return {@link Integer}
     * @throws BusinessException
     */
    int delete(Integer id) throws BusinessException;

    /**
     * 根据主键查询数据
     * @param id
     * @return {@link Address}
     * @throws BusinessException
     */
    Address findByPrimaryKey(Long id) throws BusinessException;

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     * @param entity
     * @return {@link List}
     * @throws BusinessException
     */
    List<Address> findList(Address entity) throws BusinessException;



    
}
