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

    public Page<UserModel> getUserByIdOrName(String param) {
        Page<UserModel> userByNameOrId = userMapper.getUserByNameOrId(param);
        return userByNameOrId;
    }

    public Page<UserModel> getUsersByState(int state) {
        if (state == 2){
            return userMapper.getUserList();
        }
        return userMapper.getUsersByState(state);
    }

    public int updateUserState(int userId, int state) {
        return userMapper.updateUserState(userId,state);
    }

    public UserModel getUserById(int id) {
        UserModel user = userMapper.getUserById(id);
        return user;
    }

}
