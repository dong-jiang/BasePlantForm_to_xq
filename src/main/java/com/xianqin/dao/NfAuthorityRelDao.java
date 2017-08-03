package com.xianqin.dao;

import java.util.List;


import com.xianqin.domain.NfAuthorityRel;

public interface NfAuthorityRelDao
{
    List<NfAuthorityRel> queryListByRoleIds(List<String> roleIdList);
    
}
