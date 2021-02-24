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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 姓名
     */
    private String uname;

    /**
     * 电话号码：175****3562
     */
    private String uphone;

    /**
     * 性别：男/女
     */
    private String usex;

    /**
     * 密码：6-12位
     */
    private String upassword;

    /**
     * 用户身份：0/1（普通用户/管理员）
     */
    private Integer ushenf;

    /**
     * 证件类型：身份证/其他
     */
    private String uzjtype;

    /**
     * 证件号码：0-18位
     */
    private String ucardid;

    /**
     * 自我介绍
     */
    private String udesc;

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

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }
    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
    public Integer getUshenf() {
        return ushenf;
    }

    public void setUshenf(Integer ushenf) {
        this.ushenf = ushenf;
    }
    public String getUzjtype() {
        return uzjtype;
    }

    public void setUzjtype(String uzjtype) {
        this.uzjtype = uzjtype;
    }
    public String getUcardid() {
        return ucardid;
    }

    public void setUcardid(String ucardid) {
        this.ucardid = ucardid;
    }
    public String getUdesc() {
        return udesc;
    }

    public void setUdesc(String udesc) {
        this.udesc = udesc;
    }

    @Override
    public String toString() {
        return "User{" +
        "uid=" + uid +
        ", uname=" + uname +
        ", usex=" + usex +
        ", upassword=" + upassword +
        ", ushenf=" + ushenf +
        ", uzjtype=" + uzjtype +
        ", ucardid=" + ucardid +
        ", udesc=" + udesc +
        "}";
    }
}
