package com.sonic.dao;

import com.sonic.dao.entity.UserEntity;


public interface UserDao {
	public boolean insertUser(UserEntity user);
	public UserEntity getUserById(Integer id);
}
