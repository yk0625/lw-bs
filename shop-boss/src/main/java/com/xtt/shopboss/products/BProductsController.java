package com.xtt.shopboss.products;

import com.xtt.shoprpchander.products.entity.Products;
import com.xtt.shoprpchander.products.service.ProductsService;
import com.xtt.shoprpchander.productstype.entity.ProductsType;
import com.xtt.shoprpchander.productstype.service.ProductsTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 商品管理controller
 */
@Controller
@RequestMapping(value = "p")
public class BProductsController
{

    private static final String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    @Autowired(required = false)
    private ProductsTypeService productsTypeService;

    @Autowired(required = false)
    private ProductsService productsService;

    /**
     * 商品类型导航
     *
     * @return
     */
    @RequestMapping("/productsType/index")
    public String productsTypeIndex()
    {
        return "/products/type";
    }

    /**
     * 获取商品类型列表
     *
     * @param page
     * @param rows
     * @return
     */
    @PostMapping(value = "/productsTypeList")
    @ResponseBody
    public Map productsTypeList(int page, int rows)
    {
        int startRecord = (page - 1) * rows + 1;
        int total = productsTypeService.getTypeNumber();
        List<ProductsType> productsTypeList = productsTypeService.findAllProductsType(startRecord, rows);
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", productsTypeList);
        return resultMap;
    }

    /**
     * 添加商品类型
     *
     * @param productsType
     * @return
     */
    @RequestMapping(value = "save_productsType")
    @ResponseBody
    public Map<String, String> saveProductsType(ProductsType productsType)
    {
        Map<String, String> map = new HashMap<>();
        try
        {
            //判断商品类型是否存在
            ProductsType temp = new ProductsType();
            temp.setTypeName(productsType.getTypeName());
            List<ProductsType> result = productsTypeService.findByProductsType(temp);
            if (result.size() > 0)
            {
                map.put("msg", "商品类型名称重复");
                return map;
            }
            productsTypeService.addProductsType(productsType);
            map.put("success", "true");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            map.put("msg", "商品类型信息错误");
        }
        return map;
    }

    //修改商品类别信息
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public Map update(ProductsType productsType)
    {
        Map result = new HashMap();
        try
        {
            productsTypeService.update(productsType);
            result.put("success", "true");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.put("msg", "更新失败");
        }
        return result;
    }

    /***
     * 删除商品类别 返回map形式
     *
     */
    @PostMapping(value = "/remove_productsType")
    @ResponseBody
    public Map<String, String> removeUsers(@RequestParam("id") Integer id)
    {
        Map<String, String> result = new HashMap<>();
        try
        {
            productsTypeService.delete(id);
            result.put("success", "true");
            System.out.println("删除商品类别, Id: " + id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.put("msg", "Some errors occured.");
        }
        return result;
    }
//-----------------------------------------------商品管理----------------------------------------------------------------

    /**
     * 商品导航
     *
     * @return
     */
    @RequestMapping(value = "/products/index")
    public String productsIndex()
    {
        return "/products/index";
    }

    @RequestMapping(value = "productsList")
    @ResponseBody
    public Map getAllProducts(int page, int rows)
    {
        int startRecord = (page - 1) * rows + 1;
        int total = productsService.getProductsNumber(null);
        List<Products> productsTypeList = productsService.findAllProducts(startRecord, rows);
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", productsTypeList);
        return resultMap;
    }

    /**
     * 添加商品类型
     *
     * @return
     */
    @RequestMapping(value = "save_products")
    @ResponseBody
    public Map<String, String> saveProducts(Products products)
    {
        Map<String, String> map = new HashMap<>();
        try
        {
            if (StringUtils.isBlank(products.getTypeName()))
                map.put("msg", "商品类型必须输入");
            // 判断商品类型是否存在
            ProductsType temp = new ProductsType();
            temp.setTypeName(products.getTypeName());
            List<ProductsType> result = productsTypeService.findByProductsType(temp);
            if (result.size() == 0)
            {
                map.put("msg", "商品类型不存在");
                return map;
            }
            products.setProductsTypeId(Integer.valueOf(result.get(0).getTypeNumber()));
            productsService.addProducts(products);
            map.put("success", "true");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            map.put("msg", "保存商品信息失败");
        }
        return map;
    }

    //修改商品信息
    @ResponseBody
    @RequestMapping(value = "/updateProducts", produces = {"application/json;charset=UTF-8"})
    public Map updateProducts(Products products)
    {
        Map result = new HashMap();
        try
        {
            productsService.updateByPrimaryKeySelective(products);
            result.put("success", "true");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.put("msg", "更新失败");
        }
        return result;
    }

    /***
     * 删除商品信息 返回map形式
     *
     */
    @PostMapping(value = "/remove_products")
    @ResponseBody
    public Map<String, String> removeProducts(@RequestParam("id") Integer id)
    {
        Map<String, String> result = new HashMap<>();
        try
        {
            productsService.deleteProducts(id);
            result.put("success", "true");
            System.out.println("删除商品信息, Id: " + id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.put("msg", "Some errors occured.");
        }
        return result;
    }

    /**
     * 生成随机isbn编号
     *
     * @return
     */
    public String createIsbnNum(int length)
    {
        //由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i)
        {
            //产生0-61的数字
            int number = random.nextInt(str.length());
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }
}
