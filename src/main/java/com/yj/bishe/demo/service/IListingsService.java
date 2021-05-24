package com.yj.bishe.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.bishe.demo.entity.Listings;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.bishe.demo.vo.JsonResult;
import com.yj.bishe.demo.vo.listVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public interface IListingsService extends IService<Listings> {

    //关键字查询,searchtype是用来排序的，-1、0、1分别对应降序、默认、升序，
    JsonResult searchListingsByAid(int aid,int searchtype, int page);

    //推荐房源
    JsonResult recommedListingsByAddress(String city, String street);

    //房源信息显示
    JsonResult listDataByLid(int lid);

    //获取总页数
    JsonResult getCountPage(int aid);

    //分类查询
    JsonResult fenleichaxun(int aid, int page, String hprice, String hsize, String hfoo, String htow, String hdeco, String hfea, String hgeju);

    //发布房源
    JsonResult addList(Listings listings, String laddress, HttpSession session);

    //获取用户的发布房源列表
    listVo getListsByUid(Page<Listings> page, int uid, int ushengf);

    //下架房源
    JsonResult downListByLid(int lid, int uid);

}
