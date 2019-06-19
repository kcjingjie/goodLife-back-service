package com.kc.goodlife.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoModel {

    private int id;

    private String videoUrl;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private String title;

    private String desc;

    private int scope;

    private int fromUserId;

    private String coverPath;


}
