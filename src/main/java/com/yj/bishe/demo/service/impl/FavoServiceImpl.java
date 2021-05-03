package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.bishe.demo.dao.AddressMapper;
import com.yj.bishe.demo.dao.ListingsMapper;
import com.yj.bishe.demo.entity.Address;
import com.yj.bishe.demo.entity.Favo;
import com.yj.bishe.demo.dao.FavoMapper;
import com.yj.bishe.demo.entity.Listings;
import com.yj.bishe.demo.service.IFavoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import com.yj.bishe.demo.vo.listVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    AddressMapper addressMapper;

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

    @Override
    public listVo showFavo(int page, int limit, int uid) {
        listVo ret;
        QueryWrapper<Favo> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        Page<Favo> pages = new Page<>(page-1,limit);
        List<Favo> favoList = favoMapper.selectPage(pages, wrapper).getRecords();
        if (favoList.size() > 0) {
            List<Map<String, Object>> dataList = new ArrayList<>();
            Map<String,Object> dataMap = null;
            for (Favo f : favoList) {
                Listings listings = listingsMapper.selectById(f.getLid());
                if (listings != null){
                    dataMap = new HashMap<>();
                    dataMap.put("lid",listings.getLid());
                    dataMap.put("uname",listings.getUname());
                    dataMap.put("lname",listings.getLname());
                    Address address = addressMapper.selectById(listings.getAid());
                    if (address != null)
                        dataMap.put("aid",addressMapper.selectById(listings.getAid()).show());
                    else
                        dataMap.put("aid","地址不明");
                    dataMap.put("lsize",listings.getLsize());
                    dataMap.put("lparttern",listings.getLparttern());
                    dataMap.put("lprice",listings.getLprice());
                    dataMap.put("ltow",listings.getLtow());
                    dataMap.put("lfoo",listings.getLfoo());
                    dataMap.put("ldeco",listings.getLdeco());
                    dataMap.put("ldesc",listings.getLdesc());
                    dataMap.put("lstat",listings.getLstat());
                    dataList.add(dataMap);
                }
            }
            ret = new listVo(0,"用户收藏房源采集完毕",favoMapper.selectCount(wrapper));
            ret.setData(dataList);
        }else ret = new listVo(0,"用户没有收藏房源",0);
        return ret;
    }

    @Override
    public JsonResult delFavoByUidAndLid(int uid, int lid) {
        JsonResult ret;
        if (uid > 0 && lid > 0){
            QueryWrapper<Favo> wrapper = new QueryWrapper<>();
            wrapper.eq("uid",uid);
            wrapper.eq("lid",lid);
            int delete = favoMapper.delete(wrapper);
            if (delete == 1){
                ret = new JsonResult(true,"取消收藏成功");
            }else {
                JsonResult jsonResult = favoritesByLid(uid, lid);
                if (jsonResult.isSuccess())
                    ret = new JsonResult(false,"删除数据数量异常");
                else ret = new JsonResult(false,"删除异常，不知一行数据被删除");
            }
        }else ret = new JsonResult(false,"没有数据传到后端");
        return ret;
    }
}
