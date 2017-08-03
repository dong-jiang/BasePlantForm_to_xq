package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NfMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NF_MENU")
public class NfMenu implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private String name;
	private String appId;
	private String target;
	private String url;
	private String image;
	private Short orderNum;
	private String isDefault;
	private String description;
	private String parentId;
	private String title;
	private String insertOper;
	private Date insertTime;
	private String updateOper;
	private Date updateTime;
	private String isEnabled;

	// Constructors

	/** default constructor */
	public NfMenu() {
	}

	/** minimal constructor */
	public NfMenu(String id, String name, String isEnabled) {
		this.id = id;
		this.name = name;
		this.isEnabled = isEnabled;
	}

	/** full constructor */
	public NfMenu(String id, String name, String appId, String target,
			String url, String image, Short orderNum, String isDefault,
			String description, String parentId, String title,
			String insertOper, Date insertTime, String updateOper,
			Date updateTime, String isEnabled) {
		this.id = id;
		this.name = name;
		this.appId = appId;
		this.target = target;
		this.url = url;
		this.image = image;
		this.orderNum = orderNum;
		this.isDefault = isDefault;
		this.description = description;
		this.parentId = parentId;
		this.title = title;
		this.insertOper = insertOper;
		this.insertTime = insertTime;
		this.updateOper = updateOper;
		this.updateTime = updateTime;
		this.isEnabled = isEnabled;
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

	@Column(name = "NAME", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "APP_ID", length = 32)
	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name = "TARGET", length = 32)
	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(name = "URL", length = 256)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "IMAGE", length = 256)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "ORDER_NUM", precision = 3, scale = 0)
	public Short getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Short orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "IS_DEFAULT", length = 1)
	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Column(name = "DESCRIPTION", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PARENT_ID", length = 32)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "TITLE", length = 32)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Column(name = "IS_ENABLED", nullable = false, length = 1)
	public String getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

}