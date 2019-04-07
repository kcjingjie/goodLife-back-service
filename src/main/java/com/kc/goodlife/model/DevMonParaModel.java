package com.kc.goodlife.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevMonParaModel {

    private  Long id;

    private Long deviceId;

    private String deviceName;

    private String paraName;

    private String paraId;

    private int paraDataType;

    private String dataName;

    private String paraUnit;

    private int paraType;

    private String typeName;

    private String remark;

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
