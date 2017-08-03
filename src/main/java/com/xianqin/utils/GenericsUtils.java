package com.xianqin.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenericsUtils {
	private static final Log logger = LogFactory.getLog(GenericsUtils.class);

	public static Class<? extends Object> getSuperClassGenricType(Class<? extends Object> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	public static Class<? extends Object> getSuperClassGenricType(Class<? extends Object> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			logger.debug(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if ((index >= params.length) || (index < 0)) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);

			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger
					.warn(clazz.getSimpleName()
							+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return ((Class<?>) params[index]);
	}
}
