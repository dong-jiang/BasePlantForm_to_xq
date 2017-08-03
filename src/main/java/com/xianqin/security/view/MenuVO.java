package com.xianqin.security.view;

import java.io.Serializable;

public class MenuVO implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String url;
    private String icon;
    private Short orderNum;
    private String isDefault;
    private String pid;
    private String title;
    private Boolean isleaf;
    private String level;
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public String getIcon()
    {
        return icon;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    public Short getOrderNum()
    {
        return orderNum;
    }
    public void setOrderNum(Short orderNum)
    {
        this.orderNum = orderNum;
    }
    public String getIsDefault()
    {
        return isDefault;
    }
    public void setIsDefault(String isDefault)
    {
        this.isDefault = isDefault;
    }
    public String getPid()
    {
        return pid;
    }
    public void setPid(String pid)
    {
        this.pid = pid;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public Boolean getIsleaf()
    {
        return isleaf;
    }
    public void setIsleaf(Boolean isleaf)
    {
        this.isleaf = isleaf;
    }
    public String getLevel()
    {
        return level;
    }
    public void setLevel(String level)
    {
        this.level = level;
    }
    
    public MenuVO()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public MenuVO(String id, String name, String url, String icon, Short orderNum, String isDefault, String pid, String title, Boolean isleaf, String level)
    {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.orderNum = orderNum;
        this.isDefault = isDefault;
        this.pid = pid;
        this.title = title;
        this.isleaf = isleaf;
        this.level = level;
    }
    
}
