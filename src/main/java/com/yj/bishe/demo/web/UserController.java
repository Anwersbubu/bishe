package com.yj.bishe.demo.web;


import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.IUserService;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Controller
public class UserController {

    @Resource
    IUserService userService;

    @RequestMapping("/index")//进入主页
    public String indexHtml(Map<String,Object> map){
        map.put("hello","欢迎进入HTML页面");
        return "index";
    }

    @PostMapping("/registered")//注册
    @ResponseBody
    public JsonResult userRegistered(User user){
        return userService.userRegistered(user);
    }

    @PostMapping("/login")//登陆
    @ResponseBody
    public JsonResult userLogin(Integer up, String pass, HttpSession session){
        return userService.userLogin(up,pass,session);
    }

    @PostMapping("/users/update")//更新用户个人信息
    @ResponseBody
    public JsonResult userUpdate(User user){
        return userService.userUpdateinfo(user);
    }

    @PostMapping("/users/query")//获取当前用户个人信息
    @ResponseBody
    public JsonResult queryUser(HttpSession session){
        JsonResult ret;
        User usersession = (User)session.getAttribute("usersession");
        if(usersession != null){
            ret = new JsonResult(true,"获取当前用户信息成功");
            ret.setData("user",usersession);
        }else {
            ret = new JsonResult(false,"获取当前用户信息失败");
        }
        return ret;
    }

    @RequestMapping("/users/index")
    public String helloHtml(Map<String,Object> map){
        map.put("hello","欢迎进入HTML页面");
        return "index";
    }

}
