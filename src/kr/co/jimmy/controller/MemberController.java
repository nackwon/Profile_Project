package kr.co.jimmy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

import kr.co.jimmy.DAO.MemberDAO;
import kr.co.jimmy.VO.MemberVO;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		String url = "";
		cmd = cmd == null ? "" : cmd;
		System.out.println(cmd + "이 선택");
		if ("joinform".equals(cmd)) {
			url = "/WEB-INF/views/user/joinform.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if ("join".equals(cmd)) {

			String id = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			MemberVO vo = new MemberVO();
			MemberDAO dao = new MemberDAO();

			vo.setName(id);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);

			dao.InsertUser(vo);

			url = "/WEB-INF/views/user/joinsuccess.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);

		} else if ("loginform".equals(cmd)) {
			url = "/WEB-INF/views/user/loginform.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if ("login".equals(cmd)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			MemberDAO dao = new MemberDAO();
			MemberVO vo = dao.LoginUser(email, password);

			if (vo == null) {
				// 로그인 실패
				System.out.println("로그인 실패");
				url = "/mysite/user?cmd=loginform&result=fail";
				response.sendRedirect(url);
			} else {
				// 로그인 성공
				System.out.println("로그인 성공");
				HttpSession session = request.getSession();
				session.setAttribute("authUser", vo);

				url = "/mysite/main";
				response.sendRedirect(url);
			}
		} else if ("logout".equals(cmd)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			// invalidate는 모든 세션이 없어지는 것
			// remove는 해당하는 이름으로 지운다
			url = "/mysite/main";
			response.sendRedirect(url);
		} else if ("modifyform".equals(cmd)) {
			url = "/WEB-INF/views/user/modifyform.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if ("update".equals(cmd)) {
			
			MemberDAO dao = new MemberDAO();
			
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			MemberVO vo = new MemberVO(name, password, gender);
			
			dao.UpdateUser(vo, no);
			url = "/mysite/main";
			response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
