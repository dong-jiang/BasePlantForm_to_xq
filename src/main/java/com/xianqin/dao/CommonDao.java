package com.xianqin.dao;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import com.xianqin.common.Page;
import com.xianqin.common.QueryRule;

public abstract interface CommonDao<T extends Serializable, PK extends Serializable> {
	
	// 根据主键获取实体。如果没有相应的实体，返回 null。
	public abstract T get(PK paramPK);

	// 根据主键判断实体是否存在。如果没有相应的实体，返回false。
	public abstract boolean exists(PK paramPK);

	// 存储实体到数据库。
	public abstract void save(Object paramObject);

	// 更新实体。
	public abstract void update(T paramT);

	// 根据主键删除指定实体。
	public abstract void deleteByPK(PK paramPK);

	// 删除指定的实体。
	public abstract void delete(Object paramObject);

	public abstract List<T> find(QueryRule paramQueryRule);

	public abstract Page find(QueryRule paramQueryRule, int paramInt1,
			int paramInt2);

	public abstract T findUnique(String paramString, Object paramObject);

	public abstract T findUnique(Map<String, Object> paramMap);

	public abstract T findUnique(QueryRule paramQueryRule);

	public abstract void mergeComplexObjectToTargetFromSource(
			Object paramObject1, Object paramObject2, boolean paramBoolean);
}
