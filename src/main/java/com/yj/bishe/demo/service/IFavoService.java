package com.yj.bishe.demo.service;

import com.yj.bishe.demo.entity.Favo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.bishe.demo.vo.JsonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
public interface IFavoService extends IService<Favo> {

    //收藏房源
    JsonResult favoritesByLid(Integer uid, Integer lid);

}
