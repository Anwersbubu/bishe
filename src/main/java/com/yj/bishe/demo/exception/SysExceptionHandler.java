package com.yj.bishe.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常捕获
 * @author 大大大
 *
 */
@ControllerAdvice
public class SysExceptionHandler{

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Object errorHandler(HttpServletRequest request,HttpServletResponse response,Exception ex) {
//		ex.printStackTrace();
		if(request.getRequestURI().endsWith("test2")){
			System.out.println("ajax");
			JsonResult ret = new JsonResult(false, "ajax报错了");
			ret.setData("info", ex.getMessage());
			return ret;
		}else {
			//带数据跳转
			ModelAndView mv = new ModelAndView();
			mv.addObject("url", request.getRequestURI());
			mv.addObject("msg", ex.getMessage());
			mv.setViewName("404.html");
			return mv;
		}
	}
	
}
