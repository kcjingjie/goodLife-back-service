package com.kc.goodlife.exception;


import com.kc.goodlife.result.ResultCode;

public class ServiceException extends RuntimeException {

    private ResultCode resultCode;
    private Throwable cause;

    public ServiceException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMsg(), cause);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
