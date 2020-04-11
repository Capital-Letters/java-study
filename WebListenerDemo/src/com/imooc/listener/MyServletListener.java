package com.imooc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		String appName = (String) sce.getServletContext().getAttribute("appName");
		String version = (String) sce.getServletContext().getAttribute("version");
		
		System.out.println("context destroy appName:"+appName+" version:"+version);

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String appName = sce.getServletContext().getInitParameter("app_name");
		String version = sce.getServletContext().getInitParameter("version");
		
		sce.getServletContext().setAttribute("appName", appName);
		sce.getServletContext().setAttribute("version", version);
		
		System.out.println("contextinit appName:"+appName+" version:"+version);
	}

}
