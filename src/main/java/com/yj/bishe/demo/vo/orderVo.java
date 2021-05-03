package com.yj.bishe.demo.vo;

import java.util.List;
import java.util.Map;

public class orderVo {

    private int code;

    private String msg;

    private int count;

    private List<Map<String,Object>> data;

    public orderVo(int code, String msg, int count) {
        this.code = code;
        this.msg = msg;
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Map<String,Object>> getData() {
        return data;
    }

    public void setData(List<Map<String,Object>> data) {
        this.data = data;
    }
}
