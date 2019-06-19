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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        float average = (float) videoService.getVideosCount()/videoService.getTotalDays();
        Map videoChangeRatio = videoService.getVideoChangeRatio();
        Float sevenDays = (Float) videoChangeRatio.get("sevenDays");
        Float day = (Float) videoChangeRatio.get("day");
        String count ="{videoCount:"+videoService.getVideosCount()+",sevenDays:"+sevenDays+",day:"+day+",totalDays:"+videoService.getTotalDays()+",sevenDays:"+videoService.getSevenDaysVideos()+",average:"+average+"}";
        Object parse = JSON.parse(count);
        return  ResultGenerator.generate(ResultCode.SUCCESS,parse);
    }

    @RequestMapping("/getVideosByUserId")
    @ResponseBody
    public  Result getVideosByUserId(int id,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<VideoModel> videosByUserId = videoService.getVideosByUserId(id);
        PageInfo<VideoModel> pagePageInfo = new PageInfo<VideoModel>(videosByUserId);
        return  ResultGenerator.generate(ResultCode.SUCCESS,pagePageInfo);
    }

    @RequestMapping("/getVideoWatchCount")
    @ResponseBody
    public Result getTodayWatchCount(){
        String todayWatchCount = videoService.getTodayWatchCount();
        String totalWatchCount = videoService.getTotalWatchCount();
        List<Map<String, String>> tendaysWatchCount = videoService.getTendaysWatchCount();
        List<Map<String, String>> eachDayInMonthWatchCount = videoService.getEachDayInMonthWatchCount();
        List<Map<String, String>> eachDayInMonthUploadCount = videoService.getEachDayInMonthUploadCount();
        List<Map<String, String>> monthWatchCount = videoService.getMonthWatchCount();
        String s = JSON.toJSONString(tendaysWatchCount);
        String s1 = JSON.toJSONString(eachDayInMonthWatchCount);
        String s2 = JSON.toJSONString(monthWatchCount);
        String s3 = JSON.toJSONString(eachDayInMonthUploadCount);
        String object = "{todayWatchCount:"+todayWatchCount+",totalWatchCount:"+totalWatchCount+",tenDays:"+s+",eachDayInMonth:"+s1+",monthWatchCount:"+s2+",eachDayUpload:"+s3+"}";
        Object parse = JSON.parse(object);
        return  ResultGenerator.generate(ResultCode.SUCCESS,parse);
    }
    @RequestMapping("/getHotVideos")
    @ResponseBody
    public Result getHotVideos(){
        List<Map<Integer,String>> hotVideos = videoService.getHotVideos();
        ArrayList<Object> videos = new ArrayList<Object>();
        for (int i =0;i<hotVideos.size();i++){
            Map temp = hotVideos.get(i);
            String next = (String) temp.keySet().iterator().next();
            int id = (Integer) temp.get(next);
            VideoModel videoModelById = videoService.getVideoModelById(id);
            String parse = "{id:"+videoModelById.getId()+",\"name\":"+'"'+videoModelById.getTitle()+'"'+",total:"+temp.get("sum")+"}";
            Object parse1 = JSON.parse(parse);
            videos.add(parse1);
        }
        return ResultGenerator.generate(ResultCode.SUCCESS,videos);
    }
    @RequestMapping("/getVideoById")
    @ResponseBody
    public Result getVideoById(int id){
        VideoModel videoModelById = videoService.getVideoModelById(id);
        return  ResultGenerator.generate(ResultCode.SUCCESS,videoModelById);
    }


}
