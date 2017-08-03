package com.xianqin.common;

import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServiceFactory {
	private static WebApplicationContext context;

	public static void initServiceFactory(ServletContext servletContext) {
		context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
	}

	public static Object getService(ServletContext servletContext,
			String serviceName) {
		return context.getBean(serviceName);
	}

	public static Object getService(String serviceName) {
		return context.getBean(serviceName);
	}
}
