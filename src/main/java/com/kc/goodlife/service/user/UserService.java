package com.kc.goodlife.service.user;

import com.github.pagehelper.Page;
import com.kc.goodlife.model.UserModel;

public interface UserService {

    Page<UserModel> getUserByPage();

    UserModel getUserById(int id);

    Page<UserModel> getUserByIdOrName(String param);

    Page<UserModel> getUsersByState(int state);

    int updateUserState(int userId,int state);
}
