package com.xianqin.dao.support;

import java.lang.reflect.Method;

class AnnotationMethod {
	private Method idMethod;
	private Method refMethod;

	public AnnotationMethod() {
	}

	public AnnotationMethod(Method idMethod, Method refMethod) {
		this.idMethod = idMethod;
		this.refMethod = refMethod;
	}

	public Method getIdMethod() {
		return this.idMethod;
	}

	public void setIdMethod(Method idMethod) {
		this.idMethod = idMethod;
	}

	public Method getRefMethod() {
		return this.refMethod;
	}

	public void setRefMethod(Method refMethod) {
		this.refMethod = refMethod;
	}

	public String toString() {
		return this.idMethod + "," + this.refMethod;
	}
}
