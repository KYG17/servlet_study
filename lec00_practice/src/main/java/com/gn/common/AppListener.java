package com.gn.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("서버가 종료됩니다아아아");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("서버가 시작합니다아아!!");
	}
	
	
	
	
}
