package com.yj.bishe.demo.dao;

import com.yj.bishe.demo.entity.Listings;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Mapper
@Repository
public interface ListingsMapper extends BaseMapper<Listings> {

    //房源多图片上传
    int UpdateImgByLid(@Param("lid") int lid, @Param("limg") String ling);

}
