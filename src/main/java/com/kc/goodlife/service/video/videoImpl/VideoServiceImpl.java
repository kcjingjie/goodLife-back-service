package com.kc.goodlife.service.video.videoImpl;
import com.github.pagehelper.Page;
import com.kc.goodlife.mapper.video.VideoMapper;
import com.kc.goodlife.model.VideoModel;
import com.kc.goodlife.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    public int getVideoUpdateCurdate() {
        int videoUpdateCount = videoMapper.getVideoUpdateCount();
        return videoUpdateCount;
    }

    public int getVideosCount() {
        int videosCount = videoMapper.getVideosCount();
        return videosCount;
    }

    public int getTotalDays() {
        return videoMapper.getTotalDays();
    }

    public int getSevenDaysVideos() {
        return videoMapper.getSevenDaysVideos();
    }

    public int getMonthVideos() {
        return videoMapper.getMonthVideos();
    }

    public Page<VideoModel> getVideosByUserId(Integer userId) {
        return videoMapper.getVideosByUserId(userId);
    }

    public Map getVideoChangeRatio() {
        int videoUpdateCount = videoMapper.getVideoUpdateCount();
        int sevenDaysVideos = videoMapper.getSevenDaysVideos();
        int yesterdayVideoCount = videoMapper.getYesterdayVideoCount();
        int lastSevenDaysVideos = videoMapper.getLastSevenDaysVideos();
        HashMap<String, Float> objectObjectHashMap = new HashMap<String, Float>();
        if (videoUpdateCount == 0){
            objectObjectHashMap.put("day",0f);
        }else {
            float i = ((float)yesterdayVideoCount / videoUpdateCount) - 1;
            objectObjectHashMap.put("day",i);
        }
        if (sevenDaysVideos ==0){
            objectObjectHashMap.put("sevenDays",0f);
        }else{
            float i1 = ((float)lastSevenDaysVideos / sevenDaysVideos)-1;
            objectObjectHashMap.put("sevenDays",i1);
        }
        return objectObjectHashMap;
    }

    public String getTotalWatchCount() {
        String totalWatchCount = videoMapper.getTotalWatchCount();
        return totalWatchCount;
    }

    public String getTodayWatchCount() {
        String todayWatchCount = videoMapper.getTodayWatchCount();
        return todayWatchCount;
    }

    public List<Map<String, String>> getTendaysWatchCount() {
        List<Map<String, String>> tendaysWatchCount = videoMapper.getTendaysWatchCount();
        return tendaysWatchCount;
    }

    public List<Map<String, String>> getMonthWatchCount() {
        List<Map<String, String>> monthWatchCount = videoMapper.getMonthWatchCount();
        return monthWatchCount;
    }

    public List<Map<String, String>> getEachDayInMonthWatchCount() {
        return videoMapper.getEachDayInMonthWatchCount();
    }

    public List<Map<String, String>> getEachDayInMonthUploadCount() {
        return videoMapper.getEachDayInMonthUploadCount();
    }

    public List<Map<Integer,String>> getHotVideos() {
        /**
         * 采用7天的
         */
        List<Map<Integer,String>> hotVideos = videoMapper.getHotVideosInSenvenDays();
        return hotVideos;
    }

    public VideoModel getVideoModelById(int videoId) {
        VideoModel videoModel = videoMapper.getVideoModelById(videoId);
        return videoModel;
    }
}
