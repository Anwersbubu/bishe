package com.yj.bishe.demo.vo;

import java.util.HashMap;

/**
 * 做统一返回值的
 * @author 大大大
 *
 */
public class JsonResult {

	boolean success;
	String message;
	HashMap<String,Object> data = new HashMap<>();
	
	
	
//	public JsonResult() {}
	public JsonResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HashMap<String, Object> getData() {
		return data;
	}
	public void setData(String key, Object value) {
		this.data.put(key, value);
	}
	@Override
	public String toString() {
		return "JsonResult [success=" + success + ", message=" + message + ", data=" + data + "]";
	}
	
	
	
}
