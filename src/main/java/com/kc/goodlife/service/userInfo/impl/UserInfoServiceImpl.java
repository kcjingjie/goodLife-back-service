package com.kc.goodlife.service.userInfo.impl;

import com.kc.goodlife.exception.ServiceException;
import com.kc.goodlife.mapper.userInfo.UserInfoMapper;
import com.kc.goodlife.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author liuyan
 * 用户信息 权限表
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List userInfo(String userId) throws ServiceException {

        return null;
    }

}
