package com.gn.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import com.gn.common.wrapper.UpperCaserWrapper;


public class UpperCaseFilter extends HttpFilter implements Filter {


	private static final long serialVersionUID = 1L;

	public UpperCaseFilter() {
		super();
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("UpperCaseFilter");
		UpperCaserWrapper ucw = new UpperCaserWrapper((HttpServletRequest)req);
		chain.doFilter(ucw, res);
	}
	
	
	
	

}
