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
tr {
	display: block;
	float: left;
}

th, td {
	display: block;
}
</style>
</head>
<body>
	<br>
	<h3>この内容で変更しますか？</h3>
	<br>
	<h4>・会員情報</h4>
	<form method="post" action="confirmChange.html">
		<table border="1">
			<tr>
				<td>会員NO</td>
				<td>氏名</td>
				<td>年齢</td>
				<td>性別</td>
				<td>郵便番号</td>
				<td>住所</td>
				<td>電話番号</td>
				<td>登録日</td>
			</tr>
			<tr>
				<th>
					<c:out value="${mem202DTO.member_no}"></c:out>
					<input type="hidden" id="member_no" name="member_no" value="${mem202DTO.member_no}" readonly>
				</th>
				<th>
					<c:out value="${mem202DTO.name}"></c:out>
					<input type="hidden" id="name" name="name" value="${mem202DTO.name}" readonly>
				</th>
				<th>
					<c:out value="${mem202DTO.age}"></c:out>
					<input type="hidden" id="age" name="age" value="${mem202DTO.age}" readonly>
				</th>
				<th>
					<c:if test="${fn:contains(mem202DTO.sex, 'm')}">
						<c:out value="男性"></c:out>
						<input type="hidden" name="sex" value="m" readonly>
					</c:if>
					<c:if test="${fn:contains(mem202DTO.sex, 'f')}">
						<c:out value="女性"></c:out>
						<input type="hidden" name="sex" value="f" readonly>
					</c:if>
				</th>
				<th>
					<c:out value="${mem202DTO.zip}"></c:out>
					<input type="hidden" id="zip" name="zip" value="${mem202DTO.zip}" readonly>
				</th>
				<th>
					<c:out value="${mem202DTO.address}"></c:out>
					<input type="hidden" id="address" name="address" value="${mem202DTO.address}" readonly>
				</th>
				<th>
					<c:out value="${mem202DTO.tel}" />
					<input type="hidden" id="tel" name="tel" value="${mem202DTO.tel}" readonly>
				</th>
				<th>
					<c:out value="${mem202DTO.register_date}" />
					<input type="hidden" id="register_date" name="register_date" value="${mem202DTO.register_date}" readonly>
				</th>
			</tr>
		</table>
		<input type="hidden" id="password" name="password" value="${mem202DTO.password}">
		<input type="hidden" id="confirmPassword" name="confirmPassword" value="${mem202DTO.confirmPassword}">
		<input type="submit" value="実行">
		<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>

</html>