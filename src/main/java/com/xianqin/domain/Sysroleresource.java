package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sysroleresource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYSROLERESOURCE", schema = "ABREINS")
public class Sysroleresource implements java.io.Serializable
{

    // Fields    

    private String id;

    private String description;

    private String roleid;

    private String roletype;

    private String resourceid;

    private String resourcetype;

    private String authoritytype;

    private String dimensionconstraint;

    private String circumstanceid;

    private String circumstancetype;

    private String creatercode;

    private Date createrdtime;

    private String updatercode;

    private Date updatedtime;

    private Date createtimefordw;

    private Date updatetimefordw;

    // Constructors

    /** default constructor */
    public Sysroleresource()
    {
    }

    /** minimal constructor */
    public Sysroleresource(String id, String roleid, String roletype, String resourceid, String resourcetype, String authoritytype, Date createtimefordw,
            Date updatetimefordw)
    {
        this.id = id;
        this.roleid = roleid;
        this.roletype = roletype;
        this.resourceid = resourceid;
        this.resourcetype = resourcetype;
        this.authoritytype = authoritytype;
        this.createtimefordw = createtimefordw;
        this.updatetimefordw = updatetimefordw;
    }

    /** full constructor */
    public Sysroleresource(String id, String description, String roleid, String roletype, String resourceid, String resourcetype, String authoritytype,
            String dimensionconstraint, String circumstanceid, String circumstancetype, String creatercode, Date createrdtime, String updatercode,
            Date updatedtime, Date createtimefordw, Date updatetimefordw)
    {
        this.id = id;
        this.description = description;
        this.roleid = roleid;
        this.roletype = roletype;
        this.resourceid = resourceid;
        this.resourcetype = resourcetype;
        this.authoritytype = authoritytype;
        this.dimensionconstraint = dimensionconstraint;
        this.circumstanceid = circumstanceid;
        this.circumstancetype = circumstancetype;
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

    @Column(name = "DESCRIPTION")
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    @Column(name = "ROLETYPE", nullable = false, length = 32)
    public String getRoletype()
    {
        return this.roletype;
    }

    public void setRoletype(String roletype)
    {
        this.roletype = roletype;
    }

    @Column(name = "RESOURCEID", nullable = false, length = 128)
    public String getResourceid()
    {
        return this.resourceid;
    }

    public void setResourceid(String resourceid)
    {
        this.resourceid = resourceid;
    }

    @Column(name = "RESOURCETYPE", nullable = false, length = 32)
    public String getResourcetype()
    {
        return this.resourcetype;
    }

    public void setResourcetype(String resourcetype)
    {
        this.resourcetype = resourcetype;
    }

    @Column(name = "AUTHORITYTYPE", nullable = false, length = 32)
    public String getAuthoritytype()
    {
        return this.authoritytype;
    }

    public void setAuthoritytype(String authoritytype)
    {
        this.authoritytype = authoritytype;
    }

    @Column(name = "DIMENSIONCONSTRAINT", length = 32)
    public String getDimensionconstraint()
    {
        return this.dimensionconstraint;
    }

    public void setDimensionconstraint(String dimensionconstraint)
    {
        this.dimensionconstraint = dimensionconstraint;
    }

    @Column(name = "CIRCUMSTANCEID", length = 256)
    public String getCircumstanceid()
    {
        return this.circumstanceid;
    }

    public void setCircumstanceid(String circumstanceid)
    {
        this.circumstanceid = circumstanceid;
    }

    @Column(name = "CIRCUMSTANCETYPE", length = 32)
    public String getCircumstancetype()
    {
        return this.circumstancetype;
    }

    public void setCircumstancetype(String circumstancetype)
    {
        this.circumstancetype = circumstancetype;
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