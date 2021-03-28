package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.bishe.demo.dao.ListingsMapper;
import com.yj.bishe.demo.entity.Favo;
import com.yj.bishe.demo.dao.FavoMapper;
import com.yj.bishe.demo.service.IFavoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Service
public class FavoServiceImpl extends ServiceImpl<FavoMapper, Favo> implements IFavoService {

    @Resource
    FavoMapper favoMapper;

    @Resource
    ListingsMapper listingsMapper;

    @Override
    public JsonResult favoritesByLid(Integer uid, Integer lid) {
        JsonResult ret;
        QueryWrapper<Favo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("lid",lid);
        Favo selectOne = favoMapper.selectOne(queryWrapper);
        if (selectOne == null) {
            Integer aid = listingsMapper.selectById(lid).getAid();
            if (aid != 0) {
                Favo favo = new Favo();
                favo.setUid(uid);
                favo.setLid(lid);
                favo.setAid(aid);
                int insert = favoMapper.insert(favo);
                if (insert == 1)
                    ret = new JsonResult(true, "房源收藏成功");
                else
                    ret = new JsonResult(false, "房源收藏失败");
            } else
                ret = new JsonResult(false, "房源收藏失败，房源地址异常");
        }else
            ret = new JsonResult(false,"您已经收藏过该房源了");
        return ret;
    }
}
