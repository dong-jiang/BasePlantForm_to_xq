package com.xianqin.dao.support;

import java.util.List;


import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.xianqin.common.QueryRule;
import com.xianqin.utils.StringUtils;

public class QueryRuleSupport {
	public static void createCriteriaWithQueryRule(Criteria criteria,
			QueryRule queryRule) {
		for (QueryRule.Rule rule : queryRule.getRuleList()) {
			switch (rule.getType()) {
			case 3:
				processBetween(criteria, rule);
				break;
			case 4:
				processEqual(criteria, rule);
				break;
			case 1:
				processLike(criteria, rule);
				break;
			case 5:
				processNotEqual(criteria, rule);
				break;
			case 6:
				processGreaterThen(criteria, rule);
				break;
			case 7:
				processGreaterEqual(criteria, rule);
				break;
			case 8:
				processLessThen(criteria, rule);
				break;
			case 9:
				processLessEqual(criteria, rule);
				break;
			case 10:
				processSQL(criteria, rule);
				break;
			case 2:
				processIN(criteria, rule);
				break;
			case 11:
				processIsNull(criteria, rule);
				break;
			case 12:
				processIsNotNull(criteria, rule);
				break;
			case 13:
				processIsEmpty(criteria, rule);
				break;
			case 14:
				processIsNotEmpty(criteria, rule);
				break;
			case 101:
				break;
			case 102:
				break;
			default:
				throw new IllegalArgumentException("type " + rule.getType()
						+ " not supported.");
			}
		}
		for (QueryRule subQueryRule : queryRule.getQueryRuleList()) {
			Criteria subCriteria = criteria.createCriteria(subQueryRule
					.getPropertyName());
			createCriteriaWithQueryRule(subCriteria, subQueryRule);
		}
	}

	protected static void processLike(Criteria criteria, QueryRule.Rule rule) {
		if (ArrayUtils.isEmpty(rule.getValues())) {
			return;
		}
		Object obj = rule.getValues()[0];

		if (obj != null) {
			String value = obj.toString();
			if (StringUtils.isNotEmpty(value)) {
				value = value.replace('*', '%');

				obj = value;
			}
		}
		criteria.add(Restrictions.like(rule.getPropertyName(), obj));
	}

	protected static void processBetween(Criteria criteria, QueryRule.Rule rule) {
		if ((ArrayUtils.isEmpty(rule.getValues()))
				|| (rule.getValues().length < 2)) {
			return;
		}
		criteria.add(Restrictions.between(rule.getPropertyName(), rule
				.getValues()[0], rule.getValues()[1]));
	}

	protected static void processEqual(Criteria criteria, QueryRule.Rule rule) {
		if (ArrayUtils.isEmpty(rule.getValues())) {
			return;
		}
		criteria.add(Restrictions.eq(rule.getPropertyName(),
				rule.getValues()[0]));
	}

	protected static void processNotEqual(Criteria criteria, QueryRule.Rule rule) {
		if (ArrayUtils.isEmpty(rule.getValues())) {
			return;
		}
		criteria.add(Restrictions.ne(rule.getPropertyName(),
				rule.getValues()[0]));
	}

	protected static void processGreaterThen(Criteria criteria,
			QueryRule.Rule rule) {
		if (ArrayUtils.isEmpty(rule.getValues())) {
			return;
		}
		criteria.add(Restrictions.gt(rule.getPropertyName(),
				rule.getValues()[0]));
	}

	protected static void processGreaterEqual(Criteria criteria,
			QueryRule.Rule rule) {
		if (ArrayUtils.isEmpty(rule.getValues())) {
			return;
		}
		criteria.add(Restrictions.ge(rule.getPropertyName(),
				rule.getValues()[0]));
	}

	protected static void processLessThen(Criteria criteria, QueryRule.Rule rule) {
		if (ArrayUtils.isEmpty(rule.getValues())) {
			return;
		}
		criteria.add(Restrictions.lt(rule.getPropertyName(),
				rule.getValues()[0]));
	}

	protected static void processLessEqual(Criteria criteria,
			QueryRule.Rule rule) {
		if (ArrayUtils.isEmpty(rule.getValues())) {
			return;
		}
		criteria.add(Restrictions.le(rule.getPropertyName(),
				rule.getValues()[0]));
	}

	protected static void processSQL(Criteria criteria, QueryRule.Rule rule) {
		criteria.add(Restrictions.sqlRestriction(rule.getPropertyName()));
	}

	protected static void processIsNull(Criteria criteria, QueryRule.Rule rule) {
		criteria.add(Restrictions.isNull(rule.getPropertyName()));
	}

	protected static void processIsNotNull(Criteria criteria,
			QueryRule.Rule rule) {
		criteria.add(Restrictions.isNotNull(rule.getPropertyName()));
	}

	protected static void processIsNotEmpty(Criteria criteria,
			QueryRule.Rule rule) {
		criteria.add(Restrictions.isNotEmpty(rule.getPropertyName()));
	}

	protected static void processIsEmpty(Criteria criteria, QueryRule.Rule rule) {
		criteria.add(Restrictions.isEmpty(rule.getPropertyName()));
	}

	protected static void processIN(Criteria criteria, QueryRule.Rule rule) {
		if (ArrayUtils.isEmpty(rule.getValues())) {
			return;
		}
		if ((rule.getValues().length == 1) && (rule.getValues()[0] != null)
				&& (rule.getValues()[0] instanceof List)) {
			List list = (List) rule.getValues()[0];
			if ((list != null) && (!(list.isEmpty())))
				criteria.add(Restrictions.in(rule.getPropertyName(), list));
		} else {
			criteria.add(Restrictions.in(rule.getPropertyName(), rule
					.getValues()));
		}
	}
}
