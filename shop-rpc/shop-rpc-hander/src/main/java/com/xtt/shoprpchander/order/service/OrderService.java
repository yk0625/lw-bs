package com.xtt.shoprpchander.order.service;

import com.xtt.shoprpchander.order.entity.Order;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Introduce
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/17</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
public interface OrderService
{

    /**
     * 新增订单
     *
     * @param orderLst
     * @return
     */
    int addOrder(List<Order> orderLst);

    /**
     * 批量更新订单
     *
     * @param orderLst
     * @return
     */
    int updateOrderBatch(List<Order> orderLst);

    /**
     * 清空订单
     *
     * @param accountId
     */
    int clearOrder(Integer accountId);

    /**
     * 根据订单编号删除订单
     *
     * @param orderNumber
     * @return
     */
    int delOrder(String orderNumber);

    /**
     * 查询订单列表
     *
     * @param order
     * @return
     */
    List<Order> findOrderList(Order order);

    /**
     * 根据条件查询订单，并按照订单编号分组
     * @param order
     * @return
     */
    LinkedHashMap<String, List<Order>> findOrderLst(Order order);

    /**
     * 根据订单编号查询订单
     *
     * @param orderNum
     * @return
     */
    List<Order> findOrderByNum(String orderNum);

    /**
     * 根据订单号更新订单状态
     * @param orderNum
     * @param addressId
     * @return
     */
    int submitOrder(String orderNum, String state, Integer addressId);

    int getTypeNumber();

    List<Order> findAllOrder(int pageNum, int rows);

    void updateOrderStatusById(Integer id, String status);

    void delAllOrder(Integer accountId);
}
