package com.mistra.consumer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: WangRui
 * @Version: 1.0
 * @Time: 2019/8/4 18:55
 * @Description:
 */
@Data
@TableName(value = "user")
public class User {

    @TableId(value="id")
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("phone")
    private String phone;
}
