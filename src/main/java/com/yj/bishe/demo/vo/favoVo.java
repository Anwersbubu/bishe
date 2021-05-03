package com.yj.bishe.demo.vo;

import com.yj.bishe.demo.entity.Favo;
import com.yj.bishe.demo.entity.Listings;

import java.util.List;

public class favoVo {

    private int code;

    private String msg;

    private int count;

    private List<Favo> data;

    public favoVo(int code, String msg, int count) {
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

    public List<Favo> getData() {
        return data;
    }

    public void setData(List<Favo> data) {
        this.data = data;
    }
}
