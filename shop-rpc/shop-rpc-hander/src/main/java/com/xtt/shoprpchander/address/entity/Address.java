package com.xtt.shoprpchander.address.entity;

import java.io.Serializable;

public class Address implements Serializable
{
    private static final long serialVersionUID = -8473982204366796312L;
    private Integer id;

    private Integer accountId;

    private String consignee;

    private String province;

    private String city;

    private String district;

    private String detailAddress;

    private String zipcode;

    private String telephone;

    private Integer flag;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Integer accountId)
    {
        this.accountId = accountId;
    }

    public String getConsignee()
    {
        return consignee;
    }

    public void setConsignee(String consignee)
    {
        this.consignee = consignee;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getDetailAddress()
    {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress)
    {
        this.detailAddress = detailAddress;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public Integer getFlag()
    {
        return flag;
    }

    public void setFlag(Integer flag)
    {
        this.flag = flag;
    }
}