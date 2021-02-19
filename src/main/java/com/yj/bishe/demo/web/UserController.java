package com.yj.bishe.demo.web;


import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
//@RequestMapping("/users")
public class UserController {

    @RequestMapping("/users/index")
    public String helloHtml(Map<String,Object> map){
        map.put("hello","欢迎进入HTML页面");
        return "index";
    }

    @RequestMapping("/index")
    public String indexHtml(Map<String,Object> map){
        map.put("hello","欢迎进入HTML页面");
        return "index";
    }

    @PostMapping("/testajax")
    @ResponseBody
    public JsonResult testAjax(String test){
        JsonResult json = new JsonResult(false,"OK");
        json.setData("date",test);
        return json;
    }

}
