package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sysroleuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYSROLEUSER", schema = "ABREINS")
public class Sysroleuser implements java.io.Serializable
{

    // Fields    

    private String id;

    private String roleid;

    private String userid;

    private String activeflag;

    private String creatercode;

    private Date createrdtime;

    private String updatercode;

    private Date updatedtime;

    private Date createtimefordw;

    private Date updatetimefordw;

    // Constructors

    /** default constructor */
    public Sysroleuser()
    {
    }

    /** minimal constructor */
    public Sysroleuser(String id, String roleid, String userid, String activeflag, Date createtimefordw, Date updatetimefordw)
    {
        this.id = id;
        this.roleid = roleid;
        this.userid = userid;
        this.activeflag = activeflag;
        this.createtimefordw = createtimefordw;
        this.updatetimefordw = updatetimefordw;
    }

    /** full constructor */
    public Sysroleuser(String id, String roleid, String userid, String activeflag, String creatercode, Date createrdtime, String updatercode, Date updatedtime,
            Date createtimefordw, Date updatetimefordw)
    {
        this.id = id;
        this.roleid = roleid;
        this.userid = userid;
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

    @Column(name = "ROLEID", nullable = false, length = 32)
    public String getRoleid()
    {
        return this.roleid;
    }

    public void setRoleid(String roleid)
    {
        this.roleid = roleid;
    }

    @Column(name = "USERID", nullable = false, length = 32)
    public String getUserid()
    {
        return this.userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
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