package com.gdut.imis.esclientdemo;

import com.gdut.imis.esclientdemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsClientDemoApplicationTests {
@Autowired
private UserMapper userMapper;

	@Test
	public void contextLoads() {
	}
	@Test
	public void insert(){
		User u=new User();
		u.setAge(21);
		u.setEmail("1016644172@qq.com");
		u.setId(3L);
		u.setName("tjl");
		userMapper.insert(u);
		userMapper.selectList(null).forEach(System.out::println);
	}
	@Test
	public void test(){
		System.out.println(System.getProperty("user.dir"));
	}

}
