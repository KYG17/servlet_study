package com.gn.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

//@WebFilter("/receive/*")
//@WebFilter("/*")
//@WebFilter("/receive/data")
//서블릿의 별명을 servletNames에 작성
//별명이 receiveDataServlet인 서블릿을 필터해줄거에요!
@WebFilter(servletNames = "receiveDataServlet")
public class DataFilter extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = 1L;

	public DataFilter() {
		super();
		System.out.println("[DataFilter] 기본 생성자 생성됨!");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("[DataFilter] inin() : 필터 초기화");
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("[DataFilter] 요청 가로챔");
		//chain.doFilter를 기준으로 위에는 요청을 가로챔
		chain.doFilter(req, res);
		//chain.doFilter를 기준으로 아래는 요청을 가로챔
		System.out.println("[DataFilter] 응답 가로챔");	
	}
	
	@Override
	public void destroy() {
		//필터가 필요없어질 때 실행
		System.out.println("[DataFilter] 필터 종료");
	}


	

}
