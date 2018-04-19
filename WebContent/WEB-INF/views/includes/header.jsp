<%@page import="kr.co.jimmy.VO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVO vo = (MemberVO) session.getAttribute("authUser");
	//실제로 vo에는 주소값이 담겨있다
	
%>
<!-- /header -->
<div id="header">
	<a href="/mysite/main"><h1>MySite</h1></a>
	<ul>
		<%
			if (vo == null) {
		%>
		<!-- 로그인 전 -->
		<li><a href="/mysite/user?cmd=loginform">로그인</a></li>
		<li><a href="/mysite/user?cmd=joinform">회원가입</a></li>
		<%
			} else {
		%>
		<!-- 로그인 후 -->

		<li><a href="/mysite/user?cmd=modifyform">회원정보수정</a></li>
		<li><a href="/mysite/user?cmd=logout">로그아웃</a></li>
		<li><%=vo.getName()%>님</li>
		<%
			}
		%>
	</ul>
</div>