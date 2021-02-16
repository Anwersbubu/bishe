package com.yj.bishe.demo.web;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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
//@RequestMapping("//user")
public class UserController {

    @RequestMapping("/hello")
    public String helloHtml(Map<String,Object> map){
        map.put("hello","欢迎进入HTML页面");
        return "/index";
    }

}
