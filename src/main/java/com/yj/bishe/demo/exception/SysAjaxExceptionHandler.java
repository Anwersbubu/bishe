package com.yj.bishe.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class SysAjaxExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public JsonResult errorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		ex.printStackTrace();
		
		JsonResult ret = new JsonResult(false, "ajax报错了");
		ret.setData("info", ex.getMessage());
		
		return ret;
	}
	
}
