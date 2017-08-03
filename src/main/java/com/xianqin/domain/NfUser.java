package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NfUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NF_USER")
public class NfUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private String account;
	private String name;
	private String type;
	private String password;
    private String salt;
	private String description;
	private String orgcode;
	private Date passwordChangedDate;
	private String accountEnabled;
	private String accountLocked;
	private String accountLockedReason;
	private String sex;
	private Date birthdate;
	private String nationality;
	private String credentialsType;
	private String credentialsNumber;
	private String email;
	private String mobileTelephone;
	private String homeTelephone;
	private String officeTelephone;
	private String fax;
	private String homeAddress;
	private String insertOper;
	private Date insertTime;
	private String updateOper;
	private Date updateTime;
	private String activeFlag;
	private Date deletionDate;

	// Constructors

	/** default constructor */
	public NfUser() {
	}

	/** minimal constructor */
	public NfUser(String id, String name, String accountEnabled,
			String accountLocked) {
		this.id = id;
		this.name = name;
		this.accountEnabled = accountEnabled;
		this.accountLocked = accountLocked;
	}

	/** full constructor */
	public NfUser(String id, String account, String name, String type,
			String password, String salt,String description, String orgcode,
			Date passwordChangedDate, String accountEnabled,
			String accountLocked, String accountLockedReason, String sex,
			Date birthdate, String nationality, String credentialsType,
			String credentialsNumber, String email, String mobileTelephone,
			String homeTelephone, String officeTelephone, String fax,
			String homeAddress, String insertOper, Date insertTime,
			String updateOper, Date updateTime, String activeFlag,
			Date deletionDate) {
		this.id = id;
		this.account = account;
		this.name = name;
		this.type = type;
		this.password = password;
        this.salt = salt;
		this.description = description;
		this.orgcode = orgcode;
		this.passwordChangedDate = passwordChangedDate;
		this.accountEnabled = accountEnabled;
		this.accountLocked = accountLocked;
		this.accountLockedReason = accountLockedReason;
		this.sex = sex;
		this.birthdate = birthdate;
		this.nationality = nationality;
		this.credentialsType = credentialsType;
		this.credentialsNumber = credentialsNumber;
		this.email = email;
		this.mobileTelephone = mobileTelephone;
		this.homeTelephone = homeTelephone;
		this.officeTelephone = officeTelephone;
		this.fax = fax;
		this.homeAddress = homeAddress;
		this.insertOper = insertOper;
		this.insertTime = insertTime;
		this.updateOper = updateOper;
		this.updateTime = updateTime;
		this.activeFlag = activeFlag;
		this.deletionDate = deletionDate;
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

	@Column(name = "ACCOUNT", length = 32)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", length = 32)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "PASSWORD", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    @Column(name = "SALT")
    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    @Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ORGCODE", length = 32)
	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PASSWORD_CHANGED_DATE", length = 7)
	public Date getPasswordChangedDate() {
		return this.passwordChangedDate;
	}

	public void setPasswordChangedDate(Date passwordChangedDate) {
		this.passwordChangedDate = passwordChangedDate;
	}

	@Column(name = "ACCOUNT_ENABLED", nullable = false, length = 1)
	public String getAccountEnabled() {
		return this.accountEnabled;
	}

	public void setAccountEnabled(String accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	@Column(name = "ACCOUNT_LOCKED", nullable = false, length = 1)
	public String getAccountLocked() {
		return this.accountLocked;
	}

	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}

	@Column(name = "ACCOUNT_LOCKED_REASON", length = 1)
	public String getAccountLockedReason() {
		return this.accountLockedReason;
	}

	public void setAccountLockedReason(String accountLockedReason) {
		this.accountLockedReason = accountLockedReason;
	}

	@Column(name = "SEX", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", length = 7)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "NATIONALITY", length = 2)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "CREDENTIALS_TYPE", length = 32)
	public String getCredentialsType() {
		return this.credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

	@Column(name = "CREDENTIALS_NUMBER", length = 32)
	public String getCredentialsNumber() {
		return this.credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MOBILE_TELEPHONE", length = 16)
	public String getMobileTelephone() {
		return this.mobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	@Column(name = "HOME_TELEPHONE", length = 16)
	public String getHomeTelephone() {
		return this.homeTelephone;
	}

	public void setHomeTelephone(String homeTelephone) {
		this.homeTelephone = homeTelephone;
	}

	@Column(name = "OFFICE_TELEPHONE", length = 16)
	public String getOfficeTelephone() {
		return this.officeTelephone;
	}

	public void setOfficeTelephone(String officeTelephone) {
		this.officeTelephone = officeTelephone;
	}

	@Column(name = "FAX", length = 16)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "HOME_ADDRESS", length = 256)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
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

	@Column(name = "ACTIVE_FLAG", length = 1)
	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DELETION_DATE", length = 7)
	public Date getDeletionDate() {
		return this.deletionDate;
	}

	public void setDeletionDate(Date deletionDate) {
		this.deletionDate = deletionDate;
	}

}