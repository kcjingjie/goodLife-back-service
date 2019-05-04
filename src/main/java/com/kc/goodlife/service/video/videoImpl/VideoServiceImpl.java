package com.kc.goodlife.service.video.videoImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.kc.goodlife.mapper.video.VideoMapper;
import com.kc.goodlife.model.VideoModel;
import com.kc.goodlife.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
