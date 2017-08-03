package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NfAuthorityRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NF_AUTHORITY_REL")
public class NfAuthorityRel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields

	private String id;
	private String description;
	private String roleId;
	private String roleType;
	private String resourceId;
	private String resourceType;
	private String authorityType;
	private String dimensionConstraint;
	private String circumstanceId;
	private String circumstanceType;
	private String insertOper;
	private Date insertTime;
	private String updateOper;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public NfAuthorityRel() {
	}

	/** minimal constructor */
	public NfAuthorityRel(String id, String roleId, String roleType,
			String resourceId, String resourceType, String authorityType) {
		this.id = id;
		this.roleId = roleId;
		this.roleType = roleType;
		this.resourceId = resourceId;
		this.resourceType = resourceType;
		this.authorityType = authorityType;
	}

	/** full constructor */
	public NfAuthorityRel(String id, String description, String roleId,
			String roleType, String resourceId, String resourceType,
			String authorityType, String dimensionConstraint,
			String circumstanceId, String circumstanceType, String insertOper,
			Date insertTime, String updateOper, Date updateTime) {
		this.id = id;
		this.description = description;
		this.roleId = roleId;
		this.roleType = roleType;
		this.resourceId = resourceId;
		this.resourceType = resourceType;
		this.authorityType = authorityType;
		this.dimensionConstraint = dimensionConstraint;
		this.circumstanceId = circumstanceId;
		this.circumstanceType = circumstanceType;
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

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ROLE_ID", nullable = false, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_TYPE", nullable = false, length = 32)
	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Column(name = "RESOURCE_ID", nullable = false, length = 128)
	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "RESOURCE_TYPE", nullable = false, length = 32)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "AUTHORITY_TYPE", nullable = false, length = 32)
	public String getAuthorityType() {
		return this.authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	@Column(name = "DIMENSION_CONSTRAINT", length = 32)
	public String getDimensionConstraint() {
		return this.dimensionConstraint;
	}

	public void setDimensionConstraint(String dimensionConstraint) {
		this.dimensionConstraint = dimensionConstraint;
	}

	@Column(name = "CIRCUMSTANCE_ID", length = 256)
	public String getCircumstanceId() {
		return this.circumstanceId;
	}

	public void setCircumstanceId(String circumstanceId) {
		this.circumstanceId = circumstanceId;
	}

	@Column(name = "CIRCUMSTANCE_TYPE", length = 32)
	public String getCircumstanceType() {
		return this.circumstanceType;
	}

	public void setCircumstanceType(String circumstanceType) {
		this.circumstanceType = circumstanceType;
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