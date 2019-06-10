package com.gdut.imis.esclientdemo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.api.R;
import com.gdut.imis.esclientdemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author lulu
 * @Date 2019/6/10 20:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void test(){
        userDao.getUserForTestMapperLocation().forEach(System.out::println);
        userDao.selectList(null).forEach(System.out::println);
    }

    @Test
    public void getUserForTestMapperLocation() throws Exception {

        QueryWrapper<User> wrapper=new QueryWrapper();
wrapper.notExists("SELECT * FROM USER u WHERE EXISTS( SELECT *  FROM USER  uu WHERE uu.age < 30 AND u.age=uu.`age` AND u.age=user.age)");
//
//        wrapper.select("CONVERT(CONCAT_WS('-',age,COUNT(*)) USING utf8)").groupBy("age");
        userDao.selectList(wrapper).forEach(System.out::println);
//        wrapper.orderBy(true,false,"age","name");
//        wrapper.select(User.class,i->i.getProperty().startsWith("n")).between("age",20,31).nested(i->i.eq("name","lele")).or().like(false,"email","@qq");
     /*   UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
        UpdateWrapper<User> u2=new UpdateWrapper<>();
        userUpdateWrapper.set("age",23).eq("name","lele");
        userDao.update(null,userUpdateWrapper);
        u2.setSql(true,"email='123@qq.com',age='100'").eq("name","tjl");
userDao.update(null,u2);*/
       /* LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> l1= Wrappers.<User>lambdaQuery();
lambdaQueryWrapper.likeRight(User::getName,"l").lt(User::getAge,30);

Map<SFunction<User,?>,Object> map=new HashMap<>();
map.put(User::getAge,23);
map.put(User::getName,"lele");
l1.allEq(map);
        userDao.selectList(lambdaQueryWrapper).forEach(System.out::println);
        userDao.selectList(l1).forEach(System.out::println);*/

    }

}