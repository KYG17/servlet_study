package com.gn.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Attach;


@WebServlet("/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public FileDownloadServlet() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 읽어올 파일명 전달받기
		//attach_no -> a태그 뒤에 ? 에 있는 key값
		int attachNo = Integer.parseInt(request.getParameter("attach_no"));
		Attach a = new BoardService().selectAttachOne(attachNo);
		
		//2.파일명 비어있는지 확인
		String filepath = a.getAttachPath();
		if(filepath == null || filepath.trim().equals("")) {
			//400에러를 발생시킴 -> 잘못된 요청입니다!!
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//3.파일 경로에 파일 존재하는지 확인
		//File 객체 생성!
		File file = new File(filepath);
		if(!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		//4. 파일 다운로드 응답 헤더 설정
		response.setContentType("application/octet-stream");
		//이렇게 하면 자바가 file의 파일크기를 알 수 있음
		//long 이기 때문에 int 로 다운케스팅을 해줘야함!
		response.setContentLength((int)file.length());
		
		//5.파일명 설정 ! 우리는 한국인 -> 그래서 encording도 세팅을 해줘야함 !!
		//이름만 세팅 하는게 아니라 인코딩 방식도 확인해줘야 한다!!
		//매개변수로 oriname ! 두번째로 어떤 걸로 인코딩 할래? , replaceAll 꼭 해줘야함 ! 규율이다
		String encodedFileName = URLEncoder.encode(a.getOriName(),"UTF-8").replaceAll("\\+","%20");
		response.setHeader("Content-Disposition","attachment; filename=\""+encodedFileName+"\"");
		
		//6.파일 데이터를 클라이언트에게 보내주자! 다운로드를 해주는거다
		//밖에 있는거를 자바로 읽어오고 , 클라이언트에게 보내준다
		try(FileInputStream fis = new FileInputStream(file);
				OutputStream out = response.getOutputStream()){
			byte[] buffer = new byte[1024];
			int byteRead;
			while((byteRead = fis.read(buffer)) != -1) {
				out.write(buffer,0,byteRead);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
			

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
