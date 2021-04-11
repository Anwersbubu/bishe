package com.yj.bishe.demo.service;

import com.yj.bishe.demo.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.bishe.demo.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public interface IAddressService extends IService<Address> {

    //关键字查询
    JsonResult searchAidByKeyword(String city, String area, String street);

    //租房按钮
    JsonResult zufangSer(String city, String street);

    //通过aid得到地址
    JsonResult aidToAddress(int aid);

    //获取表中全部国家信息
    JsonResult getAllCountry();

    //根据国家获取省份
    JsonResult getAllProvniceByCountry(String country);

    //根据国家和省份获取城市
    JsonResult getCityByCountryProv(String country, String province);

    //根据国家、省份和城市获取区
    JsonResult getAreaByCounPrpCt(String country, String province, String city);

    //根据国家、省份、城市和区获取街道
    JsonResult getStreetByCounProCtAr(String country, String province, String city, String area);

}
