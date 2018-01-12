package com.sonic.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.sonic.common.DbRouter;
import com.sonic.common.ShardField;
import com.sonic.dao.UserDao;
import com.sonic.dao.UserDetailDao;
import com.sonic.dao.entity.UserDetailEntity;
import com.sonic.dao.entity.UserEntity;
import com.sonic.service.UserService;

@DbRouter(shardField = {"id"},write = true)
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private UserDetailDao userDetailDao;
	@Override
	@Transactional
	@DbRouter(shardField = {"id"},write = true)
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
	@Override
	@Transactional(readOnly=true)
	public UserEntity getUser(Integer id,ShardField shardField) {
		return this.userDao.getUserById(id);
	}
	@Override
	@Transactional(readOnly=true)
	public UserDetailEntity getUserDetail(Integer id,ShardField shardField) {
		return this.userDetailDao.getUserById(id);
	}
}
