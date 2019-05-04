package com.kc.goodlife.mapper.video;
import com.github.pagehelper.Page;
import com.kc.goodlife.model.VideoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
/**
 * 视频的mapper
 */
@Component
public interface VideoMapper {

    @Select("SELECT count(*) From videos where DATE_FORMAT(time,'%Y-%m-%d')=DATE_FORMAT(curdate(),'%Y-%m-%d') ")
    int getVideoUpdateCount();

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
     * 最近一个月
     */
    @Select("SELECT count(*) FROM VIDEOS where DATE_SUB(CURDATE(),INTERVAL 1 MONTH)<= date(time)")
    int getMonthVideos();
    /**
     * 根据用户 搜索视频列表
     */
    @Select("SELECT * From VIDEOS where fromUserId = #{userId}")
    Page<VideoModel> getVideosByUserId(@Param("userId") Integer userId);
}