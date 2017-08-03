package com.xianqin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.xianqin.common.QueryRule;
import com.xianqin.dao.NfUserDao;
import com.xianqin.domain.NfUser;
@Repository("nfUserDAO")
public class NfUserDaoImpl extends CommonDaoImpl<NfUser, String> implements NfUserDao
{

    @Override
    public NfUser queryNfUserByAccount(String account)
    {
        Assert.hasText(account, "没有输入account");
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.addEqual("account", account);
        queryRule.addEqual("accountEnabled", "T");
        queryRule.addEqual("accountLocked", "F");
        queryRule.addEqual("activeFlag", "1");
        List<NfUser> nfUserList = find(queryRule);
        NfUser nfUser = null;
        if(null!=nfUserList&&!nfUserList.isEmpty()){
            nfUser =nfUserList.get(0); 
        }
        return nfUser;
    }

}
