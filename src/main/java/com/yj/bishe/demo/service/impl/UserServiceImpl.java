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
    public JsonResult userRegistered(User user, HttpSession session) {
        int n;
        JsonResult ret;
        user.setUpassword(userMapper.MdPassword(user.getUpassword()));
        try {
            n = userMapper.insert(user);
            if (n == 1){
                ret = new JsonResult(true,"注册成功");
                User user1 = userMapper.queryUserByUphone(user.getUphone());
                user1.setUpassword("保密");
                ret.setData("user",user1);
                session.setAttribute("usersession",user);
            }else {
                ret = new JsonResult(false,"注册失败");
            }
            return ret;
        }catch (Exception e){
            ret = new JsonResult(false,"注册失败电话号码已注册过");
        }
        return ret;
    }

    //登陆(电话号码+密码)
    @Override
    public JsonResult userLogin(String uphone, String upassword, HttpSession session) {
        JsonResult ret;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uphone",uphone);
        wrapper.eq("upassword", userMapper.MdPassword(upassword));
        User selectOne = userMapper.selectOne(wrapper);
        if (selectOne != null){
            selectOne.setUpassword("保密");
            ret = new JsonResult(true,"登陆成功");
            session.setAttribute("usersession",selectOne);
            ret.setData("user",selectOne);
        }else {
            ret = new JsonResult(false,"电话号码或密码错误登陆失败");
        }
        return ret;
    }

    //更新用户信息(用户自己更新，不能更新uid、upassword、ushenf)
    @Override
    public JsonResult userUpdateinfo(User user) {
        JsonResult ret;
        int i = userMapper.updateById(user);
        if (i == 1){
            ret = new JsonResult(true,"用户更新个人信息成功");
            User selectById = userMapper.selectById(user.getUid());
            ret.setData("user",selectById);
        }else {
            ret = new JsonResult(false,"用户更新个人信息失败");
        }
        return ret;
    }

    //管理员更新用户信息(除了不能修改uid、没有更新限制)
    @Override
    public JsonResult adminUpdateUser(User user) {
        JsonResult ret;
        user.setUpassword(userMapper.MdPassword(user.getUpassword()));
        int i = userMapper.updateById(user);
        if (i == 1){
            ret = new JsonResult(true,"修改用户信息成功");
            User selectById = userMapper.selectById(user.getUid());
            ret.setData("user",selectById);
        }else {
            ret = new JsonResult(false,"修改用户信息失败");
        }
        return ret;
    }

    @Override
    public JsonResult queryUserByUid2phone(String uphone) {
        JsonResult ret;
        User user = userMapper.queryUserByUphone(uphone);
        if (user != null){
            ret = new JsonResult(true,"查询到用户信息");
            ret.setData("user",user);
        }else {
            ret = new JsonResult(false,"通过uid或uphone查询用户信息失败");
        }
        return ret;
    }


}
