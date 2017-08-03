package com.xianqin.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sysmenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYSMENU", schema = "ABREINS")
public class Sysmenu implements java.io.Serializable
{

    // Fields    

    private String id;

    private String menuname;

    private String appid;

    private String target;

    private String url;

    private String icon;

    private Short ordernum;

    private String isdefault;

    private String description;

    private String isleaf;

    private String level;

    private String parentid;

    private String title;

    private String isenabled;

    private String creatercode;

    private Date createrdtime;

    private String updatercode;

    private Date updatedtime;

    private Date createtimefordw;

    private Date updatetimefordw;

    // Constructors

    /** default constructor */
    public Sysmenu()
    {
    }

    /** minimal constructor */
    public Sysmenu(String id, String menuname, String isenabled, Date createtimefordw, Date updatetimefordw)
    {
        this.id = id;
        this.menuname = menuname;
        this.isenabled = isenabled;
        this.createtimefordw = createtimefordw;
        this.updatetimefordw = updatetimefordw;
    }

    /** full constructor */
    public Sysmenu(String id, String menuname, String appid, String target, String url, String icon, Short ordernum, String isdefault, String description,
            String isleaf, String level, String parentid, String title, String isenabled, String creatercode, Date createrdtime, String updatercode,
            Date updatedtime, Date createtimefordw, Date updatetimefordw)
    {
        this.id = id;
        this.menuname = menuname;
        this.appid = appid;
        this.target = target;
        this.url = url;
        this.icon = icon;
        this.ordernum = ordernum;
        this.isdefault = isdefault;
        this.description = description;
        this.isleaf = isleaf;
        this.level = level;
        this.parentid = parentid;
        this.title = title;
        this.isenabled = isenabled;
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

    @Column(name = "MENUNAME", nullable = false, length = 32)
    public String getMenuname()
    {
        return this.menuname;
    }

    public void setMenuname(String menuname)
    {
        this.menuname = menuname;
    }

    @Column(name = "APPID", length = 32)
    public String getAppid()
    {
        return this.appid;
    }

    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    @Column(name = "TARGET", length = 32)
    public String getTarget()
    {
        return this.target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    @Column(name = "URL")
    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Column(name = "ICON")
    public String getIcon()
    {
        return this.icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    @Column(name = "ORDERNUM", precision = 3, scale = 0)
    public Short getOrdernum()
    {
        return this.ordernum;
    }

    public void setOrdernum(Short ordernum)
    {
        this.ordernum = ordernum;
    }

    @Column(name = "ISDEFAULT", length = 1)
    public String getIsdefault()
    {
        return this.isdefault;
    }

    public void setIsdefault(String isdefault)
    {
        this.isdefault = isdefault;
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

    @Column(name = "ISLEAF", length = 1)
    public String getIsleaf()
    {
        return this.isleaf;
    }

    public void setIsleaf(String isleaf)
    {
        this.isleaf = isleaf;
    }

    @Column(name = "Level", length = 1)
    public String getLevel()
    {
        return this.level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    @Column(name = "PARENTID", length = 32)
    public String getParentid()
    {
        return this.parentid;
    }

    public void setParentid(String parentid)
    {
        this.parentid = parentid;
    }

    @Column(name = "TITLE", length = 32)
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Column(name = "ISENABLED", nullable = false, length = 1)
    public String getIsenabled()
    {
        return this.isenabled;
    }

    public void setIsenabled(String isenabled)
    {
        this.isenabled = isenabled;
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