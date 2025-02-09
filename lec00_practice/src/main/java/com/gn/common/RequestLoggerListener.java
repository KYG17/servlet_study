package com.gn.common;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestLoggerListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("요청이 처리되었습니다");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("새로운 요청이 들어왔습니다");
	}
	
	

}
