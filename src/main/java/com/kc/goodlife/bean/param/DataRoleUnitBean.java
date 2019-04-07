package com.kc.goodlife.bean.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author liuyan
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataRoleUnitBean {

    private String unitId;

    private Long droleId;

}
