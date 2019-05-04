package com.kc.goodlife.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kc.goodlife.model.UserModel;
import com.kc.goodlife.result.Result;
import com.kc.goodlife.result.ResultCode;
import com.kc.goodlife.result.ResultGenerator;
import com.kc.goodlife.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/userList")
    @ResponseBody
    public Result getUserList(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<UserModel> userByPage = userService.getUserByPage();
        return ResultGenerator.generate(ResultCode.SUCCESS,userByPage);
    }

}
