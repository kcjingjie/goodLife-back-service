package com.kc.goodlife.service.user;

import com.github.pagehelper.Page;
import com.kc.goodlife.model.UserModel;
public interface UserService {
    Page<UserModel> getUserByPage();
}
