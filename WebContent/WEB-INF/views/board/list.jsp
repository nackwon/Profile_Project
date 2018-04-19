<%@page import="kr.co.jimmy.VO.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<BoardVO> list = (ArrayList<BoardVO>) request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
<link href="/mysite/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">

		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="hidden" name="cmd" value=""> 
					<input type="text" id="kwd" name="kwd" value=""> 
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<%
						for (BoardVO vo : list) {
					%>
					<tr>
						<td><%=vo.getNumber() %></td>
						<td><a href="/mysite/board?cmd=updateView&no=<%=vo.getNumber()%>"><%=vo.getTitle() %></a></td>
						<td><%=vo.getUser_no() %></td>
						<td><%=vo.getHit() %></td>
						<td><%=vo.getReg_date() %></td>
						<td><a href="/mysite/board?cmd=deleteView" class="del">삭제</a></td>
					</tr>
					<%
						}
					%>
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<div class="bottom">
					<a href="/mysite/board?cmd=writeView" id="new-book">글쓰기</a>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>


	</div>
</body>
</html>