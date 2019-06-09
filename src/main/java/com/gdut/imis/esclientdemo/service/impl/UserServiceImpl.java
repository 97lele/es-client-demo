package com.gdut.imis.esclientdemo.service.impl;

import com.gdut.imis.esclientdemo.entity.User;
import com.gdut.imis.esclientdemo.dao.UserDao;
import com.gdut.imis.esclientdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lele
 * @since 2019-06-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
