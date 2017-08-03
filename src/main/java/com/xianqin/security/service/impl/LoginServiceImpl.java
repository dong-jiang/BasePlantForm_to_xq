package com.xianqin.security.service.impl;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xianqin.dao.NfAuthorityRelDao;
import com.xianqin.dao.NfMenuDao;
import com.xianqin.dao.NfUserDao;
import com.xianqin.dao.NfUserRoleRelDao;
import com.xianqin.domain.NfAuthorityRel;
import com.xianqin.domain.NfMenu;
import com.xianqin.domain.NfUser;
import com.xianqin.domain.NfUserRoleRel;
import com.xianqin.security.service.LoginService;
import com.xianqin.security.view.MenuSort;
import com.xianqin.security.view.MenuVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService
{

    @Resource
    private NfUserDao nfUserDao;

    @Resource
    private NfUserRoleRelDao nfUserRoleRelDao;

    @Resource
    private NfAuthorityRelDao nfAuthorityRelDao;

    @Resource
    private NfMenuDao nfMenuDao;

    @Override
    public List<String> queryRolesByUserId(String id)
    {
        List<NfUserRoleRel> nfUserRoleRelList = nfUserRoleRelDao.queryUserRoleRelListbyUserId(id);
        List<String> roleIdList = new ArrayList<String>();
        for(NfUserRoleRel nfUserRoleRel:nfUserRoleRelList){
            roleIdList.add(nfUserRoleRel.getRoleId());
        }
        return roleIdList;
    }

    @Override
    public List<String> queryMenuIdListByRoleList(List<String> roleIdList)
    {
        List<NfAuthorityRel> nfAuthorityRelList = nfAuthorityRelDao.queryListByRoleIds(roleIdList);
        List<String> menuIdList = new ArrayList<String>();
        for(NfAuthorityRel nfAuthorityRel:nfAuthorityRelList){
            if(!menuIdList.contains(nfAuthorityRel.getResourceId())){
                menuIdList.add(nfAuthorityRel.getResourceId());
            }
        }
        return menuIdList;
    }

    @Override
    public NfUser queryNfUserByAccount(String account)
    {
        return nfUserDao.queryNfUserByAccount(account);
    }

    @Override
    public List<MenuVO> queryMenuListByIdList(List<String> menuIdList)
    {
        List<NfMenu> nfMenuList = nfMenuDao.queryListByIdList(menuIdList);
        List<MenuVO> menuVOList = new ArrayList<MenuVO>();
        for(NfMenu nfMenu:nfMenuList){
            MenuVO  menuVO = new MenuVO();
            menuVO.setId(nfMenu.getId());
            menuVO.setIcon(nfMenu.getImage());
            menuVO.setIsDefault(nfMenu.getIsDefault());
            menuVO.setIsleaf(true);
            menuVO.setLevel("1");
            menuVO.setName(nfMenu.getName());
            menuVO.setOrderNum(nfMenu.getOrderNum());
            menuVO.setPid(nfMenu.getParentId());
            menuVO.setTitle(nfMenu.getTitle());
            menuVO.setUrl(nfMenu.getUrl());
            menuVOList.add(menuVO);
        }
        
        //附带根节点菜单，如菜单不在树上，则不绑进菜单列表中
        return MenuSort.sortShowMenu(menuVOList);
    }

    @Override
    public List<MenuVO> queryMenuListByAccount(String account)
    {
      //认证登录用户
        NfUser nfUser = queryNfUserByAccount(account);
        //返回用户角色列表
        List<String> roleIdList = queryRolesByUserId(nfUser.getId());
        //根据用户角色列表查询相应权限
        List<String> menuIdList = queryMenuIdListByRoleList(roleIdList);
        //设置返回数据 user\role\menu\org
        return queryMenuListByIdList(menuIdList);
    }

    

    

}
