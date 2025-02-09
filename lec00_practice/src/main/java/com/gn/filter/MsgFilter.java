package com.gn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.gn.common.MsgRequestWrapper;

public class MsgFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		MsgRequestWrapper mrw = new MsgRequestWrapper
				((HttpServletRequest)request);
		chain.doFilter(mrw, response);
		
	}

	
	
	
}
