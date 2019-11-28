package com.gdut.imis.esclientdemo.dao;

import com.gdut.imis.esclientdemo.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lele
 * @since 2019-06-10
 */
public interface RoleDao  {

     void insertRole(Role role);

    Role getRoleById(Long id);

}
