package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.bishe.demo.entity.Address;
import com.yj.bishe.demo.dao.AddressMapper;
import com.yj.bishe.demo.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Resource
    AddressMapper addressMapper;

    @Override
    public JsonResult searchAidByKeyword(String city, String area, String street) {
        JsonResult ret;
        Integer aid;
        QueryWrapper<Address> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("city",city);
        wrapper1.eq("area",area);
        wrapper1.eq("street",street);
        Address address = addressMapper.selectOne(wrapper1);
        if (address != null){
            ret = new JsonResult(true,"关键字所在地区查询成功");
            aid = address.getAid();
            ret.setData("aid",aid);
        }else
            ret = new JsonResult(false,"关键字所在地区查询失败");
        return ret;
    }

    @Override
    public JsonResult zufangSer(String city, String street) {
        JsonResult ret;
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("city",city);
        wrapper.eq("street",street);
        Address address = addressMapper.selectOne(wrapper);
        if (address != null){
            ret = new JsonResult(true,"租房按钮正常");
            ret.setData("aid",address.getAid());
        }else {
            ret = new JsonResult(false,"租房按钮不正常");
        }
        return ret;
    }

    @Override
    public JsonResult aidToAddress(int aid) {
        JsonResult ret;
        Address address = addressMapper.selectById(aid);
        if (address != null){
            ret = new JsonResult(true,"地址转换完成");
            ret.setData("address",address.show());
        }else
            ret = new JsonResult(false,"地址转换失败");
        return ret;
    }

    @Override
    public JsonResult getAllCountry() {
        JsonResult ret;
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.select("distinct(country)");
        List<Address> countryList = addressMapper.selectList(wrapper);
        if (countryList != null){
            ret = new JsonResult(true,"地址信息拿到");
            ret.setData("countryList",countryList);
        }else ret = new JsonResult(false,"地址信息拿去失败");
        return ret;
    }

    @Override
    public JsonResult getAllProvniceByCountry(String country) {
        JsonResult ret;
        if (country != null && !country.equals("选择国家")){
         QueryWrapper<Address> wrapper = new QueryWrapper<>();
         wrapper.eq("country",country);
         wrapper.select("distinct(province)");
         List<Address> provinceList = addressMapper.selectList(wrapper);
         if (provinceList != null){
             ret = new JsonResult(true,"获取"+country+"下的省份成功");
             ret.setData("provinceList",provinceList);
         }else
             ret = new JsonResult(false,"获取"+country+"下的省份失败");
        }else
            ret = new JsonResult(false,"没有获取到国家");
        return ret;
    }

    @Override
    public JsonResult getCityByCountryProv(String country, String province) {
        JsonResult ret;
        if (!country.equals("选择国家") && !province.equals("选择省份")){
            QueryWrapper<Address> wrapper = new QueryWrapper<>();
            wrapper.eq("country",country);
            wrapper.eq("province",province);
            wrapper.select("distinct(city)");
            List<Address> cityList = addressMapper.selectList(wrapper);
            if (cityList != null){
                ret = new JsonResult(true,"获取"+country+province+"下的城市成功");
                ret.setData("cityList",cityList);
            }else
                ret = new JsonResult(false,"获取"+country+province+"下的城市失败");
        }else
            ret = new JsonResult(false,"没有获取到国家和省份");
        return ret;
    }

    @Override
    public JsonResult getAreaByCounPrpCt(String country, String province, String city) {
        JsonResult ret;
        if (!country.equals("选择国家") && !province.equals("选择省份") && !city.equals("选择城市")){
            QueryWrapper<Address> wrapper = new QueryWrapper<>();
            wrapper.eq("country",country);
            wrapper.eq("province",province);
            wrapper.eq("city",city);
            wrapper.select("distinct(area)");
            List<Address> areaList = addressMapper.selectList(wrapper);
            if (areaList != null){
                ret = new JsonResult(true,"获取"+country+province+city+"下的城市成功");
                ret.setData("areaList",areaList);
            }else
                ret = new JsonResult(false,"获取"+country+province+city+"下的城市失败");
        }else
            ret = new JsonResult(false,"没有获取到国家、省份和城市");
        return ret;
    }

    @Override
    public JsonResult getStreetByCounProCtAr(String country, String province, String city, String area) {
        JsonResult ret;
        if (!country.equals("选择国家") && !province.equals("选择省份") && !city.equals("选择城市") && !area.equals("选择区")){
            QueryWrapper<Address> wrapper = new QueryWrapper<>();
            wrapper.eq("country",country);
            wrapper.eq("province",province);
            wrapper.eq("city",city);
            wrapper.eq("area",area);
            wrapper.select("distinct(street)");
            List<Address> streetList = addressMapper.selectList(wrapper);
            if (streetList != null){
                ret = new JsonResult(true,"获取"+country+province+city+area+"下的城市成功");
                ret.setData("streetList",streetList);
            }else
                ret = new JsonResult(false,"获取"+country+province+city+area+"下的城市失败");
        }else
            ret = new JsonResult(false,"没有获取到国家、省份、城市和区");
        return ret;
    }
}
