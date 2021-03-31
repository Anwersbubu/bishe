package com.yj.bishe.demo.web;


import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.IListingsService;
import com.yj.bishe.demo.vo.JsonResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
public class ListingsController {

    @Resource
    IListingsService listingsService;

    //进入房源页
    @RequestMapping("/listings")
    public String listLings(){
        return "listings";
    }

    //首页房源详细信息显示,先用listdetails进入房源信息页面，再用接口接收lid显示出信息
    @GetMapping("/listdetails")
    public String listDetails(){
        return "listdata";
    }

    @PostMapping("/homedata")
    @ResponseBody
    public JsonResult listDataByLid(int lid){
        return listingsService.listDataByLid(lid);
    }

    //关键字查询先通过高德将关键字所在地区查询出来到街道级，
    //然后再通过查询出的地址到自己的数据库中查出对应的地址ID，再通过地址ID到房源表查询房源
    @PostMapping("/listingsbyaid")
    @ResponseBody
    public JsonResult searchByKey(int key){
        return listingsService.searchListingsByAid(key);
    }

    //房源推荐:先通过前端传过来的地址信息在地址表找出对应的aid然后通过aid在收藏表里筛选出aid下的前7个房源
    @PostMapping("/recommed")
    @ResponseBody
    public JsonResult recommedListings(String city, String town){
        //通过城市和街道访问地址查询接口在地址表里得到aid，再用aid在收藏表里进行条件分类查询得到降序的uid集合，
        // 取前七个，查询出房源信息用Map包装成JSON数据传给前端
        return listingsService.recommedListingsByAddress(city, town);
    }

}
