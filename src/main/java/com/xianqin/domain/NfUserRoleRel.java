package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NfUserRoleRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NF_USER_ROLE_REL")
public class NfUserRoleRel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private String id;
	private String roleId;
	private String userId;
	private String activeFlag;
	private String insertOper;
	private Date insertTime;
	private String updateOper;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public NfUserRoleRel() {
	}

	/** minimal constructor */
	public NfUserRoleRel(String id, String roleId, String userId) {
		this.id = id;
		this.roleId = roleId;
		this.userId = userId;
	}

	/** full constructor */
	public NfUserRoleRel(String id, String roleId, String userId,
			String activeFlag, String insertOper, Date insertTime,
			String updateOper, Date updateTime) {
		this.id = id;
		this.roleId = roleId;
		this.userId = userId;
		this.activeFlag = activeFlag;
		this.insertOper = insertOper;
		this.insertTime = insertTime;
		this.updateOper = updateOper;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ROLE_ID", nullable = false, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "USER_ID", nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ACTIVE_FLAG", length = 1)
	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Column(name = "INSERT_OPER", length = 32)
	public String getInsertOper() {
		return this.insertOper;
	}

	public void setInsertOper(String insertOper) {
		this.insertOper = insertOper;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSERT_TIME", length = 7)
	public Date getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "UPDATE_OPER", length = 32)
	public String getUpdateOper() {
		return this.updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}