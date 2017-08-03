package com.xianqin.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_PAGE_SIZE = 20;
	private int pageSize;
	private long start;
	private List<T> data;
	private long totalCount;
	private String message;

	public Page() {
		this(0L, 0L, DEFAULT_PAGE_SIZE, new ArrayList<T>());
	}

	public Page(long start, long totalSize, int pageSize, List<T> data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public long getTotalPageCount() {
		if (this.totalCount % this.pageSize == 0L) {
			return (this.totalCount / this.pageSize);
		}
		return (this.totalCount / this.pageSize + 1L);
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public List<T> getResult() {
		return this.data;
	}

	public long getCurrentPageNo() {
		return (this.start / this.pageSize + 1L);
	}

	public boolean hasNextPage() {
		return (getCurrentPageNo() < getTotalPageCount() - 1L);
	}

	public boolean hasPreviousPage() {
		return (getCurrentPageNo() > 1L);
	}

	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, 20);
	}

	public static int getStartOfPage(int pageNo, int pageSize) {
		return ((pageNo - 1) * pageSize);
	}

	public long getStart() {
		return this.start;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
