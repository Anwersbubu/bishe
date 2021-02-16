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
public class Pano implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 全景图片ID
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 房源ID
     */
    private Integer lid;

    /**
     * 全景图片
     */
    private String pimg;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }
    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    @Override
    public String toString() {
        return "Pano{" +
        "pid=" + pid +
        ", lid=" + lid +
        ", pimg=" + pimg +
        "}";
    }
}
