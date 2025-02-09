package com.gn.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class StringUpperWrapper extends HttpServletRequestWrapper {

	@Override
	public String getParameter(String name) {
		
		return super.getParameter(name).toUpperCase();
	}

	public StringUpperWrapper(HttpServletRequest request) {
		super(request);
		
	}
	
	

}
