package com.sonic;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sonic.common.ShardField;
import com.sonic.dao.entity.UserDetailEntity;
import com.sonic.dao.entity.UserEntity;
import com.sonic.service.UserService;

public class DemoTest {

	private ClassPathXmlApplicationContext appCtx;

	@Before
	public void init() {
		appCtx = new ClassPathXmlApplicationContext(
				new String[] { "spring/dal-spring.xml", "spring/service-spring.xml" });
	}

	@Test
	public void test_add() {
		UserService userService = appCtx.getBean("userService", UserService.class);
		try {
			UserDetailEntity user = new UserDetailEntity();
			user.setId(3);
			user.setName("tom");
			user.setSex(1);
			user.setAge(26);
			userService.testAddUser(user);
			user = new UserDetailEntity();
			user.setId(4);
			user.setName("tom");
			user.setSex(1);
			user.setAge(26);
			userService.testAddUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testQuery(){
		UserService userService = appCtx.getBean("userService", UserService.class);
		try {
			UserEntity userEntity = userService.getUser(4,new ShardField(4));
			System.out.println(userEntity.getId()+","+userEntity.getName());
			userEntity = userService.getUser(3,new ShardField(3));
			System.out.println(userEntity.getId()+","+userEntity.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
