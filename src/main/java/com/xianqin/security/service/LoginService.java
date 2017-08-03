package com.xianqin.security.service;

import java.util.List;


import com.xianqin.domain.NfUser;
import com.xianqin.security.view.MenuVO;

public interface LoginService {

    public List<String> queryRolesByUserId(String id);
	
	public List<String> queryMenuIdListByRoleList(List<String> roleIdList);
	
	public NfUser queryNfUserByAccount(String account);

	public List<MenuVO> queryMenuListByIdList(List<String> menuIdList);
    
	public List<MenuVO> queryMenuListByAccount(String account);
    
}
