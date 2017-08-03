package com.xianqin.common;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xianqin.dao.impl.DomainDaoImpl;



public class StartupListener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		// 初始化ServiceFactory
		ServiceFactory.initServiceFactory(sce.getServletContext());
		// 设置查询优化
		DomainDaoImpl.setOptimizeFind(true);


		
	}
}
