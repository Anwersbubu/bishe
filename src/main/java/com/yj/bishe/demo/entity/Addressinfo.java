package com.yj.bishe.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Addressinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地址详细ID
     */
    @TableId(value = "aiid", type = IdType.AUTO)
    private Integer aiid;

    /**
     * 地址ID
     */
    private Integer aid;

    /**
     * 乡村
     */
    private String rural;

    /**
     * 门牌号
     */
    private String hnum;

    /**
     * 经度
     */
    private String jingdu;

    /**
     * 纬度
     */
    private String weidu;

    public Integer getAiid() {
        return aiid;
    }

    public void setAiid(Integer aiid) {
        this.aiid = aiid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getRural() {
        return rural;
    }

    public void setRural(String rural) {
        this.rural = rural;
    }

    public String getHnum() {
        return hnum;
    }

    public void setHnum(String hnum) {
        this.hnum = hnum;
    }

    public String getJingdu() {
        return jingdu;
    }

    public void setJingdu(String jingdu) {
        this.jingdu = jingdu;
    }

    public String getWeidu() {
        return weidu;
    }

    public void setWeidu(String weidu) {
        this.weidu = weidu;
    }

    @Override
    public String toString() {
        return "Addressinfo{" +
                "aiid=" + aiid +
                ", aid=" + aid +
                ", rural='" + rural + '\'' +
                ", hnum='" + hnum + '\'' +
                ", jingdu='" + jingdu + '\'' +
                ", weidu='" + weidu + '\'' +
                '}';
    }
}
