package com.yj.bishe.demo.service.impl;

import com.yj.bishe.demo.entity.Order;
import com.yj.bishe.demo.dao.OrderMapper;
import com.yj.bishe.demo.service.IOrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
