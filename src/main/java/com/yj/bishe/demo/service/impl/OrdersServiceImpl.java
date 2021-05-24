package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.bishe.demo.dao.AddressMapper;
import com.yj.bishe.demo.dao.ListingsMapper;
import com.yj.bishe.demo.dao.UserMapper;
import com.yj.bishe.demo.entity.Address;
import com.yj.bishe.demo.entity.Listings;
import com.yj.bishe.demo.entity.Orders;
import com.yj.bishe.demo.dao.OrdersMapper;
import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import com.yj.bishe.demo.vo.orderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Resource
    OrdersMapper orderMapper;

    @Resource
    ListingsMapper listingsMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    AddressMapper addressMapper;

    @Override
    public orderVo getOrdersBy(int ushengf, int uid,int page, int limit) {
        orderVo ret;
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        if (ushengf == 1);
        else
        wrapper.eq("uid",uid);
        Page<Orders> pages = new Page<>(page,limit);
        List<Orders> ordersList = orderMapper.selectPage(pages, wrapper).getRecords();
            if (ordersList.size() > 0) {
                List<Map<String, Object>> dataList = new ArrayList<>();
                for (Orders o : ordersList) {
                    Map<String, Object> dataMap = new HashMap<>();
                    int lid = o.getLid();
                    Listings listings = listingsMapper.selectById(lid);
                    User user = userMapper.selectById(listings.getUid());
                    dataMap.put("oid", o.getOid());
                    dataMap.put("lid", lid);
                    dataMap.put("lname", listings.getLname());
                    dataMap.put("laddress", addressMapper.selectById(listings.getAid()).show());
                    dataMap.put("uname", user.getUname());
                    dataMap.put("uphone", user.getUphone());
                    dataMap.put("dura", o.getDura());
                    dataMap.put("money", o.getMoney());
                    dataMap.put("time", o.getTime().toLocalDate());
                    dataList.add(dataMap);
                }
                ret = new orderVo(0, "用户历史订单生成完毕", orderMapper.selectCount(wrapper));
                ret.setData(dataList);
            } else ret = new orderVo(1, "用户没有订单信息", 0);
        return ret;
    }

    @Override
    public JsonResult getOrderDataById(int oid) {
        JsonResult ret;
        if (oid>0){
            System.out.println(oid);
            Orders orders = orderMapper.selectById(oid);
            if (orders != null){
                Integer lid = orders.getLid();
                Integer uid = orders.getUid();
                Listings listings = listingsMapper.selectById(lid);
                User user = userMapper.selectById(uid);
                user.setUpassword("保密");
                Address address = addressMapper.selectById(listings.getLid());
                ret = new JsonResult(true,"获取成功");
                ret.setData("orderdata",orders);
                ret.setData("lists",listings);
                ret.setData("addressinfo",address.show());
                ret.setData("user",user);
            }else
                ret = new JsonResult(false,"未有给条订单记录");
        }else {
            ret = new JsonResult(false,"为接收到请求数据参数");
        }
        return ret;
    }
}
