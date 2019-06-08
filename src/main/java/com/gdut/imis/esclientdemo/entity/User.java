package com.gdut.imis.esclientdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lulu
 * @Date 2019/6/8 13:30
 */
@Data
@TableName("user")
public class User {
    private String name;
    @TableId("id")
    private Long id;
    @TableField("age")
    private Integer age;
    private String email;
}
