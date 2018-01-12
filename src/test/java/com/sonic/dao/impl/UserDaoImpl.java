package com.sonic.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sonic.dao.UserDao;
import com.sonic.dao.entity.UserEntity;


public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	public boolean insertUser(UserEntity user) {
		
		return getSqlSession().insert("user.insertUser", user) > 0;
	}

	@Override
	public UserEntity getUserById(Integer id) {
		return (UserEntity) getSqlSession().selectOne("user.queryUserById", id);
	}

}
