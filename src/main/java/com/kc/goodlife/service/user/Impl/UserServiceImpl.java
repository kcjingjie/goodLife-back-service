package com.kc.goodlife.service.user.Impl;

import com.github.pagehelper.Page;
import com.kc.goodlife.mapper.user.UserMapper;
import com.kc.goodlife.model.UserModel;
import com.kc.goodlife.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Page<UserModel> getUserByPage() {

        Page<UserModel> userList = userMapper.getUserList();
        return userList;
    }
}
