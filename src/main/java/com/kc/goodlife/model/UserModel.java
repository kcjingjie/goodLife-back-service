package com.kc.goodlife.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {

    private Integer id;

    private String username;

    private String password;

    private int state;

    private String phone;

    private String role;

    private String imgUrl;


}
