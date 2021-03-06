package com.kc.goodlife.mapper.userInfo;

import com.github.pagehelper.Page;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserInfoMapper {

    @Select("SELECT * FROM user")
    Page<User> getUserList();

}
