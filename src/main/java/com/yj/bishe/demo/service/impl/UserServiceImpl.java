package com.yj.bishe.demo.service.impl;

import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.dao.UserMapper;
import com.yj.bishe.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
