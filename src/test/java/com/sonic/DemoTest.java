package com.sonic;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sonic.dao.entity.UserDetailEntity;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
