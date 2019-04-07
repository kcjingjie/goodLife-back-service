package com.kc.goodlife.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author liuyan
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserManagerModel {

    private Long id;

    private String userName;

    private String password;



}
