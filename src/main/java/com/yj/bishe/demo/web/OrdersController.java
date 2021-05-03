package com.yj.bishe.demo.web;


import com.alibaba.fastjson.JSON;
import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.IOrdersService;
import com.yj.bishe.demo.vo.orderVo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Controller
public class OrdersController {

    @Resource
    IOrdersService orderService;

    @RequestMapping("/users/orderdetails")
    public String toOrderDetails(){ return "orderdata"; }

    @RequestMapping("/users/listorder")
    public String toListOrer(){ return "orderlist";}

    //获取用户的订单
    @RequestMapping("/users/myorder")
    @ResponseBody
    public orderVo getMyOrders(int page, int limit, HttpServletRequest request){
        User usersession = (User) request.getSession().getAttribute("usersession");
        return orderService.getOrdersBy(usersession.getUid(), page - 1, limit);
    }

}
