package com.yj.bishe.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public class Listings implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房源ID
     */
    @TableId(value = "lid", type = IdType.AUTO)
    private Integer lid;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 房东姓名
     */
    private String uname;

    /**
     * 房源名
     */
    private String lname;

    /**
     * 地址ID
     */
    private Integer aid;

    /**
     * 房源大小
     */
    private String lsize;

    /**
     * 房源格局
     */
    private String lparttern;

    /**
     * 房源价格
     */
    private String lprice;

    /**
     * 房源图片
     */
    private String limg;

    /**
     * 房源描述
     */
    private String ldesc;

    /**
     * 房源状态
     */
    private String lstat;

    /**
     * 上传时间
     */
    private LocalDateTime ltime;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
    public String getLsize() {
        return lsize;
    }

    public void setLsize(String lsize) {
        this.lsize = lsize;
    }
    public String getLparttern() {
        return lparttern;
    }

    public void setLparttern(String lparttern) {
        this.lparttern = lparttern;
    }
    public String getLprice() {
        return lprice;
    }

    public void setLprice(String lprice) {
        this.lprice = lprice;
    }
    public String getLimg() {
        return limg;
    }

    public void setLimg(String limg) {
        this.limg = limg;
    }
    public String getLdesc() {
        return ldesc;
    }

    public void setLdesc(String ldesc) {
        this.ldesc = ldesc;
    }
    public String getLstat() {
        return lstat;
    }

    public void setLstat(String lstat) {
        this.lstat = lstat;
    }
    public LocalDateTime getLtime() {
        return ltime;
    }

    public void setLtime(LocalDateTime ltime) {
        this.ltime = ltime;
    }

    @Override
    public String toString() {
        return "Listings{" +
        "lid=" + lid +
        ", uid=" + uid +
        ", uname=" + uname +
        ", lname=" + lname +
        ", aid=" + aid +
        ", lsize=" + lsize +
        ", lparttern=" + lparttern +
        ", lprice=" + lprice +
        ", limg=" + limg +
        ", ldesc=" + ldesc +
        ", lstat=" + lstat +
        ", ltime=" + ltime +
        "}";
    }
}
