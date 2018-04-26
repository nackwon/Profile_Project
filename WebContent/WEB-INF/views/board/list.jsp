<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.jimmy.DAO.BoardDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>boardList</title>
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
				<form id="search_form" action="" method="get">
					<input type="hidden" name="cmd" value="search">
					<input type="text" name="kwd" value="">
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
					<c:forEach items="${boardList}" var="boardList" varStatus="status">
						<tr>
						<td>${boardList.number}</td>
						<td><a href="/mysite/board?cmd=updateView&no=${boardList.number}">${boardList.title}</a></td>
						<td>${boardList.user_name}</td>
						<td>${boardList.hit}</td>
						<td>${boardList.reg_date}</td>
						<c:choose>
							<c:when test="${sessionScope.authUser != null && sessionScope.authUser.no == boardList.user_no }">
								<td><a href="/mysite/board?cmd=delete&use_no=${boardList.user_no}&no=${boardList.number}" class="del">삭제</a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<c:if test="">
						</c:if>
					</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
					<c:if test="${requestScope.totalPage > 5}">
						<li><a href="mysite/board?defaultPage=${param.defaultPage - 1 }">◀</a></li>
					</c:if>
					
					
					<c:forEach begin="${requestScope.startListBoard}" end="${requestScope.endListBoard}" step="1">
						<c:if test="${requestScope.startListBoard == param.defaultPage }">
						</c:if>
					</c:forEach> 
					
					
					<c:if test="${requestScope.endListBoard < requestScope.totalPage }">
							<li><a href="mysite/board?defaultPage=${param.defaultPage + 1 }">▶</a></li>
					</c:if>
					</ul>
				</div>
				<c:if test="${sessionScope.authUser != null}">
					<div class="bottom">
						<a href="/mysite/board?cmd=writeView" id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>

		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>