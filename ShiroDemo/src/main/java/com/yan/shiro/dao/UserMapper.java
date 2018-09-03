package com.yan.shiro.dao;

import java.util.List;
import java.util.Set;

import com.yan.shiro.model.Role;
import com.yan.shiro.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUserName(String userName);
    
    Set<String> selectRoleByUserName(String userName);
}