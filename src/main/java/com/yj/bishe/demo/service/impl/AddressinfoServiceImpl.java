package com.yj.bishe.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.bishe.demo.dao.AddressinfoMapper;
import com.yj.bishe.demo.entity.Addressinfo;
import com.yj.bishe.demo.service.IAddressinfoService;
import org.springframework.stereotype.Service;

@Service
public class AddressinfoServiceImpl extends ServiceImpl<AddressinfoMapper, Addressinfo> implements IAddressinfoService {
}
