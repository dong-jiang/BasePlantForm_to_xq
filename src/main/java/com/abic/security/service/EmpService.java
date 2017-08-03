package com.abic.security.service;

import java.util.List;

import com.abic.security.domain.Emp;

public interface EmpService {

	public void saveEmp(Emp emp);

	public void updateEmp(Emp emp);

	public Emp findEmpById(int id);

	public void deleteEmp(Emp emp);

	public List<Emp> findAllList();
	
	public boolean isEmpExist(Emp emp);
	
	public void deleteAllEmps();

}