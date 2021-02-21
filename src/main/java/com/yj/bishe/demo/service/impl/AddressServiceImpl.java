package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.bishe.demo.entity.Address;
import com.yj.bishe.demo.dao.AddressMapper;
import com.yj.bishe.demo.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
