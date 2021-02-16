package com.yj.bishe.demo.service.impl;

import com.yj.bishe.demo.entity.Address;
import com.yj.bishe.demo.dao.AddressMapper;
import com.yj.bishe.demo.service.IAddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
