package com.sonic.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.sonic.common.DbRouter;
import com.sonic.dao.UserDao;
import com.sonic.dao.UserDetailDao;
import com.sonic.dao.entity.UserDetailEntity;
import com.sonic.dao.entity.UserEntity;
import com.sonic.service.UserService;

@DbRouter(shardField = {"id","name"},write = true)
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private UserDetailDao userDetailDao;
	@Override
	@Transactional
	public void testAddUser(UserDetailEntity userDetail) {

		UserEntity user = new UserEntity();
		user.setId(userDetail.getId());
		user.setName(userDetail.getName());
		
		userDao.insertUser(user);
		userDetailDao.insertUser(userDetail);
		
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}
}
