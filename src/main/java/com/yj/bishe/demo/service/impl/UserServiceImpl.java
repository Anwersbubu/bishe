package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.dao.UserMapper;
import com.yj.bishe.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

    @Resource
    UserMapper userMapper;

    //注册
    @Override
    public JsonResult userRegistered(User user) {
        int n;
        JsonResult ret;
        user.setUpassword(userMapper.MdPassword(user.getUpassword()));
        n = userMapper.insert(user);
        if (n == 1){
            ret = new JsonResult(true,"注册成功");
            ret.setData("uname",user.getUname());
        }else {
            ret = new JsonResult(false,"注册失败");
        }
        return ret;
    }

    @Override
    public JsonResult userLogin(Integer uphone, String upassword, HttpSession session) {

        JsonResult ret;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uphone",uphone);
        wrapper.eq("upassword", userMapper.MdPassword(upassword));
        User selectOne = userMapper.selectOne(wrapper);
        if (selectOne != null){
            ret = new JsonResult(true,"登陆成功");
            session.setAttribute("usersession",selectOne);
            ret.setData("user",selectOne);
        }else {
            ret = new JsonResult(false,"登陆失败");
        }
        return ret;
    }


}
