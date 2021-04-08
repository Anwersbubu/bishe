package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.bishe.demo.dao.*;
import com.yj.bishe.demo.entity.Address;
import com.yj.bishe.demo.entity.Addressinfo;
import com.yj.bishe.demo.entity.Listings;
import com.yj.bishe.demo.entity.Pano;
import com.yj.bishe.demo.service.IListingsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Service
public class ListingsServiceImpl extends ServiceImpl<ListingsMapper, Listings> implements IListingsService {

    @Resource
    ListingsMapper listingsMapper;

    @Resource
    AddressMapper addressMapper;

    @Resource
    AddressinfoMapper addressinfoMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    FavoMapper favoMapper;

    @Resource
    PanoMapper panoMapper;

    //关键字查询
    @Override
    public JsonResult searchListingsByAid(int aid) {
        JsonResult ret;
            QueryWrapper<Listings> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("aid",aid);
            List<Listings> listingsList = listingsMapper.selectList(wrapper2);
            if (listingsList.size() > 0){
                ret = new JsonResult(true,"关键字查询到房源信息");
                Map<Integer, Listings> listingsMap = listingsList.stream().collect(Collectors.toMap(Listings::getLid, Function.identity()));
                ret.setData("listings",listingsMap);
            }else {
                ret = new JsonResult(false,"关键字所在地区无房源");
            }
        return ret;
    }

    @Override
    public JsonResult recommedListingsByAddress(String city, String street) {
        JsonResult ret;
        List<Listings> recommedList;
        List<Integer> lidList = favoMapper.recommed7Lid(city, street);
        if (lidList != null) {
            recommedList = new ArrayList<>();
            for (Integer lid : lidList) {
                QueryWrapper<Listings> wrapper = new QueryWrapper<>();
                wrapper.eq("lid",lid).eq("lstat","空闲");
                Listings listings = listingsMapper.selectOne(wrapper);
                recommedList.add(listings);
            }
            ret = new JsonResult(true,"定位地区推荐房源成功");
            ret.setData("List",recommedList);
        }else {
            ret = new JsonResult(false,"定位地址异常报错");
        }
        return ret;
    }

    @Override
    public JsonResult listDataByLid(int lid) {
        JsonResult ret;
        Listings list = listingsMapper.selectById(lid);
        ret = new JsonResult(true,"房源信息查询成功");
        if (list != null && list.getAid() != null && list.getUid() != null){
            Address address = addressMapper.selectById(list.getAid());
            String uphone = userMapper.selectById(list.getUid()).getUphone();
            ret.setData("cell",uphone);
            QueryWrapper<Pano> panwrapper = new QueryWrapper<>();
            panwrapper.eq("lid",lid);
            Pano pano = panoMapper.selectOne(panwrapper);
            if (pano != null){
                String panoPimg = pano.getPimg();
                if (panoPimg != null){
                    ret.setData("pimg",panoPimg);
                }else ret.setData("pimg",null);
            }
            if (address != null ){
                QueryWrapper<Addressinfo> wrapper = new QueryWrapper<>();
                wrapper.eq("aid",list.getAid());
                Addressinfo addressinfo = addressinfoMapper.selectOne(wrapper);
                if (addressinfo != null){
                    ret.setData("adress",address.show()+addressinfo.show());
                }else {
                    ret.setData("adress",address.show());
                }
            }else {
                ret.setData("adress",null);
            }
            ret.setData("list",list);
        }else
            ret = new JsonResult(false,"房源信息查询失败");
        return ret;
    }

}
