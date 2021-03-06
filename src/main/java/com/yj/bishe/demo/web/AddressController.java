package com.yj.bishe.demo.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yj.bishe.demo.entity.Address;
import com.yj.bishe.demo.service.IAddressService;
import com.yj.bishe.demo.util.GaoDeApi;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Controller
public class AddressController {

    //高德地图web调用的密钥
    final String key = "36a678df773856fff019cc42ebfc80c0";
    //高德逆查询地址路径
    final String seachAddrURL = "https://restapi.amap.com/v3/geocode/regeo";
    //高德搜索POI路径
    final String searchPOIURL = "https://restapi.amap.com/v3/place/text";

    @Resource
    IAddressService addressService;

    //访问更改定位页面
    @RequestMapping("/address")
    public String toAddress(){return "changeAddress";}

    //获取地址表中的国家信息
    @PostMapping("/getcountry")
    @ResponseBody
    public JsonResult getAllCountry(){
        return addressService.getAllCountry();
    }

    //通过国家获取省
    @PostMapping("/getProvince")
    @ResponseBody
    public JsonResult getAllProvince(String country){
        return addressService.getAllProvniceByCountry(country);
    }

    //通过国家和省份获取城市
    @PostMapping("/getCity")
    @ResponseBody
    public JsonResult getAllCity(String country, String province){
        return addressService.getCityByCountryProv(country,province);
    }

    //通过国家、省份和城市获取区
    @PostMapping("/getArea")
    @ResponseBody
    public JsonResult getAllArea(String country, String province, String city){
        return  addressService.getAreaByCounPrpCt(country, province, city);
    }

    //通过国家、省份、城市和区获取街道
    @PostMapping("/getStreet")
    @ResponseBody
    public JsonResult getAllStreet(String country, String province, String city, String area){
        return addressService.getStreetByCounProCtAr(country, province, city, area);
    }

    //根据前端传来的地址经纬度查询出具体地址信息
    @PostMapping("/getaddress")
    @ResponseBody
    public JSONObject getAddressByGaoDe(String param){
        param = "key="+key+"&location="+param;
        String sendGET = GaoDeApi.SendGET(seachAddrURL, param);
        return JSONObject.parseObject(sendGET);
    }

    //关键字查询先通过高德将关键字所在地区查询出来到街道级，
    //然后再通过查询出的地址到自己的数据库中查出对应的地址ID，再通过地址ID到房源表查询房源
    @GetMapping("/search")
    @ResponseBody
    public JsonResult searchByKey(String city, String keywords){
        String param = "key="+key+"&keywords="+keywords+"&types=商务住宅;住宅区;住宅小区&city="+city;
        String sendGET = GaoDeApi.SendGET(searchPOIURL, param);
        JSONArray jsonArray = JSONObject.parseObject(sendGET).getJSONArray("pois");
        String adname = null;
        String street = null;
        boolean flage = false;//遍历查询街道标志
        for (int i=0; i < jsonArray.size() && !flage; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            adname = jsonObject.get("adname").toString();
            street = jsonObject.get("address").toString();
            int endIndexOf = street.indexOf("街道");
            if(endIndexOf != -1){
                street = street.substring(0,endIndexOf+2);
                flage = true;
            }
        }
        if (flage) {
            return addressService.searchAidByKeyword(city, adname, street);
        }else
            return new JsonResult(false,"关键字信息不全");
    }

    //通过aid返回地址字符串
    @PostMapping("/showAddress")
    @ResponseBody
    public JsonResult AidToAddress(int key){
        return addressService.aidToAddress(key);
    }

    //租房按钮根据定位显示房源信息
    @PostMapping("/zufang")
    @ResponseBody
    public JsonResult zufang(String city, String street){
        return addressService.zufangSer(city,street);
    }

}
