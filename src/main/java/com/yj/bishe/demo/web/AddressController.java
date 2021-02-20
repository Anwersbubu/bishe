package com.yj.bishe.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.yj.bishe.demo.util.GaoDeApi;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Controller
//@RequestMapping("//address")
public class AddressController {

    //高德地图web调用的密钥
    final String key = "36a678df773856fff019cc42ebfc80c0";

    //根据前端传来的地址经纬度查询出具体地址信息
    @PostMapping("/getaddress")
    @ResponseBody
    public JSONObject getAddressByGaoDe(String URL,String param){
        JsonResult ret;
        param = "key="+key+"&location="+param;
        String sendGET = GaoDeApi.SendGET(URL, param);
        JSONObject jsonObject = JSONObject.parseObject(sendGET);
        return jsonObject;
    }

    //关键字查询





}
