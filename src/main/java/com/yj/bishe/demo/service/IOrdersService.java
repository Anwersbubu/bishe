package com.yj.bishe.demo.service;

import com.yj.bishe.demo.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.bishe.demo.vo.JsonResult;
import com.yj.bishe.demo.vo.orderVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public interface IOrdersService extends IService<Orders> {

    //获取用户的历史订单
    orderVo getOrdersBy(int ushengf,int uid,int page, int limit);

    //获取订单详细信息
    JsonResult getOrderDataById(int oid);

}
