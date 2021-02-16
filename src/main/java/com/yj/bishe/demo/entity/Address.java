package com.yj.bishe.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地址ID
     */
    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区（县）
     */
    private String area;

    /**
     * 街道
     */
    private String street;

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

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
        return "Address{" +
        "aid=" + aid +
        ", country=" + country +
        ", province=" + province +
        ", city=" + city +
        ", area=" + area +
        ", street=" + street +
        ", rural=" + rural +
        ", hnum=" + hnum +
        ", jingdu=" + jingdu +
        ", weidu=" + weidu +
        "}";
    }
}
