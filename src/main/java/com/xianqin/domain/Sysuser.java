package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sysuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYSUSER", schema = "ABREINS")
public class Sysuser implements java.io.Serializable
{

    // Fields    

    private String id;

    private String useraccount;

    private String username;

    private String password;

    private String comcode;

    private String sex;

    private Date birthdate;

    private String mailaddress;

    private String mobilephone;

    private String homephone;

    private String officephone;

    private String fax;

    private String homeaddress;

    private String credentialstype;

    private String credentialsnumber;

    private String description;

    private String activeflag;

    private String creatercode;

    private Date createrdtime;

    private String updatercode;

    private Date updatedtime;

    private Date createtimefordw;

    private Date updatetimefordw;

    // Constructors

    /** default constructor */
    public Sysuser()
    {
    }

    /** minimal constructor */
    public Sysuser(String id, String useraccount, String username, String password, String sex, String activeflag, Date createtimefordw, Date updatetimefordw)
    {
        this.id = id;
        this.useraccount = useraccount;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.activeflag = activeflag;
        this.createtimefordw = createtimefordw;
        this.updatetimefordw = updatetimefordw;
    }

    /** full constructor */
    public Sysuser(String id, String useraccount, String username, String password, String comcode, String sex, Date birthdate, String mailaddress,
            String mobilephone, String homephone, String officephone, String fax, String homeaddress, String credentialstype, String credentialsnumber,
            String description, String activeflag, String creatercode, Date createrdtime, String updatercode, Date updatedtime, Date createtimefordw,
            Date updatetimefordw)
    {
        this.id = id;
        this.useraccount = useraccount;
        this.username = username;
        this.password = password;
        this.comcode = comcode;
        this.sex = sex;
        this.birthdate = birthdate;
        this.mailaddress = mailaddress;
        this.mobilephone = mobilephone;
        this.homephone = homephone;
        this.officephone = officephone;
        this.fax = fax;
        this.homeaddress = homeaddress;
        this.credentialstype = credentialstype;
        this.credentialsnumber = credentialsnumber;
        this.description = description;
        this.activeflag = activeflag;
        this.creatercode = creatercode;
        this.createrdtime = createrdtime;
        this.updatercode = updatercode;
        this.updatedtime = updatedtime;
        this.createtimefordw = createtimefordw;
        this.updatetimefordw = updatetimefordw;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Column(name = "USERACCOUNT", nullable = false, length = 32)
    public String getUseraccount()
    {
        return this.useraccount;
    }

    public void setUseraccount(String useraccount)
    {
        this.useraccount = useraccount;
    }

    @Column(name = "USERNAME", nullable = false, length = 50)
    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false, length = 32)
    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Column(name = "COMCODE", length = 8)
    public String getComcode()
    {
        return this.comcode;
    }

    public void setComcode(String comcode)
    {
        this.comcode = comcode;
    }

    @Column(name = "SEX", nullable = false, length = 1)
    public String getSex()
    {
        return this.sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDATE", length = 7)
    public Date getBirthdate()
    {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }

    @Column(name = "MAILADDRESS", length = 100)
    public String getMailaddress()
    {
        return this.mailaddress;
    }

    public void setMailaddress(String mailaddress)
    {
        this.mailaddress = mailaddress;
    }

    @Column(name = "MOBILEPHONE", length = 16)
    public String getMobilephone()
    {
        return this.mobilephone;
    }

    public void setMobilephone(String mobilephone)
    {
        this.mobilephone = mobilephone;
    }

    @Column(name = "HOMEPHONE", length = 16)
    public String getHomephone()
    {
        return this.homephone;
    }

    public void setHomephone(String homephone)
    {
        this.homephone = homephone;
    }

    @Column(name = "OFFICEPHONE", length = 16)
    public String getOfficephone()
    {
        return this.officephone;
    }

    public void setOfficephone(String officephone)
    {
        this.officephone = officephone;
    }

    @Column(name = "FAX", length = 16)
    public String getFax()
    {
        return this.fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    @Column(name = "HOMEADDRESS")
    public String getHomeaddress()
    {
        return this.homeaddress;
    }

    public void setHomeaddress(String homeaddress)
    {
        this.homeaddress = homeaddress;
    }

    @Column(name = "CREDENTIALSTYPE", length = 2)
    public String getCredentialstype()
    {
        return this.credentialstype;
    }

    public void setCredentialstype(String credentialstype)
    {
        this.credentialstype = credentialstype;
    }

    @Column(name = "CREDENTIALSNUMBER", length = 100)
    public String getCredentialsnumber()
    {
        return this.credentialsnumber;
    }

    public void setCredentialsnumber(String credentialsnumber)
    {
        this.credentialsnumber = credentialsnumber;
    }

    @Column(name = "DESCRIPTION", length = 500)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Column(name = "ACTIVEFLAG", nullable = false, length = 1)
    public String getActiveflag()
    {
        return this.activeflag;
    }

    public void setActiveflag(String activeflag)
    {
        this.activeflag = activeflag;
    }

    @Column(name = "CREATERCODE", length = 32)
    public String getCreatercode()
    {
        return this.creatercode;
    }

    public void setCreatercode(String creatercode)
    {
        this.creatercode = creatercode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATERDTIME", length = 7)
    public Date getCreaterdtime()
    {
        return this.createrdtime;
    }

    public void setCreaterdtime(Date createrdtime)
    {
        this.createrdtime = createrdtime;
    }

    @Column(name = "UPDATERCODE", length = 32)
    public String getUpdatercode()
    {
        return this.updatercode;
    }

    public void setUpdatercode(String updatercode)
    {
        this.updatercode = updatercode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATEDTIME", length = 7)
    public Date getUpdatedtime()
    {
        return this.updatedtime;
    }

    public void setUpdatedtime(Date updatedtime)
    {
        this.updatedtime = updatedtime;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATETIMEFORDW", nullable = false, length = 7)
    public Date getCreatetimefordw()
    {
        return this.createtimefordw;
    }

    public void setCreatetimefordw(Date createtimefordw)
    {
        this.createtimefordw = createtimefordw;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATETIMEFORDW", nullable = false, length = 7)
    public Date getUpdatetimefordw()
    {
        return this.updatetimefordw;
    }

    public void setUpdatetimefordw(Date updatetimefordw)
    {
        this.updatetimefordw = updatetimefordw;
    }

}