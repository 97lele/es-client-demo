package com.gdut.imis.esclientdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author lulu
 * @Date 2019/6/8 13:30
 */
@Data
@TableName(value = "user",keepGlobalPrefix = false)

public class User extends Model<User>{
    @TableField(condition = SqlCondition.LIKE,strategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;
    @TableField(condition = "%s&gt;=#{%s}")
    private Integer age;
    private String email;

    public User() {
    }

    public User(String name, Long id, Integer age, String email) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
