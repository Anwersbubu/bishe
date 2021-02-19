package com.yj.bishe.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yj.bishe.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 拦截器二
 * 普通用户和管理员的拦截
 * @author 大大大
 *
 */
@Component
public class TwoInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("usersession");
		if(user!=null) {
			Integer ushenf = user.getUshenf();
			if(ushenf == 1) {
				return true;
			}
			else {
				response.sendRedirect("http://localhost:8080/index");
				return false;
			}
		}else {
			response.sendRedirect("http://localhost:8080/index");
			return false;
		}
	}
	
}
