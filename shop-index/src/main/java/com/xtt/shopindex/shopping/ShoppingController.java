package com.xtt.shopindex.shopping;

import com.xtt.shopcommon.bean.GenericController;
import com.xtt.shopcommon.bean.JsonMessage;
import com.xtt.shopcommon.enums.CommonEnums;
import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.products.entity.Products;
import com.xtt.shoprpchander.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车信息存放在session中
 * <p>File：</p>
 * <p>Description: 购物车controller</p>
 * <p>Copyright: Copyright (c) 2019/4/29 00:16 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
@Controller
@RequestMapping(value = "shop")
public class ShoppingController extends GenericController
{

    @Autowired(required = false)
    private ProductsService productsService;

    /**
     * 添加购物车
     *
     * @param request
     * @param shopEntity
     * @return
     */
    @RequestMapping(value = "addShop")
    @ResponseBody
    public JsonMessage addShop(HttpServletRequest request, ShopEntity shopEntity)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null)
        {
            return getJsonMessage(CommonEnums.USER_NOT_LOGIN);
        }
        if (shopEntity.getGoodsId() == null) return getJsonMessage(CommonEnums.ERROR_GOODS_EXIST);
        if (shopEntity.getNum() == null) return getJsonMessage(CommonEnums.ERROR_NUM_EXIST);
        //查询商品信息
        Products products = productsService.findById(shopEntity.getGoodsId());
        if (products == null) return getJsonMessage(CommonEnums.PARAMS_VALID_ERR);
        shopEntity.setGoodsName(products.getGoodsName());
        shopEntity.setImageUrl(products.getGoodsImage());
        shopEntity.setPrice(products.getSellPrice());
        Map<Integer, ShopEntity> result = (Map<Integer, ShopEntity>) request.getSession().getAttribute("shopEntity");
        if (CollectionUtils.isEmpty(result))
        {
            result = new HashMap<>();
        }
        // 如果存在则做加减法
        if (result.keySet().contains(shopEntity.getGoodsId()))
        {
            shopEntity.setNum(shopEntity.getNum() + result.get(shopEntity.getGoodsId()).getNum());
        }
        shopEntity.setTotalPrice(shopEntity.getPrice().multiply(new BigDecimal(shopEntity.getNum())));
        // 价格精度控制
        shopEntity.setPrice(shopEntity.getPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        shopEntity.setTotalPrice(shopEntity.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        result.put(shopEntity.getGoodsId(), shopEntity);

        request.getSession().setAttribute("shopEntity", result);
        return jsonMessage;
    }

    /**
     * 删除购物车信息
     *
     * @param goodIds
     * @param request
     * @return
     */
    @RequestMapping("delShopInfo")
    @ResponseBody
    public JsonMessage delShopInfo(String[] goodIds, HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        Map<Integer, ShopEntity> result = (Map<Integer, ShopEntity>) request.getSession().getAttribute("shopEntity");
        for (String item : goodIds)
        {
            System.out.println("删除购物车信息 ： " + item);
            result.remove(Integer.valueOf(item));
        }
        return jsonMessage;
    }

    /**
     * 查询购物车列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "shopInfo")
    @ResponseBody
    public JsonMessage getShopInfo(HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        Map<Integer, ShopEntity> result = (Map<Integer, ShopEntity>) request.getSession().getAttribute("shopEntity");
        if (CollectionUtils.isEmpty(result))
        {
            result = new HashMap<>();
        }
        List<ShopEntity> resp = new ArrayList<>();
        int num = 0;
        for (ShopEntity shopEntity : result.values())
        {
            resp.add(shopEntity);
            num++;
            /*if (num >= showNum)
            {
                break;
            }*/
        }
        jsonMessage.setObject(resp);
        return jsonMessage;
    }

    /**
     * 购物车结算
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "countShop")
    @ResponseBody
    public JsonMessage countShop(HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        Map<Integer, ShopEntity> result = (Map<Integer, ShopEntity>) request.getSession().getAttribute("shopEntity");
        if (CollectionUtils.isEmpty(result))
        {
            result = new HashMap<>();
        }
        // 返回结果
        Map<String, String> resp = new HashMap<>();
        resp.put("totalNum", String.valueOf(result.keySet().size()));
        //计算商品总额
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ShopEntity value : result.values())
        {
            totalPrice = totalPrice.add(value.getTotalPrice());
        }
        resp.put("totalPrice", totalPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN).toPlainString());
        jsonMessage.setObject(resp);
        return jsonMessage;
    }

//--------------------------------------------------结算清单------------------------------------------------------------


    /**
     * 查询用户地址列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "addressList")
    @ResponseBody
    public JsonMessage findAddressList(HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        // 获取用户信息
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null) return getJsonMessage(CommonEnums.USER_NOT_LOGIN);
        //根据账户ID查询用户收货地址列表

        return jsonMessage;
    }

    /**
     * 用户添加收货地址 最多添加10个地址
     *
     * @return
     */
    @RequestMapping(value = "addAddress")
    @ResponseBody
    public JsonMessage addAddress()
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);

        return jsonMessage;
    }
}
