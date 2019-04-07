package com.kc.goodlife.mapper.login;

import com.kc.goodlife.model.UserManagerModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author liuyan
 */
@Component
public interface AuthUserMapper {

    @Select("SELECT username,password FROM manager WHERE username = #{username}")
    UserManagerModel getUserByUsername(@Param("username") String username);

    @Select(" SELECT password FROM sys_user" +
            " WHERE user_name = #{username}")
    String getHashedPasswd(@Param("userId") Long userId);

    @Update(" UPDATE sys_user SET password = #{hashedNewPasswd},last_person = #{userId},last_time = now()" +
            " WHERE user_id = #{userId}")
    int updateUserPassword(@Param("userId") Long userId,
                           @Param("hashedNewPasswd") String hashedNewPasswd);
}
