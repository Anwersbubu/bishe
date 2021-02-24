package com.yj.bishe;

import com.yj.bishe.demo.BisheApplication;
import com.yj.bishe.demo.service.IFavoService;
import com.yj.bishe.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = BisheApplication.class)
public class Test {

    @Autowired
    IUserService userService;

    @Resource
    IFavoService favoService;

    @org.junit.jupiter.api.Test
    public void testuser(){
        System.out.println(userService.getById(1).toString());
    }

    @org.junit.jupiter.api.Test
    public void testFavo(){
        System.out.println(favoService.getById(1).toString());
    }

}
