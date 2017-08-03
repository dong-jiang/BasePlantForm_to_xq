package com.xianqin.dao.impl;

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

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.xianqin.common.Page;
import com.xianqin.common.QueryRule;
import com.xianqin.dao.support.QueryRuleSupport;
import com.xianqin.utils.BeanUtils;
import com.xianqin.utils.DataUtils;
import com.xianqin.utils.StringUtils;

@Component("domainDaoImpl")
@SuppressWarnings("all")
public class DomainDaoImpl extends HibernateDaoSupport implements
		ApplicationContextAware {
	protected ApplicationContext applicationContext;
	private static final int DEFAULT_PAGE_SIZE = 10;
	private static boolean optimizeFind;

	public static boolean isOptimizeFind() {
		return optimizeFind;
	}

	public static void setOptimizeFind(boolean optimizeFind) {
		DomainDaoImpl.optimizeFind = optimizeFind;
	}

	/**
	 * 说明: 1.在既使用注解又使用HibernateDaoSupport的情况下,只能这么写,
	 * 原因是HibernateDaoSupport是抽象类,且方法都是final修饰的,
	 * 这样就不能为其注入sessionFactory或者hibernateTemplate
	 * 2.若使用xml配置的话,就可以直接给HibernateDaoSupport注入.
	 */
	// 而使用HibernateDaosupport,又必须为其注入sessionFactory或者hibernateTemplate

	// 这里为其注入sessionFactory,最后只需要让自己的Dao继承这个MyDaoSupport.
	// 不直接在自己的Dao里继承HibernateDaoSupport的原因是这样可以简化代码,
	// 不用每次都为其注入sessionFactory或者hibernateTemplate了,在这里注入一次就够了.
//	@Resource(name = "sessionFactory")
//	public void setSuperSessionFactory(SessionFactory sessionFactory) {
//		super.setSessionFactory(sessionFactory);
//	}

	// 或者为其注入hibernateTemplate
	@Resource(name = "hibernateTemplate")
	public void setSuperHibernateTemplate(HibernateTemplate hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
	}

	
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public <T> List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public void save(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	public void saveAll(List list) {
		for (Object entity : list) {
			getHibernateTemplate().saveOrUpdate(entity);
		}
	}

	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	public <T> void deleteAll(List entityList) {
		getHibernateTemplate().deleteAll(entityList);
	}

	public <T> void deleteByPK(Class<T> entityClass, Serializable id) {
		Object obj = get(entityClass, id);
		if (obj != null) {
			delete(obj);
		}
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void evict(Object obj) {
		getHibernateTemplate().evict(obj);
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	@SuppressWarnings("unchecked")
	public List findByHql(String hql, final Object... values) {
		Assert.hasText(hql, "参数hql错误");
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

		List list = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(fnHql);
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
				});
		return list;
	}

	@SuppressWarnings("unchecked")
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

		List list = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(fnHql);
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
				});
		return list;
	}

	@SuppressWarnings("unchecked")
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

			List list = getHibernateTemplate().executeWithNativeSession(
					new HibernateCallback() {
						public List<?> doInHibernate(Session session)
								throws HibernateException {
							Query query = session.createQuery(fnHql);
							if (values != null) {
								for (int i = 0; i < values.length; i++) {
									if ((values[i] instanceof Collection)) {
										query.setParameterList(
												"queryParam" + i,
												(Collection) values[i]);
									} else {
										query.setParameter(i, values[i]);
									}
								}
							}
							query.setFirstResult(startIndex);
							query.setMaxResults(realPageSize);
							return query.list();
						}
					});
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
		List countList = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(countQueryString);
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
						return query.list();
					}
				});
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

		List list = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(fnHql);
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
						return query.list();
					}
				});
		return new Page(startIndex, totalCount, pageSize, list);
	}

	@SuppressWarnings("unchecked")
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

		List countlist = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(countQueryString
								.toString());
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
				});
		long totalCount = ((Long) countlist.get(0)).longValue();
		if (totalCount < 1L) {
			return new Page();
		}
		final int realPageSize = pageSize;

		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List list = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(fnHql);
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
						return query.list();
					}
				});
		return new Page(startIndex, totalCount, pageSize, list);
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public <T> boolean exists(Class<T> entityClass, Serializable id) {
		T entity = (T) getHibernateTemplate().get(entityClass, id);
		if (entity == null) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public long getCount(String hql, Object[] values) {
		Assert.hasText(hql);

		StringBuffer countQueryString = new StringBuffer(hql.length() + 20)
				.append(" select count (*) ").append(
						removeSelect(removeOrders(hql)));

		List countlist = getHibernateTemplate().find(
				countQueryString.toString(), values);
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
		if (hqlLowerCase.startsWith("selectdistinct")) {
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

	@SuppressWarnings("unchecked")
	public <T> List find(final Class<T> entityClass, final QueryRule queryRule) {
		List list = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entityClass);
						QueryRuleSupport.createCriteriaWithQueryRule(criteria,
								queryRule);

						List<Order> orders = getOrderFromQueryRule(queryRule);
						for (Order o : orders) {
							criteria.addOrder(o);
						}
						return criteria.setFirstResult(0).list();
					}
				});
		return list;
	}

	@SuppressWarnings("unchecked")
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
			Page page = (Page) getHibernateTemplate().executeWithNativeSession(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Criteria criteria = session
									.createCriteria(entityClass);
							QueryRuleSupport.createCriteriaWithQueryRule(
									criteria, queryRule);

							List<Order> orders = getOrderFromQueryRule(queryRule);
							for (Order o : orders) {
								criteria.addOrder(o);
							}
							int startIndex = Page.getStartOfPage(realPageNo,
									realPageSize);
							List list = criteria.setFirstResult(startIndex)
									.setMaxResults(realPageSize).list();
							return new Page(startIndex, -1L, realPageSize, list);
						}
					});
			return page;
		}
		Page page = (Page) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(entityClass);
						QueryRuleSupport.createCriteriaWithQueryRule(criteria,
								queryRule);
						CriteriaImpl impl = (CriteriaImpl) criteria;

						Projection projection = impl.getProjection();
						List<CriteriaImpl.OrderEntry> orderEntries;
						try {
							orderEntries = (List) BeanUtils.forceGetProperty(
									impl, "orderEntries");
							BeanUtils.forceSetProperty(impl, "orderEntries",
									new ArrayList());
						} catch (Exception e) {
							throw new InternalError(
									" Runtime Exception impossibility throw ");
						}
						long totalCount = Long.valueOf(
								""
										+ criteria.setProjection(
												Projections.rowCount())
												.uniqueResult()).longValue();

						criteria.setProjection(projection);
						if (projection == null) {
							criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
						}
						try {
							BeanUtils.forceSetProperty(impl, "orderEntries",
									orderEntries);
						} catch (Exception e) {
							throw new InternalError(
									" Runtime Exception impossibility throw ");
						}
						if (totalCount < 1L) {
							return new Page();
						}
						List<Order> orders = getOrderFromQueryRule(queryRule);
						for (Order o : orders) {
							criteria.addOrder(o);
						}
						int startIndex = Page.getStartOfPage(realPageNo,
								realPageSize);
						List list = criteria.setFirstResult(startIndex)
								.setMaxResults(realPageSize).list();
						return new Page(startIndex, totalCount, realPageSize,
								list);
					}
				});
		return page;
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public <T> T findUnique(Class<T> entityClass, Map<String, Object> properties) {
		QueryRule queryRule = QueryRule.getInstance();
		for (Iterator iterator = properties.keySet().iterator(); iterator
				.hasNext();) {
			String key = (String) iterator.next();
			queryRule.addEqual(key, properties.get(key));
		}
		return findUnique(entityClass, queryRule);
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public void mergeList(List pojoList, List poList, String idName) {
		mergeList(pojoList, poList, idName, false);
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public Long getSequence(final String sequenceName) {
		Long seq = (Long) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sqlQuery = session.createSQLQuery("select "
								+ sequenceName
								+ ".nextval from systables where tabid=1");

						List list = sqlQuery.list();
						return Long.valueOf("" + list.get(0));
					}
				});
		return seq;
	}

	@SuppressWarnings("unchecked")
	public List findUnionBySql(final String sql, final Object[] values) {
		Assert.hasText(sql);
		Assert.isTrue(sql.toLowerCase(Locale.US).indexOf("union") != -1);
		List list = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						return query.list();
					}
				});
		return list;
	}

	@SuppressWarnings("unchecked")
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

			List countlist = getHibernateTemplate().executeWithNativeSession(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query query = session.createQuery(countQueryString
									.toString());
							if (values != null) {
								for (int i = 0; i < values.length; i++) {
									if ((values[i] instanceof Collection)) {
										query.setParameterList(
												"queryParam" + i,
												(Collection) values[i]);
									} else {
										query.setParameter(i, values[i]);
									}
								}
							}
							return query.list();
						}
					});
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
				List list = getHibernateTemplate().executeWithNativeSession(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException {
								Query query = session.createQuery(fnHql);
								if (values != null) {
									for (int i = 0; i < values.length; i++) {
										if ((values[i] instanceof Collection)) {
											query.setParameterList("queryParam"
													+ i, (Collection) values[i]);
										} else {
											query.setParameter(i, values[i]);
										}
									}
								}
								query.setFirstResult((int) fnRealIndex);
								query.setMaxResults((int) fnRealSize);
								return query.list();
							}
						});
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

	@SuppressWarnings("unchecked")
	public Page findTopUnionByHqls(List<String> hqls, int top,
			List<List<Object>> valuess) {
		List<List> datas = new ArrayList();
		int selectCount = 0;
		for (int i = 0; i < hqls.size(); i++) {
			final String fnHql = (String) hqls.get(i);
			final Object[] values = ((List) valuess.get(i)).toArray();
			final int maxCount = top - selectCount;
			List list = getHibernateTemplate().executeWithNativeSession(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query query = session.createQuery(fnHql);
							if (values != null) {
								for (int j = 0; j < values.length; j++) {
									if ((values[j] instanceof Collection)) {
										query.setParameterList(
												"queryParam" + j,
												(Collection) values[j]);
									} else {
										query.setParameter(j, values[j]);
									}
								}
							}
							query.setMaxResults(maxCount);
							return query.list();
						}
					});
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

	@SuppressWarnings("unchecked")
	public List findBySql(final String sql, final Object... values) {
		Assert.hasText(sql);
		List list = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						return query.list();
					}
				});
		return list;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
