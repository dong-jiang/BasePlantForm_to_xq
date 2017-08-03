package com.xianqin.dao;

import com.xianqin.domain.NfUser;


public interface NfUserDao {

    public NfUser queryNfUserByAccount(String account);
    
    //获取
    /*此类负责养护校验四通*/
    //根据ID获取账号
    //登录流程：登录 --认证、获取角色信息，获取账户通用信息、获取机构信息、获取菜单信息
    //缓存数据 角色信息、菜单信息、机构信息 均丢入缓存
    
    //用户增删改查
    //角色增删改查
    //机构增删改查
    //菜单增删改查
    //
    
}
