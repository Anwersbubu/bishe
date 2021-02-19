package com.yj.bishe.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yj.bishe.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 拦截器一
 * 登录验证
 * 未登录的用户只可以查询操作
 * @author 大大大
 *
 */
@Component
public class OneInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("usersession");
		if(user!=null) {
			return true;
		}else {
			response.sendRedirect("http://localhost:8080/index");
			return false;
		}
	}
	
}
