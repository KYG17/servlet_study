package com.gn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/receive/data")
//name은 필터의 별명 , urlPatterns는 inform action
@WebServlet(name="receiveDataServlet",urlPatterns="/receive/data")
public class ReceiveDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ReceiveDataServlet() {}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("--- 확인 ---");
		String data = req.getParameter("test_data");
		System.out.println("데이터 : " + data);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp); // doGet 메소드 호출
	}
	
	
	
}
