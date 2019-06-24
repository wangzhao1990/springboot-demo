package com.zhao.demo.service.user;

import com.zhao.demo.common.exceptions.ApiException;
import com.zhao.demo.dal.mapper.UserMapper;
import com.zhao.demo.dal.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }
}
