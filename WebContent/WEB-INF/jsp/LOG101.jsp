<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<link rel="stylesheet" href="css/main.css">

</head>
<body>
	<a href="join.html">新規登録はこちら</a>
	<br>
	<form method="post" action="login.html">
		<div>
			<label for="member_no">会員NO</label>
			<input type="text" id="member_no" name="member_no">
			<form:errors cssClass="error" path="log101DTO.member_no" />
		</div>
		<div>
			<label for="password">パスワード</label>
			<input type="password" id="password" name="password">
			<form:errors cssClass="error" path="log101DTO.password" />
		</div>
		<input type="submit" value="ログイン">
		<input type='reset' value="クリア">
	</form>
	<c:out value="${error}" />
</body>

</html>