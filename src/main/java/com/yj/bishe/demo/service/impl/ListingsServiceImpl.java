package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.bishe.demo.dao.*;
import com.yj.bishe.demo.entity.*;
import com.yj.bishe.demo.service.IListingsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
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
    public JsonResult searchListingsByAid(int aid, int searchtype, int page) {
        JsonResult ret;
        QueryWrapper<Listings> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("aid", aid);
        wrapper2.eq("lstat", "空闲");
        switch (searchtype) {
            case -1:
                wrapper2.orderByDesc("lprice");
                break;
            case 1:
                wrapper2.orderByAsc("lprice");
                break;
            default:
                break;
        }
        Page<Listings> pageHelp = new Page<>(page, 10, false);
        IPage<Listings> listingsIPage = listingsMapper.selectPage(pageHelp, wrapper2);
        if (listingsIPage.getSize() > 0) {
            List<Listings> listingsList = listingsIPage.getRecords();
            ret = new JsonResult(true, "关键字查询到房源信息");
            List<String> addressList = new ArrayList<>();
            for (Listings list : listingsList) {
                addressList.add(addressMapper.selectById(list.getAid()).show());
            }
            ret.setData("addresss", addressList);
            ret.setData("listings", listingsList);
        } else {
            ret = new JsonResult(false, "关键字所在地区无房源");
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
                wrapper.eq("lid", lid).eq("lstat", "空闲");
                Listings listings = listingsMapper.selectOne(wrapper);
                if (listings != null)
                    recommedList.add(listings);
            }
            ret = new JsonResult(true, "定位地区推荐房源成功");
            ret.setData("List", recommedList);
        } else {
            ret = new JsonResult(false, "定位地址异常报错");
        }
        return ret;
    }

    @Override
    public JsonResult listDataByLid(int lid) {
        JsonResult ret;
        QueryWrapper<Listings> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("lid", lid);
        wrapper1.eq("lstat", "空闲");
        Listings list = listingsMapper.selectOne(wrapper1);
        ret = new JsonResult(true, "房源信息查询成功");
        if (list != null && list.getAid() != null && list.getUid() != null) {
            Address address = addressMapper.selectById(list.getAid());
            String uphone = userMapper.selectById(list.getUid()).getUphone();
            ret.setData("cell", uphone);
            QueryWrapper<Pano> panwrapper = new QueryWrapper<>();
            panwrapper.eq("lid", lid);
            Pano pano = panoMapper.selectOne(panwrapper);
            if (pano != null) {
                String panoPimg = pano.getPimg();
                ret.setData("pimg", panoPimg);
            }
            if (address != null) {
                QueryWrapper<Addressinfo> wrapper = new QueryWrapper<>();
                wrapper.eq("aid", list.getAid());
                Addressinfo addressinfo = addressinfoMapper.selectOne(wrapper);
                if (addressinfo != null) {
                    ret.setData("adress", address.show() + addressinfo.show());
                } else {
                    ret.setData("adress", address.show());
                }
            } else {
                ret.setData("adress", null);
            }
            ret.setData("list", list);
        } else
            ret = new JsonResult(false, "房源信息查询失败");
        return ret;
    }

    @Override
    public JsonResult getCountPage(int aid) {
        JsonResult ret;
        if (aid > 0) {
            QueryWrapper<Listings> wrapper = new QueryWrapper<>();
            wrapper.eq("aid", aid);
            wrapper.eq("lstat", "空闲");
            Integer count = listingsMapper.selectCount(wrapper);
            if (count > 0) {
                ret = new JsonResult(true, "当前地区下的房源总数成功获取");
                ret.setData("count", count);
            } else ret = new JsonResult(false, "当前地区下没有房源");
        } else ret = new JsonResult(false, "地址信息空白");
        return ret;
    }

    @Override
    public JsonResult fenleichaxun(int aid, int page, String hprice, String hsize, String hfoo, String htow, String hdeco, String hfea, String hgeju) {
        JsonResult ret;
        if (aid > 0) {
            QueryWrapper<Listings> wrapper = new QueryWrapper<>();
            wrapper.eq("lstat", "空闲");
            System.out.println(hprice + hsize + hfoo + htow + hdeco + hgeju);
            if (!hprice.equals("asc")) wrapper.orderByAsc("lprice");
            else wrapper.orderByDesc("lprice");
            if (!hsize.equals("asc")) wrapper.orderByAsc("lsize");
            else wrapper.orderByDesc("lprice");
            if (!hfoo.equals("默认")) wrapper.eq("lfoo", hfoo);
            if (!htow.equals("默认")) wrapper.eq("ltow", htow);
            if (!hdeco.equals("默认")) wrapper.eq("ldeco", hdeco);
            if (!hgeju.equals("")) wrapper.like("lparttern", hgeju);
            Page<Listings> p = new Page<>(page, 10, false);
            List<Listings> listingsList = listingsMapper.selectPage(p, wrapper).getRecords();
            if (listingsList != null && listingsList.size() > 0) {
                ret = new JsonResult(true, "分类查询成功");
                List<String> addressList = new ArrayList<>();
                for (Listings l : listingsList) {
                    addressList.add(addressMapper.selectById(l.getAid()).show());
                }
                //获取查询的记录总条数
                Integer count = listingsMapper.selectCount(wrapper);
                ret.setData("num", count);
                ret.setData("listingsList", listingsList);
                ret.setData("addressList", addressList);
            } else ret = new JsonResult(false, "分类查询失败,数据不全");
        } else ret = new JsonResult(false, "地址信息错误");
        return ret;
    }

    @Override
    public JsonResult addList(Listings listings, String laddress, HttpSession session) {
        JsonResult ret;
        if (!laddress.equals("")) {
            StringBuilder address = new StringBuilder(laddress);
            String country, pravonice, city, area, street;
            int coun = address.indexOf("国");
            int prav = address.indexOf("省");
            int ct = address.indexOf("市");
            int ar = address.indexOf("区");
            int st = address.indexOf("街道");
            if (coun != -1 && prav != -1 && ct != -1 && ar != -1 && st != -1) {
                int flag = 0;
                QueryWrapper<Address> wrapper = new QueryWrapper<>();
                country = address.substring(0, coun + 1);
                pravonice = address.substring(coun + 1, prav + 1);
                city = address.substring(prav + 1, ct + 1);
                area = address.substring(ct + 1, ar + 1);
                street = address.substring(ar + 1, st + 2);
                if (!country.equals("") && !country.equals("国")) {
                    wrapper.eq("country", country);
                    flag++;
                }
                if (!pravonice.equals("") && !pravonice.equals("省")) {
                    wrapper.eq("province", pravonice);
                    flag++;
                }
                if (!city.equals("") && !city.equals("市")) {
                    wrapper.eq("city", city);
                    flag++;
                }
                if (!area.equals("") && !area.equals("区")) {
                    wrapper.eq("area", area);
                    flag++;
                }
                if (!street.equals("") && !street.equals("街道")) {
                    wrapper.eq("street", street);
                    flag++;
                }
                if (flag > 0) {//地址格式正常
                    User usersession = (User) session.getAttribute("usersession");
                    listings.setUid(usersession.getUid());
                    listings.setUname(usersession.getUname());
                    listings.setLtime(LocalDateTime.now());
                    listings.setLstat("空闲");
                    Address selectAddress = addressMapper.selectOne(wrapper);
                    if (selectAddress != null) {//数据库有地址信息
                        listings.setAid(selectAddress.getAid());
                    } else if (flag == 5) {//新地址添加
                        Address newAddress = new Address(country, pravonice, city, area, street);
                        int insert = addressMapper.insert(newAddress);
                        if (insert > 0)
                            listings.setAid(newAddress.getAid());
                    }
                    //开始房源添加操作
                    if (listings.getAid() > 0) {
                        int insertList = listingsMapper.insert(listings);
                        if (insertList > 0) {
                            ret = new JsonResult(true, "房源添加成功");
                            ret.setData("lid", listings.getLid());
                        } else ret = new JsonResult(false, "房源添加异常");
                    } else ret = new JsonResult(false, "地址添加操作异常");
                } else ret = new JsonResult(false, "房源添加失败，地址异常");
            } else ret = new JsonResult(false, "房源添加失败，地址异常");
        } else ret = new JsonResult(false, "房源添加失败，地址异常");
        return ret;
    }

}
