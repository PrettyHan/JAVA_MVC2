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
<body>
	<div class="centerDiv">
		<c:if test="${member.NAME eq null}">
			<a href="join.html">新規会員登録</a>
			<br>
		</c:if>
		<a href="updateMember.html">会員情報修正・削除</a>
		<br>
		<a href="searchGoods.html">商品検索</a>
		<br>
		<a href="showCargo.html">お買い物かご</a>
		<br>
		<c:if test="${member.NAME eq null}">
			<input type="button" onclick="location.href='./login.html'" value="ログイン">
		</c:if>
		<c:if test="${member.NAME ne null}">
			<input type="button" onclick="location.href='./logout.html'" value="ログアウト">
		</c:if>
	</div>
	<br>

</body>
</html>