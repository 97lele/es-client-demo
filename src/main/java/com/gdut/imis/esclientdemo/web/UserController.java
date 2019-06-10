package com.gdut.imis.esclientdemo.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdut.imis.esclientdemo.dao.UserDao;
import com.gdut.imis.esclientdemo.entity.User;
import com.gdut.imis.esclientdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lulu
 * @Date 2019/6/8 14:27
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private IUserService userService;
    @GetMapping("/user/insert")
    public String insert(@RequestParam("name")String name,@RequestParam("age")Integer age,@RequestParam("email")String email,@RequestParam("id")Long id){
        User u=new User();
        u.setId(id);
        u.setName(name);
        u.setAge(age);
        u.setEmail(email);

return userDao.insert(u)+"";
    }



    @GetMapping("/user/get")
    public List<User> get(){

        return   userDao.getUserForTestMapperLocation();
    }
}
