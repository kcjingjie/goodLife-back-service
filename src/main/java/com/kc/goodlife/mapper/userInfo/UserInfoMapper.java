package com.kc.goodlife.mapper.userInfo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserInfoMapper {
    List<String> getUserRole();
}
