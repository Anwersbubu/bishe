package com.yj.bishe.demo.service;

import com.yj.bishe.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.bishe.demo.vo.JsonResult;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public interface IUserService extends IService<User> {

    //注册
    public JsonResult userRegistered(User user);

    //登陆
    public JsonResult userLogin(Integer uphone, String upassword, HttpSession session);

}
