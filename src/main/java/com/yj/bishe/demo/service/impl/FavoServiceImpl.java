package com.yj.bishe.demo.service.impl;

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

    @Override
    public JsonResult favoritesByLid(Integer uid, Integer lid) {
        JsonResult ret = null;

        return ret;
    }
}
