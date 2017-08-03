package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NfRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NF_ROLE")
public class NfRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private String name;
	private String description;
	private String activeFlag;
	private Date timeBegin;
	private Date timeEnd;
	private String insertOper;
	private Date insertTime;
	private String updateOper;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public NfRole() {
	}

	/** minimal constructor */
	public NfRole(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public NfRole(String id, String name, String description,
			String activeFlag, Date timeBegin, Date timeEnd, String insertOper,
			Date insertTime, String updateOper, Date updateTime) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.activeFlag = activeFlag;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
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

	@Column(name = "NAME", nullable = false, length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ACTIVE_FLAG", length = 32)
	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TIME_BEGIN", length = 7)
	public Date getTimeBegin() {
		return this.timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TIME_END", length = 7)
	public Date getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
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