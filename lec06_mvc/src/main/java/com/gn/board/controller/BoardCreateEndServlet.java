package com.gn.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

/**
 * Servlet implementation class BoardCreateEndServlet
 */
@WebServlet("/boardCreateEnd")
public class BoardCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardCreateEndServlet() {
        super();
     
    }


	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청시 전달된 데이터를 담을 바구니
		Board b = new Board();
		Attach a = new Attach();
		
		//2. 파일 업로드할 경로 설정
		String path = "C:\\upload\\board";
		File dir = new File(path);
		//만약에 이 dir이 존재하지 않는다면 만들어주세요!
		if(! dir.exists()) {
			dir.mkdirs();
		}
		
		//3. 파일을 업로드 할 저장 공간 정보 셋팅
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//(1)저장은 어디에? dir에! -> setRepository
		factory.setRepository(dir);
		//(2)파일 크기는 얼마나?! -> setSizeThreshold
		// byte -> KB -> MB -> GB -> TB
		//1024 -> 1KB
		factory.setSizeThreshold(1024*1024*10);
		
		//4. 요청을 통해 전달된 데이터 읽어오기
		//매개 변수로 세팅한 정보를 넣어주기 -> factory
		//try ~ catch문이 있어야 함
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//요청을 한 request가 매개변수
			List<FileItem> items = upload.parseRequest(request);
			for(int i = 0 ; i < items.size(); i++) {
				FileItem fileItem = items.get(i);
				if(fileItem.isFormField()) {
					// (1) 파일이 아닌 폼 내부 요소 -> input type = "text" 아님 "password"
					//System.out.println(fileItem.getFieldName());
					switch(fileItem.getFieldName()) {
					case "board_title" : b.setBoardTitle(fileItem.getString("utf-8")); 
						break;
					case "board_content" : b.setBoardContent(fileItem.getString("utf-8"));
						break;
					case "board_writer" : b.setBoardWriter(Integer.parseInt(fileItem.getString("utf-8")));
						break;
					}
				}else {
					// (2) 파일 형태의 폼 요소 -> input type = "file"
					//System.out.println(fileItem.getName());
					if(fileItem.getSize() > 0) {
						//새로운 파일 이름 만들기 !
						String oriName = fileItem.getName();
						int idx = oriName.lastIndexOf(".");
						String ext = oriName.substring(idx);
						
						String uuid = UUID.randomUUID().toString().replace("-", "");
						String newName = uuid+ext;
						
						//세팅해준 dir 에 넣어주기
						File uploadFile = new File(dir,newName);
						fileItem.write(uploadFile);
						
						a.setOriName(oriName);
						a.setNewName(newName);
						//C:\\upload\\board\\새로운이름.확장자
						a.setAttachPath(path+"\\"+newName);
					}
				}
			}
			//1. 바구니에 데이터 들어있는지 확인
			System.out.println(b);
			System.out.println(a);
			//2. 지정한 경로에 파일 업로드 되었는지 확인!
			int result = new BoardService().createBoard(b,a);
			//RequestDispatcher view = request.getRequestDispatcher("views/board/create_fail.jsp");
			JSONObject obj = new JSONObject();
			if(result > 0) {
				//view = request.getRequestDispatcher("/views/board/create_success.jsp");
				obj.put("res_code",200);
				obj.put("res_msg", "정상적으로 게시글이 등록되었습니당!");
			}else {
				obj.put("res_code",500);
				obj.put("res_msg", "게시글 등록 중 오류가 발생하였습니당!");
				String deletePath = a.getAttachPath();  
				File deleteFile = new File(deletePath);
				if(deleteFile.exists()) {
					deleteFile.delete();
				}
			}
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(obj);
			//view.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
