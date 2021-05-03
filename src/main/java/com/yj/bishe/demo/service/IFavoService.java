package com.yj.bishe.demo.service;

import com.alibaba.fastjson.JSON;
import com.yj.bishe.demo.entity.Favo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.bishe.demo.vo.JsonResult;
import com.yj.bishe.demo.vo.favoVo;
import com.yj.bishe.demo.vo.listVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public interface IFavoService extends IService<Favo> {

    //收藏房源,要考虑到用户重复点击收藏按钮的情况
    //所以要先进行判断，用uid和lid
    JsonResult favoritesByLid(Integer uid, Integer lid);

    //获取收藏标表
    listVo showFavo(int page, int limit, int uid);

    //取消收藏
    JsonResult delFavoByUidAndLid(int uid, int lid);

}
