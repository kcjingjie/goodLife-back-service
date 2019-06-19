package com.kc.goodlife.mapper.user;
import com.github.pagehelper.Page;
import com.kc.goodlife.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("SELECT * FROM user")
    Page<UserModel> getUserList();

    @Select("Select * From user where id = #{userId}")
    UserModel getUserById(@Param("userId")int userId);

    @Select("Select * From user where username = #{username}")
    UserModel getUserByUserName(@Param("username")String username);

    @Select("Select * From user where username = #{param} or id=#{param}")
    Page<UserModel> getUserByNameOrId(@Param("param")String param);

    @Select("Select * From user where state = #{state}")
    Page<UserModel> getUsersByState(@Param("state")int state);

    @Update("Update user set state=#{state} where id =#{userId}")
    int updateUserState(@Param("userId")int id,@Param("state")int state);
}
