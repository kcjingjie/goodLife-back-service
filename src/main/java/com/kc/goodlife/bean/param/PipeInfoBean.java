package com.kc.goodlife.bean.param;

import lombok.Data;

@Data
public class PipeInfoBean {
    private Long deviceId;

    private Long unitId;

    private Long imageId;

    private String deviceAlias;

    private Long deviceType;

    private  Long deviceEquip;

    private int pressurePipe;

    private String deviceName;

    private String customCode;

    private String deviceDesc;

    private int status;


}
