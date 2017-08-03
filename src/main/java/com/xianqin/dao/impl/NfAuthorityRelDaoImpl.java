package com.xianqin.dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.xianqin.common.QueryRule;
import com.xianqin.dao.NfAuthorityRelDao;
import com.xianqin.domain.NfAuthorityRel;
@Repository("nfAuthorityRelDao")
public class NfAuthorityRelDaoImpl extends CommonDaoImpl<NfAuthorityRel, String> implements NfAuthorityRelDao
{

    @Override
    public List<NfAuthorityRel> queryListByRoleIds(List<String> roleIdList)
    {
        Assert.notEmpty(roleIdList,"入参roleIdList不能为空");;
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.addIn("roleId", roleIdList);
        queryRule.addEqual("roleType", "1");
        queryRule.addEqual("resourceType", "1");
        return find(queryRule);
    }

}
