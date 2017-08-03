package com.xianqin.dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.xianqin.common.QueryRule;
import com.xianqin.dao.NfUserRoleRelDao;
import com.xianqin.domain.NfUserRoleRel;
@Repository("nfUserRoleRelDao")
public class NfUserRoleRelDaoImpl extends CommonDaoImpl<NfUserRoleRel, String> implements NfUserRoleRelDao
{

    @Override
    public List<NfUserRoleRel> queryUserRoleRelListbyUserId(String userId)
    {
        Assert.hasText(userId,"没有输入userId");
        QueryRule queryRule =QueryRule.getInstance();
        queryRule.addEqual("userId", userId);
        queryRule.addEqual("activeFlag", "1");
        return super.find(queryRule);
    }

}
