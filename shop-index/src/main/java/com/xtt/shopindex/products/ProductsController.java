package com.xtt.shopindex.products;

import com.google.common.collect.Lists;
import com.xtt.shopcommon.bean.GenericController;
import com.xtt.shopcommon.bean.JsonMessage;
import com.xtt.shopcommon.bean.PaginateResult;
import com.xtt.shopcommon.bean.Pagination;
import com.xtt.shopcommon.enums.CommonEnums;
import com.xtt.shoprpchander.products.entity.Products;
import com.xtt.shoprpchander.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

/**
 * Introduce
 * <p>Title: </p>
 * <p>File：ProductsController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019/4/23 22:34 </p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/products")
public class ProductsController extends GenericController
{

    @Autowired(required = false)
    private ProductsService productsService;

    /**
     * 获取商品列表
     *
     * @param typeName      商品类型
     * @param goodsName     商品名称
     * @param pagination    分页对象
     * @return
     */
    @RequestMapping(value = "productsList")
    @ResponseBody
    public JsonMessage productsList(String typeName, String goodsName, Pagination pagination)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        Products products = new Products();
        products.setTypeName(typeName);
        products.setGoodsName(goodsName);
        PaginateResult<Products> result = productsService.search(pagination, products);
        jsonMessage.setObject(result);
        return jsonMessage;
    }

    /**
     * 换一批推荐
     * @return
     */
    @RequestMapping("changeBatch")
    @ResponseBody
    public JsonMessage changeBatch()
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        List<Products> result =  productsService.findList(new Products());
        //随机取三条数据
        List<Products> resp = Lists.newArrayList();
        Random random = new Random();
        List<Integer> integerList = Lists.newArrayList();
        while (true)
        {
            int tmp = random.nextInt(result.size());
            if(!integerList.contains(tmp))
            {
                integerList.add(tmp);
                resp.add(result.get(tmp));
            }
            if(integerList.size() >= 3)
            {
                break;
            }
        }
        jsonMessage.setObject(resp);
        return jsonMessage;
    }

}
