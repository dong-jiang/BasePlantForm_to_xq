package com.xianqin.dao.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xianqin.dao.CommonDao;
import com.xianqin.utils.BeanUtils;

public class DomainSupport {
	private static final Log logger = LogFactory.getLog(DomainSupport.class);
	private static Map<Class, String> supportTypeMap = new HashMap();
	private CommonDao service;

	public DomainSupport(CommonDao service) {
		this.service = service;
	}

	private AnnotationMethod getAnnMethod(Class clazz, Object parent) {
		Method idMethod = null;
		Method refMethod = null;
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if ((methodName.startsWith("get")) || (methodName.startsWith("is"))) {
				Annotation[] annotations = method.getAnnotations();
				if (annotations != null) {
					for (Annotation annotation : annotations) {
						if (((annotation instanceof Id))
								|| ((annotation instanceof EmbeddedId))) {
							idMethod = method;
						} else if (((annotation instanceof JoinColumn))
								|| ((annotation instanceof JoinColumns))) {
							if (parent.getClass()
									.equals(method.getReturnType())) {
								refMethod = method;
							}
						}
					}
					if ((idMethod != null) && (refMethod != null)) {
						return new AnnotationMethod(idMethod, refMethod);
					}
				}
			}
		}
		return new AnnotationMethod(idMethod, refMethod);
	}

	private boolean needHandleMethod(Method method) {
		Annotation[] annotations = method.getAnnotations();
		if (annotations == null) {
			return true;
		}
		for (Annotation annotation : annotations) {
			if (((annotation instanceof Id))
					|| ((annotation instanceof EmbeddedId))
					|| ((annotation instanceof JoinColumn))
					|| ((annotation instanceof JoinColumns))) {
				return false;
			}
		}
		return true;
	}

	private void mergeList(List listTarget, List listSource,
			boolean isCopyNull, Object parent) throws Exception {
		Class clazz = null;
		if (listSource.size() > 0) {
			clazz = listSource.get(0).getClass();
		} else if (listTarget.size() > 0) {
			clazz = listTarget.get(0).getClass();
		}
		if (clazz == null) {
			return;
		}
		AnnotationMethod annMethod = getAnnMethod(clazz, parent);
		Method idMethod = annMethod.getIdMethod();
		Method refMethod = annMethod.getRefMethod();
		Map<Object, Object> mapSource = new HashMap();
		Map<Object, Object> mapTarget = new HashMap();
		Object[] params = new Object[0];
		String refSetMethod = "set" + refMethod.getName().substring(3);
		for (Object element : listSource) {
			Object key = idMethod.invoke(element, params);
			mapSource.put(key, element);
		}
		for (Object element : listTarget) {
			Object key = idMethod.invoke(element, params);
			mapTarget.put(key, element);
		}
		for (Iterator it = listTarget.iterator(); it.hasNext();) {
			Object element = it.next();
			Object key = idMethod.invoke(element, params);
			if (!mapSource.containsKey(key)) {
				this.service.delete(element);
				it.remove();
			} else {
				mergeObject(element, mapSource.get(key), isCopyNull);
			}
		}
		Class[] setParamsClasses = { parent.getClass() };
		Object[] setParams = { parent };
		for (Object element : listSource) {
			Object key = idMethod.invoke(element, params);
			if ((key == null) || (!mapTarget.containsKey(key))) {
				listTarget.add(element);
				BeanUtils.invoke(element, refSetMethod, setParamsClasses,
						setParams);
			}
		}
	}

	private void mergeObject(Object target, Object source, boolean isCopyNull)
			throws Exception {
		if ((target == null) || (source == null)) {
			return;
		}
		List<Method> getterMethodList = BeanUtils.getGetter(source.getClass());
		List<Method> setterMethodList = BeanUtils.getSetter(target.getClass());
		Map<String, Method> map = new HashMap();
		for (Method method : getterMethodList) {
			map.put(method.getName(), method);
		}
		for (Method method : setterMethodList) {
			String fieldName = method.getName().substring(3);
			Method getterMethod = (Method) map.get("get" + fieldName);
			if (getterMethod == null) {
				getterMethod = (Method) map.get("is" + fieldName);
			}
			if ((getterMethod != null) &&

			(needHandleMethod(getterMethod))) {
				if (!supportTypeMap.containsKey(getterMethod.getReturnType())) {
					logger.warn("not supprot type: "
							+ getterMethod.getReturnType());
				} else {
					Object value = getterMethod.invoke(source, new Object[0]);
					if (getterMethod.getReturnType() == List.class) {
						Object target_value = getterMethod.invoke(target,
								new Object[0]);
						mergeList((List) target_value, (List) value,
								isCopyNull, target);
					} else if (isCopyNull) {
						method.invoke(target, new Object[] { value });
					} else if (value != null) {
						method.invoke(target, new Object[] { value });
					}
				}
			}
		}
	}

	public void mergeComplexObjectToTargetFromSource(Object target,
			Object source, boolean isCopyNull) {
		try {
			mergeObject(target, source, isCopyNull);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	   static
	   {
	     supportTypeMap.put(Integer.TYPE, "");
	     supportTypeMap.put(Long.TYPE, "");
	     supportTypeMap.put(Double.TYPE, "");
	    supportTypeMap.put(Boolean.TYPE, "");
	     supportTypeMap.put(Integer.class, "");
	     supportTypeMap.put(Long.class, "");
	     supportTypeMap.put(Double.class, "");
	     supportTypeMap.put(BigDecimal.class, "");
	     supportTypeMap.put(String.class, "");
	     supportTypeMap.put(Date.class, "");
	     supportTypeMap.put(Boolean.class, "");
	     supportTypeMap.put(byte[].class, "");
	     supportTypeMap.put(List.class, "");
	   }
}