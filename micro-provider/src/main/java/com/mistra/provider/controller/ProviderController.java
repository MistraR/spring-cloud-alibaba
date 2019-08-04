package com.mistra.provider.controller;

import com.mistra.provider.dao.UserMapper;
import com.mistra.provider.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangRui
 * @Version: 1.0
 * @Time: 2019/8/4 18:50
 * @Description:
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/get")
    public User get(Integer id) {
        return userMapper.selectById(id);
    }
}
