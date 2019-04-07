package com.kc.goodlife.service.system.impl;

import com.kc.goodlife.bean.context.TreeNode;
import com.kc.goodlife.model.OrgModel;
import com.kc.goodlife.mapper.system.OrgMapper;
import com.kc.goodlife.service.system.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liweiqiang
 */
@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgMapper orgMapper;

    /**
     * 查询组织树
     */
    public List<TreeNode> getTree(String keyword) {

        List<Map> org = orgMapper.getTree();

        List<TreeNode> tree = new ArrayList<TreeNode>();

        for (int i = 0; i < org.size(); i++) {
            if (((Integer) (org.get(i).get("pid"))) == 0) {
                TreeNode root = new TreeNode();
                root.setId(((Integer) (org.get(i).get("org_id"))).toString());
                root.setName((String) (org.get(i).get("org_name")));
                root.setChildren(getChilds(org, ((Integer) (org.get(i).get("org_id")))));
                tree.add(root);
            }
        }
        return tree;
    }

    List<TreeNode> getChilds(List<Map> orgs, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (Map org : orgs) {
            if ((org.get("pid")).equals(parentId)) {
                TreeNode node = new TreeNode();
                node.setId(((Integer) (org.get("org_id"))).toString());
                node.setName((String) (org.get("org_name")));
                node.setChildren(getChilds(orgs, (Integer) (org.get("org_id"))));
                children.add(node);
            }
        }
        return children;
    }

    /**
     * 查询组织信息
     */
    public OrgModel getOrg(Long orgId) {
        return orgMapper.getOrgInfo(orgId);
    }

    /**
     * 查询组织编号
     */
    public OrgModel getOrgCode(String orgCode) {
        return orgMapper.getOrgCode(orgCode);
    }

    /**
     * 更新组织信息
     */
    public OrgModel updateOrg(OrgModel orgModel) {
        orgMapper.updateOrg(orgModel);
        return orgModel;
    }

    /**
     * 添加组织信息
     */
    public OrgModel addOrg(OrgModel orgModel) {
        orgMapper.addOrg(orgModel);
        return orgModel;
    }

    /**
     * 删除组织
     */
    public void deleteOrg(Long orgId) {
        String orgIdStr = orgMapper.getOrgId(orgId);
        System.out.println(orgIdStr);
        orgMapper.deleteOrgInfo(orgIdStr);
    }

}
