package com.gn.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MsgRequestWrapper extends HttpServletRequestWrapper {

	public MsgRequestWrapper(HttpServletRequest request) {
		super(request);
		
	}

	@Override
	public String getParameter(String name) {
		return super.getParameter(name)+"하세요";
		
	}
	
	
	
	

}
