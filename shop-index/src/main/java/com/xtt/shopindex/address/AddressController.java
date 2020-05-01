package com.xtt.shopindex.address;

import com.xtt.shopcommon.bean.GenericController;
import com.xtt.shopcommon.bean.JsonMessage;
import com.xtt.shopcommon.enums.CommonEnums;
import com.xtt.shoprpchander.account.entity.Account;
import com.xtt.shoprpchander.address.entity.Address;
import com.xtt.shoprpchander.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户地址管理
 * <p>Title: ${Title}</p>
 * <p>File：${File}</p>
 * <p>Description: ${Description}</p>
 * <p>Copyright: Copyright (c) 2019/4/19</p>
 * <p>Company: bc</p>
 *
 * @author xtt
 * @version 1.0
 */
@Controller
@RequestMapping(value = "address")
public class AddressController extends GenericController
{

    @Autowired(required = false)
    private AddressService addressService;

    /**
     * 新增地址
     *
     * @param address
     * @return
     */
    @RequestMapping("addAddress")
    @ResponseBody
    public JsonMessage addAddress(Address address, HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if (!beanValidator(jsonMessage, address)) return jsonMessage;
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null) return getJsonMessage(CommonEnums.USER_NOT_LOGIN);
        address.setAccountId(account.getId());
        // 查询是否有默认地址
        Address tmpAddress = new Address();
        tmpAddress.setAccountId(account.getId());
        List<Address> addressList = addressService.findList(tmpAddress);
        if (addressList.size() > 10) return getJsonMessage(1000, "用户最多添加10个地址");
        // 重置默认地址
        if (address.getFlag() == 1)
        {
            tmpAddress.setFlag(1);
            addressList = addressService.findList(tmpAddress);
            if (addressList.size() != 0)
            {
                Address address1 = addressList.get(0);
                address1.setFlag(0);
                addressService.save(address1);
            }
        }
        addressService.save(address);
        Address address1 = new Address();
        address1.setAccountId(account.getId());
        List<Address> result = addressService.findList(address1);
        jsonMessage.setObject(result);
        return jsonMessage;
    }

    /**
     * 删除用户地址
     *
     * @param addressId
     * @return
     */
    @RequestMapping("delAddress")
    @ResponseBody
    public JsonMessage delAddress(Integer addressId)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        if(addressId == null) return getJsonMessage(1000, "地址不存在！");
        addressService.delete(addressId);
        return jsonMessage;
    }

    /**
     * 获取用户列表
     *
     * @param request
     * @return
     */
    @RequestMapping("addressList")
    @ResponseBody
    public JsonMessage addressList(HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null || account.getId() == null) return getJsonMessage(CommonEnums.USER_NOT_LOGIN);
        Address address = new Address();
        address.setAccountId(account.getId());
        List<Address> result = addressService.findList(address);
        jsonMessage.setObject(result);
        return jsonMessage;
    }


}
