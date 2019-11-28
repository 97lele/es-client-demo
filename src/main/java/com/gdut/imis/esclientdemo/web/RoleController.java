package com.gdut.imis.esclientdemo.web;


import com.gdut.imis.esclientdemo.dao.RoleDao;
import com.gdut.imis.esclientdemo.entity.Role;
import com.gdut.imis.esclientdemo.service.IRoleService;
import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lele
 * @since 2019-06-10
 */
@RestController
@RequestMapping("/role")
public class RoleController {
@Autowired
private RoleDao roleDao;
    @GetMapping("/get")
  public Role get(){
      return  roleDao.getRoleById(1L);
    }
    @GetMapping("/put")
    public void put(){
        roleDao.insertRole(new Role().setId(5L).setRoleName("test").setRoleJob("test"));
    }
}

