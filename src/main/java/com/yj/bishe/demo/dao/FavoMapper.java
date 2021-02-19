package com.yj.bishe.demo.dao;

import com.yj.bishe.demo.entity.Favo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface FavoMapper extends BaseMapper<Favo> {

}
