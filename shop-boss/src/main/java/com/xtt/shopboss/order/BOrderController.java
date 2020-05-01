package com.xtt.shopboss.order;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xtt.shopboss.enums.OrderStateEnum;
import com.xtt.shopcommon.bean.GenericController;
import com.xtt.shopcommon.bean.JsonMessage;
import com.xtt.shopcommon.excel.ExportExcel;
import com.xtt.shopcommon.utils.DateUtils;
import com.xtt.shoprpchander.order.entity.Order;
import com.xtt.shoprpchander.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单管理控制器
 */
@Controller
@RequestMapping(value = "o")
public class BOrderController extends GenericController
{

    @Autowired(required = false)
    private OrderService orderService;

    @RequestMapping(value = "/order/index")
    public String orderIndex(Order order, Model model)
    {
        List<Order> result = orderService.findOrderList(order);
        model.addAttribute("result", result);
        return "/order/index";
    }

    @RequestMapping(value = "/order/count")
    public String orderCount()
    {
        return "/order/count";
    }

    /**
     * 获取商品类型列表
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/orderList")
    @ResponseBody
    public Map orderList(int page, int rows)
    {
        int startRecord = (page - 1) * rows + 1;
        int total = orderService.getTypeNumber();
        List<Order> orderList = orderService.findAllOrder(startRecord, rows);
        orderList.forEach(item -> {
            item.setState(OrderStateEnum.getCNStateByEN(item.getState()));
        });
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", orderList);
        return resultMap;
    }

    /**
     * 后台订单审核
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/confirmOrder")
    @ResponseBody
    public Map<String, String> confirmOrder(@RequestParam("id") Integer id, @RequestParam("status") String status)
    {
        Map<String, String> result = new HashMap<>();
        try
        {
            orderService.updateOrderStatusById(id, status);
            result.put("success", "true");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.put("msg", "Some errors occured.");
        }
        return result;
    }

    /**
     * 后台销售统计
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/saleSettlementLst")
    @ResponseBody
    public Map saleSettlementLst(int page, int rows, @RequestParam("startTime") String startDate, @RequestParam("endTime") String endDate)
    {
        int startRecord = (page - 1) * rows + 1;
        int total = orderService.getTypeNumber();

        Order order = new Order();
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        // 使用有序Map返回
        LinkedHashMap<String, List<Order>> data = orderService.findOrderLst(order);


        // 封装返回结果
        List<OrderResponse> result = new ArrayList<>();
        for (String key : data.keySet())
        {
            BigDecimal costPrice = BigDecimal.ZERO;
            data.get(key).forEach(item -> {
                costPrice.add(item.getPurchasePrice());
            });
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderNum(key);
            orderResponse.setPaymentDate(data.get(key).get(0).getPaymentDate());
            orderResponse.setCostPrice(costPrice);
            orderResponse.setTotalSum(data.get(key).get(0).getAllAmount());
            orderResponse.setProfit(data.get(key).get(0).getAllAmount().subtract(costPrice));
            result.add(orderResponse);
        }
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", result);
        return resultMap;
    }

    /**
     * 交易历史报表导出
     * 根据订单号分组导出
     * 打印字段:
     * orderNumber     订单编号
     * accountName     收货人姓名
     * phone           手机号
     * address         收货地址
     * totalAmount     总共支付金额
     * List<product>   订单集合
     */
    @ResponseBody
    @RequestMapping(value = "/orderHistory/export", method = RequestMethod.GET)
    public void export(Order order, HttpServletResponse response) throws IOException
    {

        List<OrderExcelModel> list = findOrderCountInfo(order);
        ExportExcel excel = new ExportExcel("当月销售订单统计 【" + DateUtils.getBeginDayOfMonth() + "~" + DateUtils.getEndDayOfMonth() + "】", OrderExcelModel.class);
        excel.setDataList(list);
        excel.write(response, "红红网" + DateUtils.getNowMonth() + "月销售订单统计.xls");
    }

    /**
     * 获取商品类型列表
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/orderCount")
    @ResponseBody
    public Map orderCount(int page, int rows, Order order)
    {
        List<OrderExcelModel> list = findOrderCountInfo(order);
        int size = list.size();
        int pageCount = size / rows;
        int fromIndex = rows * (page - 1);
        int toIndex = fromIndex + rows;
        if (toIndex >= size)
        {
            toIndex = size;
        }
        if (page > pageCount + 1)
        {
            fromIndex = 0;
            toIndex = 0;
        }
        list = list.subList(fromIndex, toIndex);
        Map resultMap = new HashMap();
        resultMap.put("total", size - 1);
        resultMap.put("rows", list);
        return resultMap;
    }

    /**
     * 查询销售统计
     *
     * @param order
     * @return
     */
    public List<OrderExcelModel> findOrderCountInfo(Order order)
    {
        LinkedHashMap<String, List<Order>> result = orderService.findOrderLst(order);
        List<OrderExcelModel> list = Lists.newArrayList();
        // 封装打印对象
        for (String key : result.keySet())
        {
            List<Order> data = result.get(key);
            BigDecimal totalAmount = BigDecimal.ZERO;
            for(Order or : data)
            {
                totalAmount = totalAmount.add(or.getTotalPrice());
            }
            OrderExcelModel orderExcelModel = new OrderExcelModel();
            orderExcelModel.setOrderNumber(data.get(0).getOrderNumber());
            orderExcelModel.setAccountName(data.get(0).getConsignee());
            orderExcelModel.setPhone(data.get(0).getTelephone());
            orderExcelModel.setAddress(data.get(0).getAddressInfo());
            orderExcelModel.setTotalAmount(totalAmount.toPlainString());
            list.add(orderExcelModel);
        }
        return list;
    }
}
