package com.kc.goodlife.service.system.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kc.goodlife.model.UnitModel;
import com.kc.goodlife.mapper.system.UnitMapper;
import com.kc.goodlife.service.system.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitMapper unitMapper;
    /**
     * 批量获取单位信息
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) unitMapper.getList(keyword);
    }

    /**
     * 获取单位信息
     * @param unitId
     * @return
     */
    public UnitModel getUnitDetails(Long unitId){
        return unitMapper.getUnitInfo(unitId);
    }

    /**
     * 修改单位信息
     * @param unitModel
     * @return
     */
    public UnitModel updateUnitDetails(UnitModel unitModel){
        unitMapper.updateUnitDetails(unitModel);
        return unitModel;
    }

    /**
     * 添加单位信息
     * @param unitModel
     * @return
     */
    public UnitModel addUnit(UnitModel unitModel){
        unitMapper.addUnit(unitModel);
        return unitModel;
    }

    /**
     * 删除单位
     * @param unitIds
     * @return
     */
    public boolean deleteUnitList(String unitIds){
        unitMapper.deleteUnitList(unitIds);
        return true;
    }

    /**
     * 检验编码是否重复
     *
     * @param code
     * @return
     */
    public List<UnitModel> getUnitByCode(String code){
        return unitMapper.getUnitByCode(code);
    }
}
