package com.yj.bishe.demo.web;


import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.IFavoService;
import com.yj.bishe.demo.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Controller
public class FavoController {

    @Resource
    IFavoService favoService;

    //房源收藏
    @GetMapping("/users/favorites")
    @ResponseBody
    public JsonResult favoritesByLid(Integer lid, HttpSession session){
        User user = (User)session.getAttribute("usersession");
        Integer uid = user.getUid();
        return favoService.favoritesByLid(uid,lid);
    }

}
