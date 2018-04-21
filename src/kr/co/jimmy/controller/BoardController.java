package kr.co.jimmy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.websocket.SendResult;

import org.omg.CORBA.BAD_INV_ORDER;

import kr.co.jimmy.DAO.BoardDAO;
import kr.co.jimmy.DAO.MemberDAO;
import kr.co.jimmy.VO.BoardVO;
import kr.co.jimmy.VO.MemberVO;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "";
		String cmd = request.getParameter("cmd");
	
		cmd = cmd == null ? "" : cmd;
		if ("writeView".equals(cmd)) {
			url = "/WEB-INF/views/board/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if ("search".equals(cmd)) {
			String kwd = request.getParameter("kwd");
			System.out.println(kwd);
			BoardDAO dao = new BoardDAO();
			ArrayList<BoardVO> list = dao.SearchBoard(kwd);

			request.setAttribute("boardList", list);

			url = "/WEB-INF/views/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);

		} else if ("write".equals(cmd)) {
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
		} else if ("delete".equals(cmd)) {
			BoardDAO dao = new BoardDAO();
			HttpSession session = request.getSession();
			MemberVO member = (MemberVO) session.getAttribute("authUser");
			if (member == null) {
				url = "/mysite/user?cmd=loginform";
				response.sendRedirect(url);
			} else {
				
				int use_no = Integer.parseInt(request.getParameter("use_no"));
				int user_no = member.getNo();
				int count = dao.isCheckBoard(user_no, use_no);
				if (count == 1) {
					int no = Integer.parseInt(request.getParameter("no"));
					dao.DeleteBoard(no);

					url = "/mysite/board";
					response.sendRedirect(url);
				} else {
					url = "/mysite/board";
					response.sendRedirect(url);
				}
			}

		} else if ("updateView".equals(cmd)) {

			BoardDAO dao = new BoardDAO();
			int no = Integer.parseInt(request.getParameter("no"));
			BoardVO vo = dao.SelectBoard(no);
	
			request.setAttribute("updateVo", vo);
			url = "/WEB-INF/views/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if ("modify".equals(cmd)) {
			BoardDAO dao = new BoardDAO();

			HttpSession session = request.getSession();
			MemberVO member = (MemberVO) session.getAttribute("authUser");

			if (member == null) {
				url = "/mysite/user?cmd=loginform";
				response.sendRedirect(url);
			} else {
				String content_no = request.getParameter("content_no");
				int no = Integer.parseInt(request.getParameter("no"));
				int user_no = member.getNo();
				
				int count = dao.isCheckBoard(user_no, no);

				if (count == 1) {
					
					BoardVO vo = dao.SelectBoard(no);

					request.setAttribute("modifyVo", vo);
					url = "/WEB-INF/views/board/modify.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(url);
					rd.forward(request, response);
				} else {
					url = "/mysite/board?cmd=updateView&no=" + content_no;
					response.sendRedirect(url);
				}
			}
		} else if ("update".equals(cmd)) {
			System.out.println("들어옴");
			BoardDAO dao = new BoardDAO();
			BoardVO vo = new BoardVO();
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String user_no = request.getParameter("no");

			System.out.println(user_no);

			vo.setTitle(title);
			vo.setContent(content);

			dao.UpdateBoard(vo, user_no);

			url = "/mysite/board?cmd=updateView&no=" + user_no;
			response.sendRedirect(url);
		}

		else {
			BoardDAO dao = new BoardDAO();
			
			ArrayList<BoardVO> list = dao.ListBoard();
			
			request.setAttribute("boardList", list);

			url = "/WEB-INF/views/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
