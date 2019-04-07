package com.kc.goodlife.bean.auth;

import lombok.Data;

/**
 * @author liuyan
 */
@Data
public class AuthUserBean {
    private Integer id;

    private String username;

    private String password;

    private String realname;

    private String phone;

    private String email;
}
