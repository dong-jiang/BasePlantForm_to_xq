package com.abic.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abic.security.dao.impl.EmpDAOImpl;
import com.abic.security.domain.Emp;
import com.abic.security.service.EmpService;

@Transactional //启用事务机制
@Service("empService")
public class EmpServiceImpl implements EmpService {

	@Resource
	private EmpDAOImpl empDAO;
	
	public void saveEmp(Emp emp) {
		empDAO.save(emp);
	}

	public void updateEmp(Emp emp) {
		empDAO.update(emp);
	}

	public Emp findEmpById(int id) {
		return empDAO.get(id);
	}

	public void deleteEmp(Emp emp) {
		empDAO.delete(emp);
	}

	public List<Emp> findAllList() {
		return empDAO.findByHql("from Emp");
	}

	public boolean isEmpExist(Emp emp) {
		return empDAO.exists(emp.getEmpno());
	}

	public void deleteAllEmps() {
		empDAO.deleteAll(findAllList());
		
	}
}