package com.sonic.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sonic.dao.UserDetailDao;
import com.sonic.dao.entity.UserDetailEntity;
import com.sonic.dao.entity.UserEntity;


public class UserDetailDaoImpl extends SqlSessionDaoSupport implements UserDetailDao {

	public boolean insertUser(UserDetailEntity user) {
		
		return getSqlSession().insert("userDetail.insertUser", user) > 0;
	}

	@Override
	public UserDetailEntity getUserById(Integer id) {
		return (UserDetailEntity) getSqlSession().selectOne("userDetail.queryUserById", id);
	}

}
