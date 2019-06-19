package com.kc.goodlife.result;

import lombok.Data;

/**
 * @name
 * @description
 * @author: liuyan
 * @create: 2019/4/1 上午11:26
 **/
@Data
public class PlatformDataResult implements Result {
    private Object data;
    private String code;
    private String msg;

    public PlatformDataResult() {
    }

    public PlatformDataResult(String code, String msg, Object data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
