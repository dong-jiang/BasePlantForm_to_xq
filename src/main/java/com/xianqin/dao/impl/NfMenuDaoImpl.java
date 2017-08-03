package com.xianqin.dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.xianqin.common.QueryRule;
import com.xianqin.dao.NfMenuDao;
import com.xianqin.domain.NfMenu;
@Repository("nfMenuDao")
public class NfMenuDaoImpl extends CommonDaoImpl<NfMenu, String> implements NfMenuDao
{

    @Override
    public List<NfMenu> queryListByIdList(List<String> menuIdList)
    {
        Assert.notEmpty(menuIdList, "入参menuIdList不能为空");
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.addIn("id", menuIdList);
         return this.find(queryRule);
    }

}
