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
    public String indexHtml(){
        return "index";
    }

    @RequestMapping("/dlzc")
    public String dlzcHtml(){
        return "dlzc";
    }

    @RequestMapping("/denglu")//进入登陆页
    public String dengluHtml(){
        return "denglu";
    }

    @RequestMapping("/zhuce")//进入登陆页
    public String zhuceHtml(){
        return "zhuce";
    }

    @RequestMapping("/gerenzx")//进入个人中心页
    public String gerenzxHtml(){
        return "gerenzx";
    }

    @PostMapping("/registered")//注册
    @ResponseBody
    public JsonResult userRegistered(String uname, int uphone, String password, HttpSession session){
        User user = new User();
        user.setUname(uname);
        user.setUphone(uphone);
        user.setUpassword(password);
        user.setUshenf(0);
        return userService.userRegistered(user,session);
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

    @PostMapping("/admin/update")//管理员更新用户信息
    @ResponseBody
    public JsonResult adminUpdateUser(User user){
        return userService.adminUpdateUser(user);
    }

    @PostMapping("/admin/queryuser")//管理员通过uid或uphone查询用户信息
    @ResponseBody
    public JsonResult adminQueryUserByUid2uphone(int uid2phone){
        return userService.queryUserByUid2phone(uid2phone);
    }

    @RequestMapping("/users/index")
    public String helloHtml(Map<String,Object> map){
        map.put("hello","欢迎进入HTML页面");
        return "index";
    }

}
