package com.gn.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UpperCaserWrapper extends HttpServletRequestWrapper {

	public UpperCaserWrapper(HttpServletRequest request) {
		super(request);
		
	}

	@Override
	public String getParameter(String name) {
//		if(name.equals("msg")) {
//			return super.getParameter(name).toUpperCase();
//		}else {
//			return super.getParameter(name);
//		}
		return super.getParameter(name).toUpperCase();
		
		
		
		
		
	}
	
	

}
