package com.xtt.shopindex.order;

import com.xtt.shopcommon.bean.GenericController;
import com.xtt.shopcommon.bean.JsonMessage;
import com.xtt.shopcommon.enums.CommonEnums;
import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.order.entity.Order;
import com.xtt.shoprpchander.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Introduce
 * <p>File：</p>
 * <p>Description: 订单controller</p>
 * <p>Copyright: Copyright (c) 2019/5/5 21:52 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
@Controller
@RequestMapping(value = "order")
public class OrderController extends GenericController
{

    @Autowired(required = false)
    private OrderService orderService;

    /**
     * 根据订单编号删除订单
     *
     * @param orderNumber
     * @return
     */
    @RequestMapping(value = "delOrder")
    @ResponseBody
    public JsonMessage delOrder(String orderNumber)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if (StringUtils.isEmpty(orderNumber))
        {
            return getJsonMessage(1000, "订单号不能为空");
        }
        orderService.delOrder(orderNumber);
        return jsonMessage;
    }

    /**
     * 根据订单编号删除订单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "delAllOrder")
    @ResponseBody
    public JsonMessage delAllOrder(HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null)
        {
            return getJsonMessage(1000, "用户未登陆");
        }
        orderService.delAllOrder(account.getId());
        return jsonMessage;
    }

    /**
     * 添加订单
     *
     * @param orderLst
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JsonMessage order(List<Order> orderLst)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if (orderLst == null || orderLst.size() == 0)
        {
            return getJsonMessage(CommonEnums.PARAMS_VALID_ERR);
        }
        orderService.addOrder(orderLst);
        return jsonMessage;
    }

    /**
     * * 提交订单 根据订单号更新订单状态为paymented(已付款)
     * * 更新订单状态
     *
     * @param orderNum
     * @param addressId
     * @return
     */
    @RequestMapping("submitOrder")
    @ResponseBody
    public JsonMessage submitOrder(String orderNum, Integer addressId)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if (StringUtils.isEmpty(orderNum))
        {
            return getJsonMessage(CommonEnums.PARAMS_VALID_ERR);
        }
        //根据订单号查询订单
        List<Order> tmpOrderLst = orderService.findOrderByNum(orderNum);
        if (tmpOrderLst == null || tmpOrderLst.size() == 0)
        {
            return getJsonMessage(CommonEnums.ERROR_ORDER_EXIST);
        }
        orderService.submitOrder(orderNum, "paymented", addressId);
        return jsonMessage;
    }

}
