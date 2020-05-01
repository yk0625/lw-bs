package com.xtt.shoprpcprovider.order.service;

import com.github.pagehelper.PageHelper;
import com.xtt.shopcommon.utils.DateUtils;
import com.xtt.shoprpchander.order.entity.Order;
import com.xtt.shoprpchander.order.service.OrderService;
import com.xtt.shoprpcprovider.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
@Service
public class OrderServiceImpl implements OrderService
{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int addOrder(List<Order> orderLst)
    {
        // 设置订单编号, 使用时间戳作为订单编号
        orderLst.forEach(item -> {
            item.setPaymentDate(DateUtils.formatDate8(new Date()));
            item.setState("wait");
        });
        return orderMapper.insertBatch(orderLst);
    }

    @Override
    public int updateOrderBatch(List<Order> orderLst)
    {
        return orderMapper.updateBatch(orderLst);
    }

    @Override
    public int clearOrder(Integer accountId)
    {
        // 删除用户所有订单
        return orderMapper.delByAccountId(accountId);
    }

    @Override
    public int delOrder(String orderNumber)
    {
        return orderMapper.delByOrderNum(orderNumber);
    }

    @Override
    public List<Order> findOrderList(Order order)
    {
        return orderMapper.findList(order);
    }

    @Override
    public LinkedHashMap<String, List<Order>> findOrderLst(Order order)
    {
        List<Order> result = this.findOrderList(order);
        // key 为订单编号 value 为订单集合
        LinkedHashMap<String, List<Order>> resp = new LinkedHashMap<>();
        for (Order o : result)
        {
            o.setPrice(o.getPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            o.setTotalPrice(o.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            List<Order> item = new ArrayList<>();
            if (resp.keySet().contains(o.getOrderNumber()))
            {
                item = resp.get(o.getOrderNumber());
                item.add(o);
            }
            else
            {
                item.add(o);
            }
            resp.put(o.getOrderNumber(), item);
        }
        for (String key : resp.keySet())
        {
            List<Order> orderLst = resp.get(key);
            BigDecimal allAmount = BigDecimal.ZERO;
            for (Order or : orderLst)
            {
                allAmount = allAmount.add(or.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            }
            for (Order or : orderLst)
            {
                or.setAllAmount(allAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN));
            }
        }
        return resp;
    }

    @Override
    public List<Order> findOrderByNum(String orderNum)
    {
        return orderMapper.findOrderByNum(orderNum);
    }

    @Override
    public int submitOrder(String orderNum, String state, Integer addressId)
    {
        return orderMapper.submitOrder(orderNum, state, addressId);
    }

    @Override
    public int getTypeNumber()
    {
        return orderMapper.getOrderNumber();
    }

    @Override
    public List<Order> findAllOrder(int pageNum, int rows)
    {
        PageHelper.startPage(pageNum, rows);
        return orderMapper.selectAllOrders();
    }

    @Override
    public void updateOrderStatusById(Integer id, String status)
    {
        orderMapper.updateOrderStatusById(id, status);
    }

    @Override
    public void delAllOrder(Integer accountId)
    {
        orderMapper.delAllOrder(accountId);
    }
}
