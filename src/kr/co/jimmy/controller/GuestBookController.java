package kr.co.jimmy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jimmy.DAO.GuestDAO;
import kr.co.jimmy.VO.GuestVO;

@WebServlet("/guest")
public class GuestBookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "";
		String cmd = request.getParameter("cmd");
		cmd = cmd == null ? "" : cmd;

		if ("writer".equals(cmd)) {
			GuestDAO dao = new GuestDAO();
			GuestVO vo = new GuestVO();

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);

			dao.BoardWrite(vo);

			response.sendRedirect("/mysite/guest");

		} else if ("delete".equals(cmd)) {
			GuestDAO dao = new GuestDAO();
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			int count = dao.BoardDelete(password, no);
			
			url = "/mysite/guest?cmd=deleteform&result=fail";
			if(count == 1) {
				url = "/mysite/guest";
			}
			response.sendRedirect(url);
		} else if ("deleteform".equals(cmd)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(request, response);
		} else {
			GuestDAO dao = new GuestDAO();
			ArrayList<GuestVO> list = dao.BoardList();
			request.setAttribute("userList", list);

			url = "/WEB-INF/views/guestbook/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
}
