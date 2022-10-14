package com.sample.mall.order.service.impl;

import com.sample.mall.order.mapper.OrderItemMapper;
import com.sample.mall.order.service.IOrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class OrderItemServiceImpl implements IOrderItemService {

    @Resource
    OrderItemMapper orderItemMapper;

}
