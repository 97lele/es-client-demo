package com.gdut.imis.esclientdemo.service;

import com.gdut.imis.esclientdemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lele
 * @since 2019-06-10
 */
public interface IUserService extends IService<User> {

     void addUser(User u);

}
