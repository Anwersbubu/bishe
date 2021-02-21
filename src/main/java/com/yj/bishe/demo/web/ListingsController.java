package com.yj.bishe.demo.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yj.bishe.demo.service.IListingsService;
import com.yj.bishe.demo.util.GaoDeApi;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ListingsController {

    //高德地图web调用的密钥
    final String key = "36a678df773856fff019cc42ebfc80c0";

    @Resource
    IListingsService listingsService;

    @RequestMapping("/listings")
    public String listLings(){
        return "listings";
    }

    //关键字查询先通过高德将关键字所在地区查询出来到街道级，
    //然后再通过查询出的地址到自己的数据库中查出对应的地址ID，再通过地址ID到房源表查询房源
    @PostMapping("/listingsbyaid")
    @ResponseBody
    public JsonResult searchByKey(int key){
        return listingsService.searchListingsByAid(key);
    }

}
