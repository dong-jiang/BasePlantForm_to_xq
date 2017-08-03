package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sysrole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYSROLE", schema = "ABREINS")
public class Sysrole implements java.io.Serializable
{

    // Fields    

    private String id;

    private String roleno;

    private String rolename;

    private Date begintime;

    private Date endtime;

    private String activeflag;

    private String description;

    private String creatercode;

    private Date createrdtime;

    private String updatercode;

    private Date updatedtime;

    private Date createtimefordw;

    private Date updatetimefordw;

    // Constructors

    /** default constructor */
    public Sysrole()
    {
    }

    /** minimal constructor */
    public Sysrole(String id, String rolename, String activeflag, Date createtimefordw, Date updatetimefordw)
    {
        this.id = id;
        this.rolename = rolename;
        this.activeflag = activeflag;
        this.createtimefordw = createtimefordw;
        this.updatetimefordw = updatetimefordw;
    }

    /** full constructor */
    public Sysrole(String id, String roleno, String rolename, Date begintime, Date endtime, String activeflag, String description, String creatercode,
            Date createrdtime, String updatercode, Date updatedtime, Date createtimefordw, Date updatetimefordw)
    {
        this.id = id;
        this.roleno = roleno;
        this.rolename = rolename;
        this.begintime = begintime;
        this.endtime = endtime;
        this.activeflag = activeflag;
        this.description = description;
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

    @Column(name = "ROLENO", length = 32)
    public String getRoleno()
    {
        return this.roleno;
    }

    public void setRoleno(String roleno)
    {
        this.roleno = roleno;
    }

    @Column(name = "ROLENAME", nullable = false, length = 50)
    public String getRolename()
    {
        return this.rolename;
    }

    public void setRolename(String rolename)
    {
        this.rolename = rolename;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BEGINTIME", length = 7)
    public Date getBegintime()
    {
        return this.begintime;
    }

    public void setBegintime(Date begintime)
    {
        this.begintime = begintime;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ENDTIME", length = 7)
    public Date getEndtime()
    {
        return this.endtime;
    }

    public void setEndtime(Date endtime)
    {
        this.endtime = endtime;
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

    @Column(name = "DESCRIPTION", length = 500)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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