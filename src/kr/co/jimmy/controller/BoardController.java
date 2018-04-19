package kr.co.jimmy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jimmy.DAO.BoardDAO;
import kr.co.jimmy.DAO.GuestDAO;
import kr.co.jimmy.VO.BoardVO;
import kr.co.jimmy.VO.MemberVO;

@WebServlet("/board")
public class BoardController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "";
		String cmd = request.getParameter("cmd");
		
		cmd = cmd == null?"":cmd;
		
		if("writeView".equals(cmd)) {
			url = "/WEB-INF/views/board/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if("write".equals(cmd)) {
			BoardDAO dao = new BoardDAO();
			BoardVO vo = new BoardVO();
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			vo.setUser_no(Integer.parseInt(no));
			vo.setTitle(title);
			vo.setContent(content);
			
			dao.InsertBoard(vo);
			url = "/mysite/board";
			response.sendRedirect(url);
		} else if("deleteView".equals(cmd)) {
			
		} else if("delte".equals(cmd)) {
			
			
		} else if("updateView".equals(cmd)) {
			BoardDAO dao = new BoardDAO();
			String no = request.getParameter("no");
			BoardVO vo = dao.SelectBoard(no);
			
			request.setAttribute("vo", vo);
			url = "/WEB-INF/views/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);	
		} else if("modify".equals(cmd)) {
			BoardDAO dao = new BoardDAO();
			String no = request.getParameter("no");
			int count = dao.isCheckBoard(no);
			
			if(count == 1) {
				url = "/WEB-INF/views/board/modify.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);	
			}else {
				url = "/mysite/board?cmd=updateView&result=fail";
				response.sendRedirect(url);
			}
		}else{
			BoardDAO dao = new BoardDAO();
			ArrayList<BoardVO> list = dao.ListBoard();
			request.setAttribute("list", list);
			
			url = "/WEB-INF/views/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}	
