package com.kc.goodlife.service.login.impl;

import com.kc.goodlife.exception.ServiceException;
import com.kc.goodlife.model.UserManagerModel;
import com.kc.goodlife.utils.BCryptUtil;
import com.kc.goodlife.utils.EncryptUtil;
import com.kc.goodlife.mapper.login.AuthTokenMapper;
import com.kc.goodlife.mapper.login.AuthUserMapper;
import com.kc.goodlife.result.ResultCode;
import com.kc.goodlife.service.login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liweiqiang
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Autowired
    private AuthTokenMapper authTokenMapper;


    public Map login(String userName, String rawPassword) throws ServiceException {

        UserManagerModel user = authUserMapper.getUserByUsername(userName);

        // login successfully
        if (BCryptUtil.checkpw(rawPassword, user.getPassword())) {
            System.out.println("密码验证正确");
            // 重新登录 或者 首次登录 都会生成新的 token
            String token = EncryptUtil.encodeMD5(user.getId() + Calendar.getInstance().getTime().toString());

            // user already logged in
            if (!authTokenMapper.isTokenExistsById(user.getId()).equals(0)) {
                authTokenMapper.updateToken(user.getId(), token);
            } else {
                // 用户首次登陆
                authTokenMapper.insertNewToken(user.getId(), token);
            }
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("token", token);
            result.put("userName", userName);
            return result;
        }
        throw new ServiceException(ResultCode.USER_OR_PASS_ERROR, new Throwable(ResultCode.USER_OR_PASS_ERROR.getMsg()));
    }


    public boolean updatePassword(String token, String oldPassword, String newPassword) {

        Long userId = authTokenMapper.getUserIdByToken(token);

        String hashedPasswd = authUserMapper.getHashedPasswd(userId);

        if (!BCryptUtil.checkpw(oldPassword, hashedPasswd)) {
            return false;
        }

        if (BCryptUtil.checkpw(newPassword, hashedPasswd)) {
            return false;
        }

        if (authUserMapper.updateUserPassword(userId, BCryptUtil.hashpw(newPassword, BCryptUtil.gensalt(12))) != 1) {
            return false;
        }

        return true;
    }


    public boolean logout(String token) {
        if (authTokenMapper.isTokenExists(token).equals(0)) {
            return false;
        }

        authTokenMapper.delete(token);
        return true;
    }

    public boolean isTokenExist(String token) {
        return !authTokenMapper.isTokenExists(token).equals(0);
    }

    public Long getUserIdByToken(String token) {
        return authTokenMapper.getUserIdByToken(token);
    }
}
