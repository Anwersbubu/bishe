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
public class Favo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏ID
     */
    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 房源ID
     */
    private Integer lid;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    @Override
    public String toString() {
        return "Favo{" +
        "fid=" + fid +
        ", uid=" + uid +
        ", lid=" + lid +
        "}";
    }
}
