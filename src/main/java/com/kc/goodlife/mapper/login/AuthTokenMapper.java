package com.kc.goodlife.mapper.login;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author liuyan
 */
@Component
public interface AuthTokenMapper {
    @Select(" SELECT userId FROM token WHERE token = #{token}")
    Long getUserIdByToken(@Param("token") String token);

    @Select(" SELECT COUNT(userId) FROM TOKEN WHERE token = #{token}")
    Integer isTokenExists(@Param("token") String token);

    /**
     * 根据用户ID查询用户token
     * @param userId
     * @return
     */
    @Select(" SELECT COUNT(userId) FROM token WHERE userID = #{userId}")
    Integer isTokenExistsById(@Param("userId") Long userId);

    @Insert(" INSERT INTO token (userId, token) VALUES (#{userId}, #{token})")
    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    int insertNewToken(@Param("userId") Long userId, @Param("token") String token);

    /**
     * 根据用户ID更新token
     * @param userId
     * @param newToken
     * @return
     */
    @Update(" UPDATE token SET token = #{newToken}" +
            " WHERE userID = #{userId}")
    int updateToken(@Param("userId") Long userId, @Param("newToken") String newToken);



    @Delete(" DELETE FROM token WHERE token = #{token}")
    int delete(@Param("token") String token);
}
