package com.yj.bishe.demo.web;


import com.yj.bishe.demo.entity.User;
import com.yj.bishe.demo.service.IFavoService;
import com.yj.bishe.demo.vo.listVo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2021-02-14
 */
@Controller
public class PanoController {

    @Resource
    IFavoService favoService;

    @RequestMapping("/admin/listpano")
    public String adminListfao(){ return "adminpano"; }

    @RequestMapping("/admin/panos")
    @ResponseBody
    public listVo adminFavos(int page, int limit, HttpServletRequest request){
        User usersession = (User) request.getSession().getAttribute("usersession");
        Integer uid = usersession.getUid();
        Integer ushenf = usersession.getUshenf();
        return favoService.showFavo(page,limit,uid,ushenf);
    }

}
