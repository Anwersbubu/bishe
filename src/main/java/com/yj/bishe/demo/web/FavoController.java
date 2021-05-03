package com.yj.bishe.demo.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.IFavoService;
import com.yj.bishe.demo.vo.JsonResult;
import com.yj.bishe.demo.vo.favoVo;
import com.yj.bishe.demo.vo.listVo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/users/listfavo")
    public String toListfao(){ return "favo"; }

    @RequestMapping("/users/myfavo")
    @ResponseBody
    public listVo myFavo(int page, int limit, HttpServletRequest request){
        User usersession = (User) request.getSession().getAttribute("usersession");
        Integer uid = usersession.getUid();
        return favoService.showFavo(page,limit,uid);
    }

    //房源收藏
    @PostMapping("/users/favorites")
    @ResponseBody
    public JsonResult favoritesByLid(Integer lid, HttpSession session){
        User user = (User)session.getAttribute("usersession");
        Integer uid = user.getUid();
        return favoService.favoritesByLid(uid,lid);
    }

    //取消收藏
    @PostMapping("/users/delfavo")
    @Transactional
    @ResponseBody
    public JsonResult delFavoListByUidLid(int lid, HttpSession session){
        User usersession = (User) session.getAttribute("usersession");
        return favoService.delFavoByUidAndLid(usersession.getUid(),lid);
    }

}
