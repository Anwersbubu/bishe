package com.yj.bishe.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yj.bishe.demo.config.AlipayConfig;
import com.yj.bishe.demo.dao.ListingsMapper;
import com.yj.bishe.demo.dao.OrdersMapper;
import com.yj.bishe.demo.entity.Listings;
import com.yj.bishe.demo.entity.Orders;
import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.AlipayService;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

@Service
public class AlipayServiceImpl implements AlipayService {
	
	@Resource
    OrdersMapper ordersMapper;
	@Resource
    ListingsMapper listingsMapper;

    Orders orders = null;

    boolean payflag = false;

    int quanlid;

	@Override
	public void notifyUrl(HttpServletRequest request) throws Exception {
		 //获取支付宝POST过来反馈信息
		System.out.println("post===========");
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
 
        boolean signVerified = AlipaySignature.rsaCheckV2(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        System.out.println("notify"+signVerified);
        //——请在这里编写您的程序（以下代码仅作参考）——
 
	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
 
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
 
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            
            
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
 
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

            }
 
            System.out.println("success");
 
        }else {//验证失败
            System.out.println("fail");
            
 
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
 
        //——请在这里编写您的程序（以上代码仅作参考）——
		
	}

	@Override
	public String alipayTradePagePay(HttpServletRequest request, HttpServletResponse response, int lid, int time, int money) throws Exception {
	    //获得初始化的AlipayClient
        quanlid = lid;
        String result;
        User users=(User) request.getSession().getAttribute("usersession");
       if(users!=null) {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
            //生成订单但先不操作数据库
            orders = new Orders();
            LocalDateTime localDateTime = LocalDateTime.now();
            orders.setUid(users.getUid());
            orders.setLid(lid);
            orders.setDura(String.valueOf(time));
            orders.setMoney(String.valueOf(money));
            orders.setTime(localDateTime);
           int insert = ordersMapper.insert(orders);
           if (insert == 1)
               payflag = true;
           //商户订单号，商户网站订单系统中唯一订单号，必填
           String out_trade_no = orders.getOid().toString();
           //由于同步回调验签无法为true所以这个订单号不知道是否要和数据库保持一致，就先用非一致做一下
//           String out_trade_no = users.getUid().toString()+orders.getTime().toString();
           //付款金额，必填
           String total_amount = orders.getMoney();
           //订单名称，必填
           String subject = "租房";
           //商品描述，可空
           String body = "";
           // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
           String timeout_express = "15m";

           alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                   + "\"total_amount\":\""+ total_amount +"\","
                   + "\"subject\":\""+ subject +"\","
                   + "\"body\":\""+ body +"\","
                   + "\"timeout_express\":\""+ timeout_express +"\","
                   + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
           //请求
           result = alipayClient.pageExecute(alipayRequest).getBody();
//           System.out.println("返回页面"+ result);
        }else {
        	result = "false";
		}
        return result;

		
	}

	@Override
	public void returnUrl(HttpServletRequest request,HttpServletResponse response) throws Exception {
        System.out.println("获取支付宝GET过来反馈信息");
		//获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
      
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
            System.out.println("---------\n"+name+"\n"+valueStr+"\n-----------");
        }
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,AlipayConfig.sign_type);
        System.out.println(signVerified);
        String result;
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(!signVerified) {
//            result = "trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount;
            //判断该笔订单是否在商户网站中已经做过处理
            //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            //如果有做过处理，不执行商户的业务程序
//            int insert = ordersMapper.insert(orders);
            System.out.println("*************"+out_trade_no);
//            if (insert == 1)
//                payflag = true;
            if (payflag){
                Listings listings = listingsMapper.selectById(orders.getLid());
                listings.setLstat("已租");
                int update = listingsMapper.updateById(listings);
                if (update == 1){
                    System.out.println(out_trade_no);
                    result = "orderdata";
                    request.getSession().setAttribute("session",result);
//                    request.getRequestDispatcher("/users/orderdetails?id="+orders.getOid()).forward(request,response);
                    response.sendRedirect("http://localhost:8080/users/orderdetails?id="+out_trade_no);
                }else {
                    System.out.println("同步调回中验签失败");
                    request.getSession().setAttribute("session","验签失败");
                    ordersMapper.deleteById(out_trade_no);
                    response.sendRedirect("http://localhost:8080/users/ordertopay?id="+quanlid);
                    //验签失败，返回首页但退款没有做
                    result = "index";
                }
            }else {
                Orders orders = ordersMapper.selectById(this.orders.getOid());
                if (orders!=null)
                    ordersMapper.deleteById(this.orders.getOid());
                System.out.println("同步调回中验签失败");
                request.getSession().setAttribute("session","验签失败");
                //验签失败，返回首页但退款没有做
                result = "index";
            }
        }else {
            System.out.println("同步调回中验签失败");
            request.getSession().setAttribute("session","验签失败");
            //验签失败，返回首页但退款没有做
            result = "index";
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
//        return result;
    }
	}