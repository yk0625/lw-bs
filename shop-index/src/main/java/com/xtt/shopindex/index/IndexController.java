package com.xtt.shopindex.index;

import com.xtt.shopcommon.bean.GenericController;
import com.xtt.shopcommon.bean.JsonMessage;
import com.xtt.shopcommon.enums.CommonEnums;
import com.xtt.shopcommon.exception.BusinessException;
import com.xtt.shopcommon.utils.DateUtils;
import com.xtt.shopindex.order.SearchRecord;
import com.xtt.shopindex.shopping.ShopEntity;
import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.order.entity.Order;
import com.xtt.shoprpchander.order.service.OrderService;
import com.xtt.shoprpchander.products.entity.Products;
import com.xtt.shoprpchander.products.service.ProductsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

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
@Controller
@RequestMapping(value = "")
public class IndexController extends GenericController
{

    @Autowired(required = false)
    private ProductsService productsService;

    @Autowired(required = false)
    private OrderService orderService;

    /**
     * 默认导航
     *
     * @return
     */
    @RequestMapping(value = "")
    public String index()
    {
        return "index";
    }

    /**
     * 默认导航
     *
     * @return
     */
    @RequestMapping(value = "homePage")
    public String homePage()
    {
        return "index";
    }

    /**
     * 购物车导航
     *
     * @return
     */
    @RequestMapping(value = "order/cart")
    public String orderCartIndex(HttpServletRequest request)
    {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null)
        {
            return "index";
        }
        return "cart";
    }

    /**
     * 确认订单导航
     *
     * @return
     */
    @RequestMapping(value = "order/confirm")
    public String orderConfirmIndex(Integer[] goodIds, Model model, String orderNum, HttpServletRequest request)
    {
        if (StringUtils.isEmpty(orderNum))
        {
            return "index";
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if (goodIds.length == 0) return "confirm_order";
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null)
        {
            return "index";
        }
        checkAccount(jsonMessage, request);
        List<Integer> cartGoodIds = Arrays.asList(goodIds);
        Map<Integer, ShopEntity> cartLst = (Map<Integer, ShopEntity>) request.getSession().getAttribute("shopEntity");
        List<ShopEntity> result = new ArrayList<>();
        for (Integer key : cartLst.keySet())
        {
            if (cartGoodIds.contains(key))
            {
                result.add(cartLst.get(key));
                totalPrice = totalPrice.add(cartLst.get(key).getTotalPrice());
            }
        }

        model.addAttribute("result", result);
        model.addAttribute("orderNum", orderNum);
        model.addAttribute("totalPrice", totalPrice.toPlainString());
        //根据订单号查询订单
        List<Order> tmpOrderLst = orderService.findOrderByNum(orderNum);
        if (tmpOrderLst == null || tmpOrderLst.size() == 0)
        {
            List<Order> entityLst = new ArrayList<>();
            result.forEach(item -> {
                Order order = new Order();
                order.setState("wait");
                order.setAccountId(account.getId());
                order.setGoodsId(item.getGoodsId());
                order.setImageUrl(item.getImageUrl());
                order.setGoodsName(item.getGoodsName());
                order.setPrice(item.getPrice());
                order.setNum(item.getNum());
                order.setTotalPrice(item.getTotalPrice());
                // 以时间戳作为订单编号
                order.setOrderNumber(orderNum);
                order.setPaymentDate(DateUtils.formatDate8(new Date()));
                entityLst.add(order);
                // 删除购物车信息
                cartLst.remove(item.getGoodsId());
            });
            // 插入订单
            orderService.addOrder(entityLst);
        }
        return "confirm_order";
    }

    /**
     * 确认订单导航
     *
     * @return
     */
    @RequestMapping(value = "order/confirmI")
    public String orderConfirmI(Model model, String orderNum, Integer goodsId, String imageUrl, String goodsName, String price, Integer num, HttpServletRequest request)
    {
        if (StringUtils.isEmpty(price) || num == null)
        {
            return "index";
        }

        BigDecimal currentPrice = new BigDecimal(price);
        ShopEntity shopEntity = new ShopEntity(goodsId, imageUrl, goodsName, currentPrice, num, currentPrice);
        if (StringUtils.isEmpty(orderNum) || shopEntity == null)
        {
            return "index";
        }

        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if (shopEntity.getGoodsId() == null) return "confirm_order";
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null)
        {
            return "index";
        }
        checkAccount(jsonMessage, request);
        List<ShopEntity> result = new ArrayList<>();
        result.add(shopEntity);

        model.addAttribute("result", result);
        model.addAttribute("orderNum", orderNum);
        model.addAttribute("totalPrice", shopEntity.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN).toPlainString());
        //根据订单号查询订单
        List<Order> tmpOrderLst = orderService.findOrderByNum(orderNum);
        if (tmpOrderLst == null || tmpOrderLst.size() == 0)
        {
            List<Order> entityLst = new ArrayList<>();
            result.forEach(item -> {
                Order order = new Order();
                order.setState("wait");
                order.setAccountId(account.getId());
                order.setGoodsId(item.getGoodsId());
                order.setImageUrl(item.getImageUrl());
                order.setGoodsName(item.getGoodsName());
                order.setPrice(item.getPrice());
                order.setNum(item.getNum());
                order.setTotalPrice(item.getTotalPrice());
                // 以时间戳作为订单编号
                order.setOrderNumber(orderNum);
                order.setPaymentDate(DateUtils.formatDate8(new Date()));
                entityLst.add(order);
            });
            // 插入订单
            orderService.addOrder(entityLst);
        }
        return "confirm_order";
    }

    /**
     * 商品详情导航
     *
     * @return
     */
    @RequestMapping(value = "order/detail/{id}")
    public String orderDetailIndex(@PathVariable("id") Integer id, Model model)
    {
        // 根据ID 查询商品信息
        Products products = new Products();
        products.setId(id);
        List<Products> result = productsService.findList(products);
        if (result.size() > 0)
        {
            products = result.get(0);
            products.setSellPrice(products.getSellPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            products.setPurchasePrice(products.getPurchasePrice().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            model.addAttribute("products", products);
        }
        return "details";
    }

    /**
     * 订单列表导航
     *
     * @return
     */
    @RequestMapping(value = "order/list")
    public String orderListIndex(Model model, SearchRecord searchRecord, HttpServletRequest request)
    {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null)
        {
            return "index";
        }
        SearchRecord sessionSearch = (SearchRecord) request.getSession().getAttribute("search_record");
        if (sessionSearch == null)
        {
            sessionSearch = searchRecord;
        }
        else
        {
            // 选择性同步缓存数据
            if (searchRecord.getFlag())
            {
                sessionSearch.setOrderNum(searchRecord.getOrderNum());
            }
            if (StringUtils.isNotEmpty(searchRecord.getStatus()))
            {
                sessionSearch.setStatus(searchRecord.getStatus());
            }
            if (StringUtils.isNotEmpty(searchRecord.getSort()))
            {
                sessionSearch.setSort(searchRecord.getSort());
            }
            if (StringUtils.isNotEmpty(searchRecord.getStartTime()))
            {
                sessionSearch.setStartTime(searchRecord.getStartTime());
            }
            if (StringUtils.isNotEmpty(searchRecord.getEndTime()))
            {
                sessionSearch.setEndTime(searchRecord.getEndTime());
            }
        }

        String orderNum = sessionSearch.getOrderNum();
        String status = sessionSearch.getStatus();
        String sort = sessionSearch.getSort();
        String startTime = sessionSearch.getStartTime();
        String endTime = sessionSearch.getEndTime();

        model.addAttribute("orderNum", orderNum);
        model.addAttribute("status", status);
        model.addAttribute("sort", sort);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        // 查询订单信息
        Order order = new Order();
        order.setOrderNumber(orderNum);
        order.setState(status);
        order.setSort(sort);
        order.setStartDate(startTime);
        order.setEndDate(endTime);
        order.setAccountId(account.getId());
        // 使用有序Map返回
        LinkedHashMap<String, List<Order>> result = orderService.findOrderLst(order);
        model.addAttribute("result", result);
        // 缓存查询条件
        request.getSession().setAttribute("search_record", sessionSearch);
        return "order_list";
    }

    /**
     * 校验用户登陆状态
     *
     * @param jsonMessage
     * @param request
     * @return
     */
    protected boolean checkAccount(JsonMessage jsonMessage, HttpServletRequest request)
    {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        return true;
    }

}


