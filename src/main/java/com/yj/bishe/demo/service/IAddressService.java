package com.yj.bishe.demo.service;

import com.yj.bishe.demo.entity.Address;
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
public interface IAddressService extends IService<Address> {

    //关键字查询
    JsonResult searchAidByKeyword(String city, String area, String street);

    //租房按钮
    JsonResult zufangSer(String city, String street);

}
