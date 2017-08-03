package com.xianqin.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xianqin.utils.StringUtils;


public final class QueryRule implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int ASC_ORDER = 101;
	public static final int DESC_ORDER = 102;
	public static final int LIKE = 1;
	public static final int IN = 2;
	public static final int BETWEEN = 3;
	public static final int EQ = 4;
	public static final int NOTEQ = 5;
	public static final int GT = 6;
	public static final int GE = 7;
	public static final int LT = 8;
	public static final int LE = 9;
	public static final int SQL = 10;
	public static final int ISNULL = 11;
	public static final int ISNOTNULL = 12;
	public static final int ISEMPTY = 13;
	public static final int ISNOTEMPTY = 14;
	public static final int MAX_RESULTS = 101;
	public static final int FIRST_RESULTS = 102;
	private List<Rule> ruleList = new ArrayList<Rule>();
	private List<QueryRule> queryRuleList = new ArrayList<QueryRule>();
	private String propertyName;
	private boolean ignoreNoValue = false;

	private QueryRule() {
	}

	private QueryRule(String propertyName) {
		this.propertyName = propertyName;
	}

	public static QueryRule getInstance() {
		return new QueryRule();
	}

	public QueryRule addAscOrder(String propertyName) {
		this.ruleList.add(new Rule(101, propertyName));
		return this;
	}

	public QueryRule addDescOrder(String propertyName) {
		this.ruleList.add(new Rule(102, propertyName));
		return this;
	}

	public QueryRule addIsNull(String propertyName) {
		this.ruleList.add(new Rule(11, propertyName));
		return this;
	}

	public QueryRule addIsNotNull(String propertyName) {
		this.ruleList.add(new Rule(12, propertyName));
		return this;
	}

	public QueryRule addIsEmpty(String propertyName) {
		this.ruleList.add(new Rule(13, propertyName));
		return this;
	}

	public QueryRule addIsNotEmpty(String propertyName) {
		this.ruleList.add(new Rule(14, propertyName));
		return this;
	}

	public QueryRule addLike(String propertyName, Object value) {
		if ((this.ignoreNoValue)
				&& (((value == null) || (StringUtils.isEmpty(value.toString()))))) {
			return this;
		}
		this.ruleList.add(new Rule(1, propertyName, new Object[] { value }));
		return this;
	}

	public QueryRule addEqual(String propertyName, Object value) {
		if ((this.ignoreNoValue)
				&& (((value == null) || (StringUtils.isEmpty(value.toString()))))) {
			return this;
		}

		this.ruleList.add(new Rule(4, propertyName, new Object[] { value }));
		return this;
	}

	public QueryRule addBetween(String propertyName, Object[] values) {
		if (this.ignoreNoValue) {
			if ((values == null) || (values.length != 2)) {
				return this;
			}
			if ((values[0] == null)
					|| (StringUtils.isEmpty(values[0].toString()))) {
				return this;
			}
			if ((values[1] == null)
					|| (StringUtils.isEmpty(values[1].toString()))) {
				return this;
			}
		}
		this.ruleList.add(new Rule(3, propertyName, values));
		return this;
	}

	public QueryRule addIn(String propertyName, List<String> values) {
		if ((this.ignoreNoValue) && (((values == null) || (values.isEmpty())))) {
			return this;
		}

		this.ruleList.add(new Rule(2, propertyName, new Object[] { values }));
		return this;
	}

	public QueryRule addIn(String propertyName, Object... values) {
		if ((this.ignoreNoValue)
				&& (((values == null) || (values.length == 0)))) {
			return this;
		}

		this.ruleList.add(new Rule(2, propertyName, values));
		return this;
	}

	public QueryRule addNotEqual(String propertyName, Object value) {
		if ((this.ignoreNoValue)
				&& (((value == null) || (StringUtils.isEmpty(value.toString()))))) {
			return this;
		}

		this.ruleList.add(new Rule(5, propertyName, new Object[] { value }));
		return this;
	}

	public QueryRule addGreaterThan(String propertyName, Object value) {
		if ((this.ignoreNoValue)
				&& (((value == null) || (StringUtils.isEmpty(value.toString()))))) {
			return this;
		}

		this.ruleList.add(new Rule(6, propertyName, new Object[] { value }));
		return this;
	}

	public QueryRule addGreaterEqual(String propertyName, Object value) {
		if ((this.ignoreNoValue)
				&& (((value == null) || (StringUtils.isEmpty(value.toString()))))) {
			return this;
		}

		this.ruleList.add(new Rule(7, propertyName, new Object[] { value }));
		return this;
	}

	public QueryRule addLessThan(String propertyName, Object value) {
		if ((this.ignoreNoValue)
				&& (((value == null) || (StringUtils.isEmpty(value.toString()))))) {
			return this;
		}

		this.ruleList.add(new Rule(8, propertyName, new Object[] { value }));
		return this;
	}

	public QueryRule addLessEqual(String propertyName, Object value) {
		if ((this.ignoreNoValue)
				&& (((value == null) || (StringUtils.isEmpty(value.toString()))))) {
			return this;
		}

		this.ruleList.add(new Rule(9, propertyName, new Object[] { value }));
		return this;
	}

	public QueryRule addSql(String sql) {
		this.ruleList.add(new Rule(10, sql));
		return this;
	}

	public QueryRule addSubQueryRule(String propertyName) {
		QueryRule queryRule = new QueryRule(propertyName);
		this.queryRuleList.add(queryRule);
		return queryRule;
	}

	public List<Rule> getRuleList() {
		return this.ruleList;
	}

	public List<QueryRule> getQueryRuleList() {
		return this.queryRuleList;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public boolean isIgnoreNoValue() {
		return this.ignoreNoValue;
	}

	public void setIgnoreNoValue(boolean ignoreNoValue) {
		this.ignoreNoValue = ignoreNoValue;
	}

	public class Rule implements Serializable {
		private static final long serialVersionUID = 1L;
		private int type;
		private String propertyName;
		private Object[] values;

		public Rule(int type, String propertyName) {
			this.propertyName = propertyName;
			this.type = type;
		}

		public Rule(int paramInt, String paramString,
				Object[] paramArrayOfObject) {
			this.propertyName = paramString;
			this.values = paramArrayOfObject;
			this.type = paramInt;
		}

		public Object[] getValues() {
			return this.values;
		}

		public int getType() {
			return this.type;
		}

		public String getPropertyName() {
			return this.propertyName;
		}
	}
}