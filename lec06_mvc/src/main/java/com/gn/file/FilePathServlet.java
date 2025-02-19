package com.gn.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Attach;


@WebServlet("/filePath")
public class FilePathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FilePathServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 읽어올 파일명 전달받기 , 뭘 한거냐면 원래 Board VO 에서 newName을 했었는데 그걸 지우고 attachNo로 변경
		int attachNo = Integer.parseInt(request.getParameter("attach_no"));
		System.out.println("=========이미지 SRC: " + attachNo + "===========");
		Attach a = new BoardService().selectAttachOne(attachNo);
		
		//2. 파일명이 비어있는지 확인
		String filePath = a.getAttachPath();
		//trim.equals -> filepath의 양쪽 비어있는 공간을 없애고 비어있는 문자열이면
		if(filePath == null || filePath.trim().equals("")) {
			// 400 오류를 발생시킨다 = 잘못된 요청
			//SC_BAD_REQUEST 400을 의미
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//3. 파일 경로에 파일이 존재하는지 확인
		File file = new File(filePath);
		//! 파일이 존재하지 않는다면
		if(!file.exists()) {
			//404오류 ! 위랑 다르다 NOT FOUND ERROR , 요청한 파일을 찾을 수 없을 경우
			//SC_NOT_FOUND
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		//4.MIME 타입 감지(모든 형태)
		//너 mimeType 읽어와바
		String mimeType = getServletContext().getMimeType(filePath);
		//애가 뭐 넘어오는게 없어요!! 근데 이상한게 넘어온다?
		if(mimeType == null) {
			//그러면 임의로 세팅
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		
		//5.파일을 읽어서(In), 클라이언트 전송(Out)
		try(FileInputStream fis = new FileInputStream(file);
				OutputStream out = response.getOutputStream()) {
			byte[] buffer = new byte[1024];
			int byteRead;
			while((byteRead =fis.read(buffer)) != -1) {
				out.write(buffer,0,byteRead);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
