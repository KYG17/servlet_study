package com.gn.common.sql;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		try {
			//1. mybatis-config.xml의 설정 정보 읽어오기
			String path = "/mybatis-config.xml";
			InputStream is = Resources.getResourceAsStream(path);
			//2. SqlSessionFactoryBuilder 객체 생성
			SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
			//3.SqlSessionFactory 객체 생성
			SqlSessionFactory factory = sfb.build(is);
			//4.SqlSession 객채 생성 ->공장을 가동!!
			//매개변수 -> AutoCommit여부 지정 ->default : true;
			//AutoCommit 끄고 싶으면 openSession(false) 로 지정
			// 1. 매개변수 X : AutoCommit X
			// 2. true : AutoCommit O
			// 3. false : AutoCommit x
			session = factory.openSession(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
}
