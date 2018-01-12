package com.sonic.service;

import com.sonic.common.ShardField;
import com.sonic.dao.entity.UserDetailEntity;
import com.sonic.dao.entity.UserEntity;


public interface UserService {
	
	public void testAddUser(UserDetailEntity user);
	
	public UserEntity getUser(Integer id, ShardField shardField);
	public UserDetailEntity getUserDetail(Integer id,ShardField shardField);
}
