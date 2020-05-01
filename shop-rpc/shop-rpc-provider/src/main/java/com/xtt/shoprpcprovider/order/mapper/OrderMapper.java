package com.xtt.shoprpcprovider.order.mapper;

import com.xtt.shoprpchander.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface OrderMapper
{
    int insert(Order record);

    int insertSelective(Order record);

    /**
     * 批量插入
     * @author
     * @param list
     * @return
     */
    int insertBatch(List<Order> list);

    /**
     * 批量更新
     * @author
     * @param list
     * @return
     */
    int updateBatch(List<Order> list);

    int updateByPrimaryKeySelective(Order entity);

    int delete(Integer id);

    Order selectByPrimaryKey(Long id);

    List<Order> findList(Order entity);

    int delByAccountId(Integer accountId);

    int delByOrderNum(String orderNumber);

    List<Order> findOrderByNum(String orderNumber);

    int submitOrder(String orderNum, String state, Integer addressId);

    int getOrderNumber();

    List<Order> selectAllOrders();

    void updateOrderStatusById(Integer id, String status);

    void delAllOrder(Integer accountId);
}