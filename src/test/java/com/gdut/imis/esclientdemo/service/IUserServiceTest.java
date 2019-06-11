package com.gdut.imis.esclientdemo.service;

import com.gdut.imis.esclientdemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author lulu
 * @Date 2019/6/11 12:51
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IUserServiceTest
{
    @Autowired
    private IUserService userService;
    @Test
    public void test(){
        //String name, Long id, Integer age, String email
        userService.saveBatch(        Arrays.asList(new User("a",12L,12,"t"),new User("b",13L,13,"b"))
        );
    }

}