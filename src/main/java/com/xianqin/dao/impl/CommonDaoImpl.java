package com.xianqin.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xianqin.common.Page;
import com.xianqin.common.QueryRule;
import com.xianqin.dao.CommonDao;
import com.xianqin.dao.support.DomainSupport;
import com.xianqin.utils.GenericsUtils;

@SuppressWarnings("all")
public class CommonDaoImpl<T extends Serializable, PK extends Serializable> extends DomainDaoImpl implements CommonDao<T, PK>
{
    protected Class<T> entityClass;

    public CommonDaoImpl()
    {
        this.entityClass = (Class<T>) GenericsUtils.getSuperClassGenricType(super.getClass());
    }

    public CommonDaoImpl(Class<T> persistentClass)
    {
        this.entityClass = persistentClass;
    }

    public T get(PK id)
    {
        return super.get(this.entityClass, id);
    }

    public boolean exists(PK id)
    {
        return super.exists(this.entityClass, id);
    }

    public void deleteByPK(PK id)
    {
        super.deleteByPK(this.entityClass, id);
    }

    public T findUnique(String propertyName, Object value)
    {
        return super.findUnique(this.entityClass, propertyName, value);
    }

    public T findUnique(QueryRule queryRule)
    {
        return super.findUnique(this.entityClass, queryRule);
    }

    public T findUnique(Map<String, Object> properties)
    {
        return super.findUnique(this.entityClass, properties);
    }

    @SuppressWarnings("unchecked")
    public List<T> find(QueryRule queryRule)
    {
        return super.find(this.entityClass, queryRule);
    }

    public Page find(QueryRule queryRule, int pageNo, int pageSize)
    {
        return super.find(this.entityClass, queryRule, pageNo, pageSize);
    }

    public void update(T obj)
    {
        super.update(obj);
    }

    public void mergeComplexObjectToTargetFromSource(Object target, Object source, boolean isCopyNull)
    {
        DomainSupport entityUtils = new DomainSupport(this);
        entityUtils.mergeComplexObjectToTargetFromSource(target, source, isCopyNull);
    }
}
