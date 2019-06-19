package com.kc.goodlife.service.video;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.kc.goodlife.model.VideoModel;

import java.util.List;
import java.util.Map;

/**
 * 视频接口
 */
public interface VideoService {

    /**
     * 得到今天上传视频的数量
     * @return
     */
    int getVideoUpdateCurdate();

    /**
     * 得到总视频数
     */
    int getVideosCount();
    /**
     * 得到总天数
     */
    int getTotalDays();
    /**
     * 得到最近一周的时间视频上传的数量
     */
    int getSevenDaysVideos();
    /**
     * 得到最近一个月的视频上传数量
     */
    int getMonthVideos();
    /**
     * 根据用户搜索视频
     */
    Page<VideoModel> getVideosByUserId(Integer userId);

    /**
     * 视频量 变化
     */
    Map getVideoChangeRatio();
    /**
     * 视频访问总量
     */
    String getTotalWatchCount();

    /**
     * 视频访问量
     */
    String getTodayWatchCount();
    /**
     * 最近十天的访问量
     */
    List<Map<String, String>> getTendaysWatchCount();
    /**
     * 查询本年每月的数据
     */
    List<Map<String, String>> getMonthWatchCount();
    /**
     * 查询本月每天的数据
     */
    List<Map<String,String>> getEachDayInMonthWatchCount();

    /**
     * 查询本月每天上传的数据
     */
    List<Map<String,String>> getEachDayInMonthUploadCount();
    /**
     * 得到当天热门视频的视频id数组
     */
    List<Map<Integer,String>> getHotVideos();

    /**
     * 通过videoId 获得  videoMdoel
     */
    VideoModel getVideoModelById(int videoId);

}
