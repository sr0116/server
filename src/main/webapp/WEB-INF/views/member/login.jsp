<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp" />
</head>

<body>

<form method="post">
		<input name="id" placeholder="id" value="sr">
		<input type="password" name="pw" value="sr">
		<button>로그인</button>
	<p class="text-danger">${msg}</p>
	</form>
	
</body>
</html>