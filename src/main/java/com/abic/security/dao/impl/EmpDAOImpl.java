package com.abic.security.dao.impl;

import org.springframework.stereotype.Repository;


import com.abic.security.domain.Emp;
import com.xianqin.dao.impl.CommonDaoImpl;

@Repository("empDAO")
public class EmpDAOImpl extends CommonDaoImpl<Emp,Integer>{

}
