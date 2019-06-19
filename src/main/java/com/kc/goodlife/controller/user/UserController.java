package com.kc.goodlife.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kc.goodlife.model.UserModel;
import com.kc.goodlife.model.VideoModel;
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
        PageInfo<UserModel> userPageInfo = new PageInfo<UserModel>(userByPage);
        return ResultGenerator.generate(ResultCode.SUCCESS,userPageInfo);
    }
    /**
     * 根据 参数 查询
     */
    @RequestMapping("/userSearch")
    @ResponseBody
    public Result getUsersSearch(Integer pageNum,Integer pageSize,String param,int state){
        if (param=="" || param==null){
            PageHelper.startPage(pageNum,pageSize);
            Page<UserModel> usersByState = userService.getUsersByState(state);
            PageInfo<UserModel> userModelPageInfo = new PageInfo<UserModel>(usersByState);
            return  ResultGenerator.generate(ResultCode.SUCCESS,userModelPageInfo);
        }
        PageHelper.startPage(1,1);
        Page<UserModel> userByIdOrName = userService.getUserByIdOrName(param);
        PageInfo<UserModel> userModelPageInfo = new PageInfo<UserModel>(userByIdOrName);
        return  ResultGenerator.generate(ResultCode.SUCCESS,userModelPageInfo);
    }
    /**
     * 通过userId得到用户信息
     */
    @RequestMapping("/getUserById")
    @ResponseBody
    public Result getUserById(int id){
        UserModel user = userService.getUserById(id);
        return  ResultGenerator.generate(ResultCode.SUCCESS,user);
    }
    /**
     * 更新用户 状态
     */
    @RequestMapping("/updateUserState")
    @ResponseBody
    public Result updateUserState(int userId,int state){

        userService.updateUserState(userId,state);

        return  ResultGenerator.generate(ResultCode.SUCCESS);
    }


}
