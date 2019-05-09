package com.kc.goodlife.controller.video;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kc.goodlife.model.VideoModel;
import com.kc.goodlife.result.Result;
import com.kc.goodlife.result.ResultCode;
import com.kc.goodlife.result.ResultGenerator;
import com.kc.goodlife.service.login.AuthService;
import com.kc.goodlife.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private AuthService authService;

    @RequestMapping("/videoUpdateCurdateCount")
    @ResponseBody
    public Result getVideoCurdateCount(){
        String count = "{count:"+videoService.getVideoUpdateCurdate()+"}";
        Object parse = JSON.parse(count);
        return ResultGenerator.generate(ResultCode.SUCCESS,parse);
    }
    @RequestMapping("/videosCount")
    @ResponseBody
    public Result getVideosCount(){
        String count = "{count:"+videoService.getVideosCount()+"}";
        Object parse = JSON.parse(count);
        return  ResultGenerator.generate(ResultCode.SUCCESS,parse);
    }
    @RequestMapping("/averageVideo")
    @ResponseBody
    public Result getTotalDays(){
        String count ="{videoCount:"+videoService.getVideosCount()+",totalDays:"+videoService.getTotalDays()+"}";
        Object parse = JSON.parse(count);
        return  ResultGenerator.generate(ResultCode.SUCCESS,parse);
    }

    @RequestMapping("/chartData")
    @ResponseBody
    public Result getChartData(){
        int average = videoService.getVideosCount()/videoService.getTotalDays();
        String count ="{videoCount:"+videoService.getVideosCount()+",totalDays:"+videoService.getTotalDays()+",sevenDays:"+videoService.getSevenDaysVideos()+",average:"+average+"}";
        Object parse = JSON.parse(count);
        return  ResultGenerator.generate(ResultCode.SUCCESS,parse);
    }

    @RequestMapping("/getVideosByUserId")
    @ResponseBody
    public  Result getVideosByUserId(String token,int pageNum,int pageSize){
        Long userIdByToken = authService.getUserIdByToken(token);
        int i = userIdByToken.intValue();
        PageHelper.startPage(pageNum,pageSize);
        Page<VideoModel> videosByUserId = videoService.getVideosByUserId(i);
        PageInfo<VideoModel> pagePageInfo = new PageInfo<VideoModel>(videosByUserId);
        return  ResultGenerator.generate(ResultCode.SUCCESS,pagePageInfo);
    }



}
