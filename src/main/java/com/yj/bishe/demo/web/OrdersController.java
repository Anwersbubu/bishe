package com.yj.bishe.demo.web;


import com.alibaba.fastjson.JSON;
import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.IListingsService;
import com.yj.bishe.demo.service.IOrdersService;
import com.yj.bishe.demo.vo.JsonResult;
import com.yj.bishe.demo.vo.orderVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @Resource
    IListingsService listingsService;

    //去到订单详情页面
    @RequestMapping("/users/orderdetails")
    public String toOrderDetails(){ return "orderdata"; }

    //去到支付确认页面
    @RequestMapping("/users/ordertopay")
    public String orderToPay(){ return "ordertopay"; }

    //去到用户订单列表页面
    @RequestMapping("/users/listorder")
    public String toListOrer(){ return "orderlist";}

    //去到用户订单列表页面
    @RequestMapping("/admin/listorder")
    public String adminListOrer(){ return "adminorderlist";}

    //获取用户的订单
    @RequestMapping("/users/myorder")
    @ResponseBody
    public orderVo getMyOrders(int page, int limit, HttpServletRequest request){
        User usersession = (User) request.getSession().getAttribute("usersession");
        return orderService.getOrdersBy(usersession.getUshenf(),usersession.getUid(), page - 1, limit);
    }

    //获取用户的订单
    @RequestMapping("/admin/orders")
    @ResponseBody
    public orderVo adminOrders(int page, int limit, HttpServletRequest request){
        User usersession = (User) request.getSession().getAttribute("usersession");
        return orderService.getOrdersBy(usersession.getUshenf(),usersession.getUid(), page - 1, limit);
    }

    //获取房源信息生成订单
    @PostMapping("/users/getlistbyid")
    @ResponseBody
    public JsonResult getListByLid(int id, HttpSession session){
        JsonResult result = listingsService.listDataByLid(id);
        return result;
    }

    //获取订单详情
    @PostMapping("/users/showorder")
    @ResponseBody
    public JsonResult showOrderByOid(int oid){
        return orderService.getOrderDataById(oid);
    }

}
