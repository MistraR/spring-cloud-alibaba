package com.sample.mall.user.service;


import com.sample.mall.common.dto.UserDTO;

/**
 * 用户信息Service
 */
public interface IUserService {

    /**
     * 根据 ID 获取用户
     *
     * @param userId
     * @return
     */
    UserDTO getUser(Long userId);

    /**
     * 更新用户信息
     *
     * @param userDTO
     * @return
     */
    boolean updateUser(UserDTO userDTO);
}
