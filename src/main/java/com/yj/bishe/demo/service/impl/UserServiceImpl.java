package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.dao.UserMapper;
import com.yj.bishe.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    UserMapper userMapper;

    //注册
    @Override
    public JsonResult userRegistered(User user) {
        int n = 0;
        JsonResult ret = null;
        user.setUpassword(userMapper.MdPassword(user.getUpassword()));
        n = userMapper.insert(user);
        if (n == 1){
            ret = new JsonResult(true,"注册成功");
            ret.setData("uname",user.getUname());
            return ret;
        }else {
            ret = new JsonResult(false,"注册失败");
            return ret;
        }
    }

    @Override
    public JsonResult userLogin(Integer uphone, String upassword, HttpSession session) {

        JsonResult ret = null;
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("uphone",uphone);
        wrapper.eq("upassword", userMapper.MdPassword(upassword));
        User selectOne = userMapper.selectOne(wrapper);
        if (selectOne != null){
            ret = new JsonResult(true,"登陆成功");
            session.setAttribute("usersession",selectOne);
            ret.setData("user",selectOne);
            return ret;
        }else {
            ret = new JsonResult(false,"登陆失败");
            return ret;
        }
    }


}
