package com.xianqin.dao;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.xianqin.common.Page;
import com.xianqin.common.QueryRule;
import com.xianqin.dao.support.QueryRuleSupport;
import com.xianqin.utils.BeanUtils;
import com.xianqin.utils.DataUtils;
import com.xianqin.utils.StringUtils;

/**
 * 第一版改动，没有使用
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class FirstDaoHibernate {
	private static boolean optimizeFind;
	protected SessionFactory sessionFactory;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public static boolean isOptimizeFind() {
		return optimizeFind;
	}

	public static void setOptimizeFind(boolean optimizeFind) {
		FirstDaoHibernate.optimizeFind = optimizeFind;
	}

	/**
	 * 取得sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 采用@Autowired按类型注入SessionFactory, 当有多个SesionFactory的时候在子类重载本函数.
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getSession().get(entityClass, id);
	}

	public <T> List<T> getAll(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public void save(Object obj) {
		getSession().saveOrUpdate(obj);
	}

	public void update(Object obj) {
		getSession().update(obj);
	}

	public void saveAll(List<Object> entityList) {
		for (Object entity : entityList) {
			save(entity);
		}
	}

	public void delete(Object obj) {
		getSession().delete(obj);
	}

	public <T> void deleteAll(List<Object> entityList) {
		for (Object entity : entityList) {
			delete(entity);
		}
	}

	public <T> void deleteByPK(Class<T> entityClass, Serializable id) {
		Object obj = get(entityClass, id);
		if (obj != null) {
			delete(obj);
		}
	}

	public void flush() {
		getSession().flush();
	}

	public void evict(Object obj) {
		getSession().evict(obj);
	}

	public void clear() {
		getSession().clear();
	}

	public List findByHql(String hql, final Object... values) {
		Assert.hasText(hql);

		String newHql = hql;
		int pos = 0;
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				pos = newHql.indexOf('?', pos);
				if (pos == -1) {
					break;
				}
				if (((values[i] instanceof Collection)) && (pos > -1)) {
					newHql = newHql.substring(0, pos) + ":queryParam" + i
							+ newHql.substring(pos + 1);
				}
				pos += 1;
			}
		}
		final String fnHql = newHql;
		Query query = getSession().createQuery(fnHql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if ((values[i] instanceof Collection)) {
					query.setParameterList("queryParam" + i,
							(Collection) values[i]);
				} else {
					query.setParameter(i, values[i]);
				}
			}
		}
		return query.list();
	}

	public List findTopByHql(String hql, final int top, final Object... values) {
		String newHql = hql;
		int pos = 0;
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				pos = newHql.indexOf('?', pos);
				if (pos == -1) {
					break;
				}
				if (((values[i] instanceof Collection)) && (pos > -1)) {
					newHql = newHql.substring(0, pos) + ":queryParam" + i
							+ newHql.substring(pos + 1);
				}
				pos += 1;
			}
		}
		final String fnHql = newHql;
		Query query = getSession().createQuery(fnHql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if ((values[i] instanceof Collection)) {
					query.setParameterList("queryParam" + i,
							(Collection) values[i]);
				} else {
					query.setParameter(i, values[i]);
				}
			}
		}
		query.setFirstResult(0);
		query.setMaxResults(top);
		return query.list();
	}

	public Page findByHql(String hql, int pageNo, int pageSize,
			final Object... values) {
		Assert.hasText(hql);
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}
		String newHql = hql;
		int pos = 0;
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				pos = newHql.indexOf('?', pos);
				if (pos == -1) {
					break;
				}
				if (((values[i] instanceof Collection)) && (pos > -1)) {
					newHql = newHql.substring(0, pos) + ":queryParam" + i
							+ newHql.substring(pos + 1);
				}
				pos += 1;
			}
		}
		final String fnHql = newHql;
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		if (startIndex < 0) {
			return new Page();
		}
		if ((optimizeFind) && (pageNo > 1)) {
			final int realPageSize = pageSize;
			Query query = getSession().createQuery(fnHql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					if ((values[i] instanceof Collection)) {
						query.setParameterList("queryParam" + i,
								(Collection) values[i]);
					} else {
						query.setParameter(i, values[i]);
					}
				}
			}
			query.setFirstResult(startIndex);
			query.setMaxResults(realPageSize);
			List list = query.list();
			return new Page(startIndex, -1L, pageSize, list);
		}
		int maxCount = 100;
		String modifyHql = null;

		final boolean isIncludeDistinctFlag = isIncludeDistinct(fnHql);
		if (isIncludeDistinctFlag) {
			modifyHql = getDistinctCountHql(fnHql)
					+ removeSelect(removeOrders(fnHql));
		} else {
			modifyHql = " select 1 " + removeSelect(removeOrders(fnHql));
		}
		final String countQueryString = modifyHql;
		Query query = getSession().createQuery(countQueryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if ((values[i] instanceof Collection)) {
					query.setParameterList("queryParam" + i,
							(Collection) values[i]);
				} else {
					query.setParameter(i, values[i]);
				}
			}
		}
		if (!isIncludeDistinctFlag) {
			query.setFirstResult(startIndex);
			query.setMaxResults(100 - startIndex);
		}
		List countList = query.list();
		long totalCount = 0L;
		if (isIncludeDistinctFlag) {
			totalCount = ((Long) countList.get(0)).longValue();
			if (totalCount < 1L) {
				return new Page();
			}
		} else {
			if (countList.size() < 1) {
				return new Page();
			}
			totalCount = startIndex + countList.size();
		}
		final int realPageSize = pageSize;

		query = getSession().createQuery(fnHql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if ((values[i] instanceof Collection)) {
					query.setParameterList("queryParam" + i,
							(Collection) values[i]);
				} else {
					query.setParameter(i, values[i]);
				}
			}
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(realPageSize);
		List list = query.list();
		return new Page(startIndex, totalCount, pageSize, list);
	}

	public Page findByHqlNoLimit(String hql, int pageNo, int pageSize,
			final Object... values) {
		Assert.hasText(hql);
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		String newHql = hql;
		int pos = 0;
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				pos = newHql.indexOf('?', pos);
				if (pos == -1) {
					break;
				}
				if (((values[i] instanceof Collection)) && (pos > -1)) {
					newHql = newHql.substring(0, pos) + ":queryParam" + i
							+ newHql.substring(pos + 1);
				}
				pos += 1;
			}
		}
		final String fnHql = newHql;

		final StringBuffer countQueryString = new StringBuffer(
				fnHql.length() + 20).append(" select count (*) ").append(
				removeSelect(removeOrders(fnHql)));
		Query query = getSession().createQuery(countQueryString.toString());
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if ((values[i] instanceof Collection)) {
					query.setParameterList("queryParam" + i,
							(Collection) values[i]);
				} else {
					query.setParameter(i, values[i]);
				}
			}
		}
		List countlist = query.list();
		long totalCount = ((Long) countlist.get(0)).longValue();
		if (totalCount < 1L) {
			return new Page();
		}
		final int realPageSize = pageSize;

		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		query = getSession().createQuery(fnHql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if ((values[i] instanceof Collection)) {
					query.setParameterList("queryParam" + i,
							(Collection) values[i]);
				} else {
					query.setParameter(i, values[i]);
				}
			}
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(realPageSize);
		List list = query.list();
		return new Page(startIndex, totalCount, pageSize, list);
	}

	public <T> T findUnique(Class<T> entityClass, String propertyName,
			Object value) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual(propertyName, value);
		List<T> list = find(entityClass, queryRule);
		if (list.isEmpty()) {
			return null;
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		if (this.logger.isWarnEnabled()) {
			this.logger.warn(StringUtils.concat(new Object[] {
					"findUnique return ", Integer.valueOf(list.size()),
					" record(s). EntityClass=",
					entityClass.getClass().getName(), ",propertyName=",
					propertyName, ",value=", value }));
		}
		throw new IllegalStateException("findUnique return " + list.size()
				+ " record(s).");
	}

	public <T> boolean exists(Class<T> entityClass, Serializable id) {
		T entity = get(entityClass, id);
		if (entity == null) {
			return false;
		}
		return true;
	}

	public long getCount(String hql, Object[] values) {
		Assert.hasText(hql);
		StringBuffer countQueryString = new StringBuffer(hql.length() + 20)
				.append(" select count (*) ").append(
						removeSelect(removeOrders(hql)));
		Query query = getSession().createQuery(hql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		List countlist = query.list();
		return ((Long) countlist.get(0)).longValue();
	}

	protected static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase(Locale.US).indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	protected static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	protected static boolean isIncludeDistinct(String hql) {
		String hqlLowerCase = hql.toLowerCase(Locale.US);
		hqlLowerCase = hqlLowerCase.replace(" ", "");
		if (hqlLowerCase.startsWith("select distinct")) {
			return true;
		}
		return false;
	}

	protected static String getDistinctCountHql(String hql) {
		String hqlSelect = hql.toLowerCase(Locale.US).split("from")[0];
		String hqlSelectDistinct = hqlSelect.split(",")[0];
		hqlSelectDistinct = hql.substring(0, hqlSelectDistinct.length());
		String coml = hqlSelectDistinct.split("distinct")[1];
		coml = coml.replace("(", " ");
		coml = coml.replace(")", " ");
		return "select count(distinct " + coml + ")";
	}

	public <T> List find(final Class<T> entityClass, final QueryRule queryRule) {
		Criteria criteria = getSession().createCriteria(entityClass);
		QueryRuleSupport.createCriteriaWithQueryRule(criteria, queryRule);

		List<Order> orders = getOrderFromQueryRule(queryRule);
		for (Order o : orders) {
			criteria.addOrder(o);
		}
		return criteria.setFirstResult(0).list();
	}

	public <T> Page find(final Class<T> entityClass, final QueryRule queryRule,
			int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		final int realPageNo = pageNo;
		final int realPageSize = pageSize;
		if ((optimizeFind) && (pageNo > 1)) {
			Criteria criteria = getSession().createCriteria(entityClass);
			QueryRuleSupport.createCriteriaWithQueryRule(criteria, queryRule);

			List<Order> orders = getOrderFromQueryRule(queryRule);
			for (Order o : orders) {
				criteria.addOrder(o);
			}
			int startIndex = Page.getStartOfPage(realPageNo, realPageSize);
			List list = criteria.setFirstResult(startIndex)
					.setMaxResults(realPageSize).list();
			return new Page(startIndex, -1L, realPageSize, list);
		}
		Criteria criteria = getSession().createCriteria(entityClass);
		QueryRuleSupport.createCriteriaWithQueryRule(criteria, queryRule);
		CriteriaImpl impl = (CriteriaImpl) criteria;

		Projection projection = impl.getProjection();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List) BeanUtils.forceGetProperty(impl,
					"orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}
		long totalCount = Long.valueOf(
				""
						+ criteria.setProjection(Projections.rowCount())
								.uniqueResult()).longValue();

		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}
		if (totalCount < 1L) {
			return new Page();
		}
		List<Order> orders = getOrderFromQueryRule(queryRule);
		for (Order o : orders) {
			criteria.addOrder(o);
		}
		int startIndex = Page.getStartOfPage(realPageNo, realPageSize);
		List list = criteria.setFirstResult(startIndex)
				.setMaxResults(realPageSize).list();
		return new Page(startIndex, totalCount, realPageSize, list);
	}

	protected List<Order> getOrderFromQueryRule(QueryRule queryRule) {
		List<Order> orders = new ArrayList();
		for (QueryRule.Rule rule : queryRule.getRuleList()) {
			switch (rule.getType()) {
			case 101:
				if (StringUtils.isNotEmpty(rule.getPropertyName())) {
					orders.add(Order.asc(rule.getPropertyName()));
				}
				break;
			case 102:
				if (StringUtils.isNotEmpty(rule.getPropertyName())) {
					orders.add(Order.desc(rule.getPropertyName()));
				}
				break;
			}
		}
		return orders;
	}

	public <T> T findUnique(Class<T> entityClass, Map<String, Object> properties) {
		QueryRule queryRule = QueryRule.getInstance();
		for (Iterator iterator = properties.keySet().iterator(); iterator
				.hasNext();) {
			String key = (String) iterator.next();
			queryRule.addEqual(key, properties.get(key));
		}
		return findUnique(entityClass, queryRule);
	}

	public <T> T findUnique(Class<T> entityClass, QueryRule queryRule) {
		List<T> list = find(entityClass, queryRule);
		if (list.isEmpty()) {
			return null;
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		if (this.logger.isWarnEnabled()) {
			List<QueryRule.Rule> ruleList = queryRule.getRuleList();
			StringBuilder buf = new StringBuilder();
			buf.append("findUnique return ").append(list.size())
					.append(" record(s). EntityClass=")
					.append(entityClass.getClass().getName())
					.append(").append(queryRule={");
			if (ruleList != null) {
				for (int i = 0; i < ruleList.size(); i++) {
					QueryRule.Rule rule = (QueryRule.Rule) ruleList.get(i);
					if (rule != null) {
						buf.append(ToStringBuilder.reflectionToString(rule));
						if (i < ruleList.size() - 1) {
							buf.append(",");
						}
					}
				}
			}
			buf.append("}");
			this.logger.warn(buf.toString());
		}
		throw new IllegalStateException("findUnique return " + list.size()
				+ " record(s).");
	}

	public <T> Page pagination(List<T> objList, int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		List<T> objectArray = new ArrayList(0);
		int startIndex = (pageNo - 1) * pageSize;
		int endIndex = pageNo * pageSize;
		for (int i = startIndex; i < endIndex; i++) {
			objectArray.add(objList.get(i));
		}
		return new Page(startIndex, objList.size(), pageSize, objectArray);
	}

	public void mergeList(List pojoList, List poList, String idName) {
		mergeList(pojoList, poList, idName, false);
	}

	public void mergeList(List pojoList, List poList, String idName,
			boolean isCopyNull) {
		Map<Object, Object> map = new HashMap();
		Map<Integer, Object> keyMap = new HashMap();
		Map<Object, Object> poMap = new HashMap();
		int i = 0;
		for (int count = pojoList.size(); i < count; i++) {
			Object element = pojoList.get(i);
			if (element != null) {
				try {
					Object key = PropertyUtils.getProperty(element, idName);
					map.put(key, element);
					keyMap.put(Integer.valueOf(i), key);
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
		for (Iterator it = poList.iterator(); it.hasNext();) {
			Object element = it.next();
			try {
				Object key = PropertyUtils.getProperty(element, idName);
				poMap.put(key, null);
				if (!map.containsKey(key)) {
					delete(element);
					it.remove();
				} else {
					DataUtils.copySimpleObjectToTargetFromSource(element,
							map.get(key), isCopyNull);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
		i = 0;
		for (int count = pojoList.size(); i < count; i++) {
			Object element = pojoList.get(i);
			if (element != null) {
				Object key = keyMap.get(Integer.valueOf(i));
				if ((key == null) || (!poMap.containsKey(key))) {
					poList.add(element);
				}
			}
		}
	}

	public Long getSequence(final String sequenceName) {
		SQLQuery sqlQuery = getSession().createSQLQuery(
				"select " + sequenceName
						+ ".nextval from systables where tabid=1");

		List list = sqlQuery.list();
		return Long.valueOf("" + list.get(0));
	}

	public List findUnionBySql(final String sql, final Object[] values) {
		Assert.hasText(sql);
		Assert.isTrue(sql.toLowerCase(Locale.US).indexOf("union") != -1);
		SQLQuery query = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	public Page findUnionByHqls(List<String> hqls, List<List<Object>> valuess,
			int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		Long[] counts = new Long[hqls.size()];
		long totalCount = 0L;
		for (int k = 0; k < hqls.size(); k++) {
			String newHql = (String) hqls.get(k);
			final Object[] values = ((List) valuess.get(k)).toArray();
			int pos = 0;
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pos = newHql.indexOf('?', pos);
					if (pos == -1) {
						break;
					}
					if (((values[i] instanceof Collection)) && (pos > -1)) {
						newHql = newHql.substring(0, pos) + ":queryParam" + i
								+ newHql.substring(pos + 1);
					}
					pos += 1;
				}
			}
			String fnHql = newHql;

			final StringBuffer countQueryString = new StringBuffer(
					fnHql.length() + 20).append(" select count (*) ").append(
					removeSelect(removeOrders(fnHql)));
			Query query = getSession().createQuery(countQueryString.toString());
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					if ((values[i] instanceof Collection)) {
						query.setParameterList("queryParam" + i,
								(Collection) values[i]);
					} else {
						query.setParameter(i, values[i]);
					}
				}
			}
			List countlist = query.list();
			counts[k] = ((Long) countlist.get(0));
			totalCount += counts[k].longValue();
		}
		List<List> datas = new ArrayList();
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		long gindex = 0L;
		long selectCount = 0L;
		for (int k = 0; k < hqls.size(); k++) {
			final String fnHql = (String) hqls.get(k);
			final Object[] values = ((List) valuess.get(k)).toArray();
			long realIndex = startIndex - gindex;
			long realSize = pageSize - selectCount;
			if (realIndex < 0L) {
				realIndex = 0L;
			}
			if (counts[k].longValue() - realIndex < realSize) {
				realSize = counts[k].longValue() - realIndex;
			}
			final long fnRealIndex = realIndex;
			final long fnRealSize = realSize;
			if ((realIndex >= 0L) && (realSize > 0L)) {
				Query query = getSession().createQuery(fnHql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						if ((values[i] instanceof Collection)) {
							query.setParameterList("queryParam" + i,
									(Collection) values[i]);
						} else {
							query.setParameter(i, values[i]);
						}
					}
				}
				query.setFirstResult((int) fnRealIndex);
				query.setMaxResults((int) fnRealSize);
				List list = query.list();
				datas.add(list);
				selectCount += fnRealSize;
			}
			if (selectCount == pageSize) {
				break;
			}
			gindex += counts[k].longValue();
		}
		List resultList = new ArrayList();
		for (int i = 0; i < datas.size(); i++) {
			resultList.addAll((Collection) datas.get(i));
		}
		return new Page(startIndex, totalCount, pageSize, resultList);
	}

	public Page findTopUnionByHqls(List<String> hqls, int top,
			List<List<Object>> valuess) {
		List<List> datas = new ArrayList();
		int selectCount = 0;
		for (int i = 0; i < hqls.size(); i++) {
			final String fnHql = (String) hqls.get(i);
			final Object[] values = ((List) valuess.get(i)).toArray();
			final int maxCount = top - selectCount;
			Query query = getSession().createQuery(fnHql);
			if (values != null) {
				for (int j = 0; j < values.length; j++) {
					if ((values[j] instanceof Collection)) {
						query.setParameterList("queryParam" + j,
								(Collection) values[j]);
					} else {
						query.setParameter(j, values[j]);
					}
				}
			}
			query.setMaxResults(maxCount);
			List list = query.list();
			datas.add(list);
			selectCount += list.size();
			if (selectCount >= top) {
				break;
			}
		}
		List resultList = new ArrayList();
		for (int i = 0; i < datas.size(); i++) {
			resultList.addAll((Collection) datas.get(i));
		}
		return new Page(1L, selectCount, selectCount, resultList);
	}

	public List findBySql(final String sql, final Object... values) {
		Assert.hasText(sql);
		SQLQuery query = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

}
