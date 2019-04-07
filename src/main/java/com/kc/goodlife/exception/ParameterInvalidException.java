package com.kc.goodlife.exception;


import com.kc.goodlife.result.ResultCode;

public class ParameterInvalidException extends ServiceException {

    public ParameterInvalidException() {
        super(ResultCode.PARA_ERROR, null);
    }

    public ParameterInvalidException(Throwable t) {
        super(ResultCode.PARA_ERROR, t);
    }

}
