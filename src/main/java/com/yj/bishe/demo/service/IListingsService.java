package com.yj.bishe.demo.service;

import com.yj.bishe.demo.entity.Listings;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.bishe.demo.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public interface IListingsService extends IService<Listings> {

    //关键字查询
    JsonResult searchListingsByAid(int aid);

}
