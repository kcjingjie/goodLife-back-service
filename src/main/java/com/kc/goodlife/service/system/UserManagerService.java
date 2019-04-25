package com.kc.goodlife.service.system;

import com.github.pagehelper.Page;
import com.kc.goodlife.model.UserManagerModel;

import java.util.List;

public interface UserManagerService {
    UserManagerModel getUserDetails(Long userId);

    UserManagerModel updateUserDetails(UserManagerModel userManagerModel,Long userId,String roleIds,String droleIds,Long personId);

    boolean deleteUser(Long userId);

    boolean deleteUserList(String userIds);

    UserManagerModel addUser(UserManagerModel userManagerModel,String roleIds,String droleIds,Long personId);

    Page getList(String keyword, int pageNum, int pageSize);

    void updatePassword(Long userId, String password, Long lastPerson);


    boolean putUserRole(String roleIds, Long userId, Long personId);

    boolean putUserDataRole(String droleIds, Long userId, Long personId);

    List<UserManagerModel> getInfoByUserName(String userName);
}
