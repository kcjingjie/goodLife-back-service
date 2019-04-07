package com.kc.goodlife.service.login;

import java.util.Map;

/**
 * @author liuyan
 */
public interface AuthService {
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    Map login(String userName, String password);

    /**
     * 更新密码
     * @param token
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(String token, String oldPassword, String newPassword);

    /**
     * 登出
     * @param token
     * @return
     */
    boolean logout(String token);

    /**
     * token验证
     * @param token
     * @return
     */
    boolean isTokenExist(String token);

    /**
     * 根据用户ID获取token
     * @param token
     * @return
     */
    Long getUserIdByToken(String token);

}
