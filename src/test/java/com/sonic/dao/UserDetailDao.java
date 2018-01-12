package com.sonic.dao;

import com.sonic.dao.entity.UserDetailEntity;
import com.sonic.dao.entity.UserEntity;


public interface UserDetailDao {
	public boolean insertUser(UserDetailEntity user);
	public UserDetailEntity getUserById(Integer id);
}
