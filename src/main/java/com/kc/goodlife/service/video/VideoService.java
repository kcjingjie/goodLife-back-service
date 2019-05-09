package com.kc.goodlife.service.video;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.kc.goodlife.model.VideoModel;

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
}
