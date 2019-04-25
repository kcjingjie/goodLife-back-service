package com.kc.goodlife.service.userInfo;

import java.util.List;

/**
 * @author liuyan
 * 用户信息 权限 接口
 */
public interface UserInfoService {
    /**
     * userInfo
     * 根据用户权限返回json
     */
    List userInfo(String userId);
}
