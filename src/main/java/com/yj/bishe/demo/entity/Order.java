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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    /**
     * 房源ID
     */
    private Integer lid;

    /**
     * 租房用户ID
     */
    private Integer uid;

    /**
     * 租房时长
     */
    private String dura;

    /**
     * 金额
     */
    private String money;

    /**
     * 订单生成时间
     */
    private LocalDateTime time;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
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
    public String getDura() {
        return dura;
    }

    public void setDura(String dura) {
        this.dura = dura;
    }
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
        "oid=" + oid +
        ", lid=" + lid +
        ", uid=" + uid +
        ", dura=" + dura +
        ", money=" + money +
        ", time=" + time +
        "}";
    }
}
