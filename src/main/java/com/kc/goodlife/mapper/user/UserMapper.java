package com.kc.goodlife.mapper.user;
import com.github.pagehelper.Page;
import com.kc.goodlife.model.UserModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("SELECT * FROM user")
    Page<UserModel> getUserList();

}
