package com.kc.goodlife.controller.login;

import com.kc.goodlife.result.ResultGenerator;
import com.kc.goodlife.result.Result;
import com.kc.goodlife.result.ResultCode;
import com.kc.goodlife.service.login.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "登录")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    Result login(String username, String password) {
        System.out.println(username+password);
        if (username == null || username.equals("")) {

            return ResultGenerator.generate(ResultCode.FAIL);
        }

        if (password == null || password.equals("")) {
            return ResultGenerator.generate(ResultCode.FAIL);
        }
        return ResultGenerator.generate(ResultCode.SUCCESS, authService.login(username, password));
    }

    @ApiOperation(value = "退出")
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseBody
    Result logout(String token) {
        if (authService.logout(token)) {
            return ResultGenerator.generate(ResultCode.SUCCESS);
        }

        return ResultGenerator.generate(ResultCode.FAIL);
    }
  /*  @RequestMapping(path = "/user/info",method = RequestMethod.GET)
    @ResponseBody
    Result getUserInfo(){
        return ResultGenerator.generate(ResultCode.SUCCESS,"userinfo");
    }*/
}
