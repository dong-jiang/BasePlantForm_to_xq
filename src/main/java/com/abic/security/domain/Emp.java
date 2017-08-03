package com.abic.security.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 雇员
 */
@Entity
@Table(name ="emp")
public class Emp implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer empno; // 雇员编号
	private String ename; // 雇员姓名
	private String job; // 工作
	private Integer mgr; // 上级雇员的编号
	private Date hiredate; // 入职日期
	private Double sal; // 薪水
	private Double comm; // 奖金
	private Integer deptno; // 部门编号

	public Emp() {
	}

	public Emp(Integer empno, String ename, Date hiredate, Double sal) {
		this.empno = empno;
		this.ename = ename;
		this.hiredate = hiredate;
		this.sal = sal;
	}

	@Id
	@Column(name = "empno")
	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	@Column(name = "ename")
	public String getEname() {
		return ename;
	}

	
	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "job")
	public String getJob() {
		return job;
	}

	
	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "mgr")
	public Integer getMgr() {
		return mgr;
	}

	
	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	@Column(name = "hiredate")
	public Date getHiredate() {
		return hiredate;
	}

	
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	@Column(name = "sal")
	public Double getSal() {
		return sal;
	}

	
	public void setSal(Double sal) {
		this.sal = sal;
	}

	@Column(name = "comm")
	public Double getComm() {
		return comm;
	}

	
	public void setComm(Double comm) {
		this.comm = comm;
	}

	@Column(name = "deptno")
	public Integer getDeptno() {
		return deptno;
	}

	
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job
				+ ", mgr=" + mgr + ", hiredate=" + hiredate + ", sal=" + sal
				+ ", comm=" + comm + ", deptno=" + deptno + "]";
	}

}
