package com.kc.goodlife.mapper.video;
import com.github.pagehelper.Page;
import com.kc.goodlife.model.VideoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 视频的mapper
 */
@Component
public interface VideoMapper {
    /**
     * 通过视频id获取视频的信息
     */
    @Select("SELECT * From videos where id = #{videoId}")
    VideoModel getVideoModelById(@Param("videoId") Integer videoId);
    /**
     * 当天视频数
     * @return
     */
    @Select("SELECT count(*) From videos where DATE_FORMAT(time,'%Y-%m-%d')=DATE_FORMAT(curdate(),'%Y-%m-%d') ")
    int getVideoUpdateCount();

    /**
     * 昨天视频数
     */
    @Select("SELECT count(*) From videos where DATE_FORMAT(time,'%Y-%m-%d') = DATE_SUB(curdate(),INTERVAL 1 day)")
    int getYesterdayVideoCount();

    /**
     * 总数据量
     */
    @Select("SELECT count(*) FROM videos;")
    int getVideosCount();

    /**
     * 总天数
     */
    @Select("SELECT count(*) FROM ((SELECT DISTINCT DATE_FORMAT(time,'%Y-%m-%d') FROM videos) as a)")
    int getTotalDays();
    /**
     * 最近一周
     */
    @Select("SELECT count(*) FROM VIDEOS where DATE_SUB(CURDATE(),INTERVAL 6 DAY) <= DATE_FORMAT(time,'%Y-%m-%d')")
    int getSevenDaysVideos();
    /**
     * 上一周视频数
     */
    @Select("SELECT count(*) FROM VIDEOS where DATE_SUB(CURDATE(),INTERVAL 7 DAY) <= DATE_FORMAT(time,'%Y-%m-%d') and DATE_SUB(CURDATE(),INTERVAL 13 DAY) <= DATE_FORMAT(time,'%Y-%m-%d')")
    int getLastSevenDaysVideos();
    /**
     * 最近一个月
     */
    @Select("SELECT count(*) FROM VIDEOS where DATE_SUB(CURDATE(),INTERVAL 1 MONTH)<= date(time)")
    int getMonthVideos();
    /**
     * 根据用户 搜索视频列表
     */
    @Select("SELECT * From VIDEOS where fromUserId = #{userId}")
    Page<VideoModel> getVideosByUserId(@Param("userId") Integer userId);
    /**
     * 视频总访问量
     */
    @Select("Select sum(count) From play")
    String getTotalWatchCount();
    /**
     *查询今天访问量
     */
    @Select("Select sum(count) From play where DATE_FORMAT(watchdate,'%Y-%m-%d') = CURDATE()")
    String getTodayWatchCount();
    /**
     * 查询最近十天每天的视频播放量
     */
    @Select("Select DATE_FORMAT(watchdate,'%Y-%m-%d') as x ,sum(count) as y from play where DATE_SUB(CURDATE(),INTERVAL 10 DAY) <= DATE_FORMAT(watchdate,'%Y-%m-%d') group by DATE_FORMAT(watchdate,'%Y-%m-%d') ")
    List<Map<String, String>> getTendaysWatchCount();
    /**
     *查询每月的视频播放
     */
    @Select("Select DATE_FORMAT(watchdate,'%M') as x ,sum(count) as y from play where DATE_FORMAT(watchdate,'%Y')= year(CURRENT_DATE) group by DATE_FORMAT(watchdate,'%Y-%m') ")
    List<Map<String, String>> getMonthWatchCount();
    /**
     *查询每月的视频每天的上传情况
     */
    @Select("Select DATE_FORMAT(time,'%d') as x ,count(*) as y from videos where DATE_FORMAT(time,'%Y-%m')= DATE_FORMAT(CURRENT_DATE(),'%Y-%m') group by DATE_FORMAT(time,'%Y-%m-%d') ")
    List<Map<String,String>> getEachDayInMonthUploadCount();
    /**
     * 查询本月 每天视频的播放量
     */
    @Select("Select DATE_FORMAT(watchdate,'%d') as x ,sum(count) as y from play where DATE_FORMAT(watchdate,'%Y-%m')= DATE_FORMAT(CURRENT_DATE(),'%Y-%m') group by DATE_FORMAT(watchdate,'%Y-%m-%d') ")
    List<Map<String, String>> getEachDayInMonthWatchCount();

    /**
     * 当天热门视频排行榜
     */
    @Select("Select videoId from play where DATE_FORMAT(watchdate,'%Y-%m-%d') = CURDATE() order by count+0  desc limit 7")
    int[] getHotVideos();

    /**
     * 查询7天内视频排行榜
     */
    @Select("Select videoId ,sum(count) as sum from play where DATE_FORMAT(watchdate,'%Y-%m-%d') >= DATE_SUB(CURDATE(),INTERVAL 7 DAY) group by videoId order by sum(count)  desc limit 7")
    List<Map<Integer,String>> getHotVideosInSenvenDays();

}
