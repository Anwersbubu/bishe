package com.yj.bishe.demo.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yj.bishe.demo.entity.Orders;
import com.yj.bishe.demo.service.AlipayService;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayController {
	
	    @Resource
		AlipayService alipayService;
	    //用于请求首页显示支付付款功能
	    @RequestMapping("/payindex")
	    public String hello(){
	        return "ayindex";
	    }
	   
	    //支付成功后的异步回调，用于处理服务端业务
	    @RequestMapping("/notify_url")
	    public void notifyUrl(HttpServletRequest request)throws Exception{
	    	System.out.println("异步回调");
	        alipayService.notifyUrl(request);
	    }
	 
	    //支付成功后同步回调，用于展示给用户查看
	    @RequestMapping("/return_url")
	    public void returnUrl(HttpServletRequest request, HttpServletResponse response)throws Exception{
	        System.out.println("同步回调");
			alipayService.returnUrl(request,response);
//	        return ;
	    }
	 
	    //用户点击付款后请求此方法
	    @RequestMapping("/users/alipayTradePagePay")
		@ResponseBody
	    public JsonResult alipayTradePagePay(HttpServletRequest request, HttpServletResponse response, int lid, int time, int money) throws Exception{
			JsonResult jsonResult = new JsonResult(true,"支付结果");
			jsonResult.setData("result",alipayService.alipayTradePagePay(request,response,lid,time,money));
	       return jsonResult;
	    }


}
