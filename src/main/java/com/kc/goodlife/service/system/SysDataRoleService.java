package com.kc.goodlife.service.system;

import com.github.pagehelper.Page;
import com.kc.goodlife.bean.context.TreeNode;
import com.kc.goodlife.model.DataUnitModel;
import com.kc.goodlife.model.SysDataRoleModel;

import java.util.List;

public interface SysDataRoleService {

    Page getDataRoleList(String dataName, int pageNum, int pageSize);

    SysDataRoleModel addDataRole(SysDataRoleModel sysDataRoleModel);

    SysDataRoleModel getDataRoleInfo(Long id);

    SysDataRoleModel updateDataRoleInfo(SysDataRoleModel sysDataRoleModel);

    boolean deleteDataRoleList(String idList);

    List<TreeNode> getOrgUnitTree();

    List<DataUnitModel> getDataUnit(Long droleId);

    boolean putDataUnit(String UnitIds, Long userId, Long droleId);

}
