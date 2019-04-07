package com.kc.goodlife.handler;

import com.kc.goodlife.exception.ParameterInvalidException;
import com.kc.goodlife.exception.ServiceException;
import com.kc.goodlife.result.ResultGenerator;
import com.kc.goodlife.result.Result;
import com.kc.goodlife.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result commonExceptionHandler(Exception e) {
        log.error(e.getMessage(), e.getCause());
        return ResultGenerator.generate(ResultCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(ParameterInvalidException.class)
    @ResponseBody
    public Result parameterInvalidExceptionHandler(ParameterInvalidException e) {
        return ResultGenerator.generate(e.getResultCode());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceExceptionHandler(ServiceException e) {
        log.error(e.getMessage(), e.getCause());
        return ResultGenerator.generate(e.getResultCode());
    }
}
