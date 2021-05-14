package com.yj.bishe.demo.service;

import com.yj.bishe.demo.entity.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface AlipayService {
	
	public void notifyUrl(HttpServletRequest request)throws Exception;
	
	public String alipayTradePagePay(HttpServletRequest request , HttpServletResponse response, int lid, int time, int money) throws Exception;
	
	public void  returnUrl(HttpServletRequest request, HttpServletResponse response)throws Exception;

}
