package com.gdut.imis.esclientdemo.dao;

import com.gdut.imis.esclientdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lele
 * @since 2019-06-10
 */
public interface UserDao extends BaseMapper<User> {
List<User> getUserForTestMapperLocation();
void test();
}
