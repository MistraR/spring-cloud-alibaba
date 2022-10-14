package com.sample.mall.common.dto;

import com.sample.mall.common.base.BaseBean;

import java.util.Date;

/**
 * 用户信息DTO
 */
public class UserDTO extends BaseBean {

    /** 用户ID **/
    private Long id;

    /** 用户账号 **/
    private String account;

    /** 用户姓名 **/
    private String name;

    /** 用户密码 **/
    private String password;

    /** 用户角色 1-普通用户 2-讲师 **/
    private Integer role;

    /** 用户状态 0-锁定 1-正常 **/
    private Integer status;

    public UserDTO() {
    }

    private UserDTO(Builder builder) {
        this.id = builder.id;
        this.account = builder.account;
        this.name = builder.name;
        this.password = builder.password;
        this.status = builder.status;
    }

    public static class Builder {

        private Long id;
        private String account;
        private String name;
        private String password;
        private Integer role;
        private Integer status;

        public UserDTO.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDTO.Builder account(String account) {
            this.account = account;
            return this;
        }

        public UserDTO.Builder name(String name) {
            this.name = name;
            return this;
        }

        public UserDTO.Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserDTO.Builder role(Integer role) {
            this.role = role;
            return this;
        }

        public UserDTO.Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
