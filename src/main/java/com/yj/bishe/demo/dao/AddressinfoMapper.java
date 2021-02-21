package com.yj.bishe.demo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.bishe.demo.entity.Addressinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressinfoMapper extends BaseMapper<Addressinfo> {
}
