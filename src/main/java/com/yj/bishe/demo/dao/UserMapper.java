package com.yj.bishe.demo.dao;

import com.yj.bishe.demo.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

    //加密密码
    String MdPassword(String upassword);

    //通过uphone查询用户信息
    User queryUserByUphone(String uid2phone);

}
