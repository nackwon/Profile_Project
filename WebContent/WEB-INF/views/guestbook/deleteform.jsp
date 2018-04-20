<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
<link href="/mysite/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<title>Insert title here</title>
</head>
<body>
	<div id="container">

		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		<div id="wrapper">
			<div id="content">
				<div id="guestbook" class="delete-form">
					<form method="get" action="/mysite/guest">
						<input type="hidden" name="cmd" value="delete"> <input
							type="hidden" name="no" value="${param.no}"> <label>비밀번호</label>
						<input type="password" name="password"> <input
							type="submit" value="확인">
							<c:if test="${param.result != null}">
							<br>
							<p>
								비밀번호가 틀립니다.<br> 다시 입력해 주세요
							</p>
							</c:if>
					</form>
					<a href="/mysite/guest">방명록 리스트</a>

				</div>
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->

</body>
</html>
