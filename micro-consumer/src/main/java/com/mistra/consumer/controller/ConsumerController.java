package com.mistra.consumer.controller;

import com.mistra.consumer.dao.UserMapper;
import com.mistra.consumer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: WangRui
 * @Version: 1.0
 * @Time: 2019/8/4 18:50
 * @Description:
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private UserMapper userMapper;

    /**
     * SpringCloud提供的服务工具类
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/get")
    public User get(Integer id) {
        return userMapper.selectById(id);
    }


    /**
     *
     * @return 服务提供者所有实例的地址信息
     */
    @GetMapping("/getAllProvider")
    public List<ServiceInstance> getInstances() {
        // 查询指定服务的所有实例的信息
        return this.discoveryClient.getInstances("micro-provider");
    }
}
