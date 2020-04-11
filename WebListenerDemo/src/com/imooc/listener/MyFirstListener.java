package com.imooc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyFirstListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("context destroy");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("context init  ");

	}

}
