package com.xianqin.dao;

import java.util.List;


import com.xianqin.domain.NfUserRoleRel;

public interface NfUserRoleRelDao
{
    List<NfUserRoleRel> queryUserRoleRelListbyUserId(String userId);
}
