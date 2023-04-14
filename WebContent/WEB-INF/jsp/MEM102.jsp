<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<link rel="stylesheet" href="css/main.css">
<style>
tr { display: block; float: left; }
th, td { display: block; }
</style>
</head>
<body>
	<br>
	<h3>この内容で登録しますか？</h3>
	<br>
	<h4>・会員情報</h4>
	<form method="post" action="confirmJoin.html">
		<table border="1">
			<tr>
				<td>氏名</td>
				<td>年齢</td>
				<td>性別</td>
				<td>郵便番号</td>
				<td>住所</td>
				<td>電話番号</td>
			</tr>
			<tr>
				<th>
					<c:out value="${mem101DTO.name}"></c:out>
					<input type="hidden" id="name" name="name" value="${mem101DTO.name}" readonly>
				</th>
				<th>
					<c:out value="${mem101DTO.age}"></c:out>
					<input type="hidden" id="age" name="age" value="${mem101DTO.age}" readonly>
				</th>
				<th>
					<c:if test="${fn:contains(mem101DTO.sex, 'm')}">
						<c:out value="男性"></c:out>
						<input type="hidden" name="sex" value="m" readonly>
					</c:if>
					<c:if test="${fn:contains(mem101DTO.sex, 'f')}">
						<c:out value="女性"></c:out>
						<input type="hidden" name="sex" value="f" readonly>
					</c:if>
				</th>
				<th>
					<c:out value="${mem101DTO.zip}"></c:out>
					<input type="hidden" id="zip" name="zip" value="${mem101DTO.zip}" readonly>
				</th>
				<th>
					<c:out value="${mem101DTO.address}"></c:out>
					<input type="hidden" id="address" name="address" value="${mem101DTO.address}" readonly>
				</th>
				<th>
					<c:out value="${mem101DTO.tel}" />
					<input type="hidden" id="tel" name="tel" value="${mem101DTO.tel}" readonly>
				</th>
			</tr>
		</table>
		<input type="hidden" id="password" name="password" value="${mem101DTO.password}">
		<input type="hidden" id="confirmPassword" name="confirmPassword" value="${mem101DTO.confirmPassword}">
		<input type="submit" value="登録">
		<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>

</html>