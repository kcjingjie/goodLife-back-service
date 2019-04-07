package com.kc.goodlife.service.system;

import com.github.pagehelper.Page;
import com.kc.goodlife.bean.context.TreeNode;
import com.kc.goodlife.model.SysRoleModel;

import java.util.List;

public interface SysRoleService {
    Page getRoleList(String dataName, int pageNum, int pageSize);

    SysRoleModel addRole(SysRoleModel sysRoleModel);

    SysRoleModel getRoleInfo(Long id);

    SysRoleModel updateRoleInfo(SysRoleModel sysRoleModel);

    boolean deleteRoleList(String idList);

    List<TreeNode> getRoleTree(Long roleId);

    boolean updateRoleModule(Long roleId,String moudleIds,Long userId);
}
