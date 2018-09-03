package com.yan.shiro.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.shiro.dao.UserMapper;
import com.yan.shiro.model.Role;
import com.yan.shiro.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public User selectByUserName(String userName) {
		return userMapper.selectByUserName(userName);
	}
	
	public Set<String> selectRoleByUserName(String userName){
		return userMapper.selectRoleByUserName(userName);
	}
}
