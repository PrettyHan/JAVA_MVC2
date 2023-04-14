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
	<h3>この情報を削除しますか？</h3>
	<br>
	<h4>・会員情報</h4>
	<form method="post" action="deleteMember.html">
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
					<c:out value="${member.MEMBER_NO}"></c:out>
					<input type="hidden" id="member_no" name="member_no" value="${member.MEMBER_NO}" readonly>
				</th>
				<th>
					<c:out value="${member.NAME}"></c:out>
				</th>
				<th>
					<c:out value="${member.AGE}"></c:out>
				</th>
				<th>
					<c:if test="${fn:contains(member.SEX, 'm')}">
						<c:out value="男性"></c:out>
					</c:if>
					<c:if test="${fn:contains(member.SEX, 'f')}">
						<c:out value="女性"></c:out>
					</c:if>
				</th>
				<th>
					<c:out value="${member.ZIP}"></c:out>
				</th>
				<th>
					<c:out value="${member.ADDRESS}"></c:out>
				</th>
				<th>
					<c:out value="${member.TEL}" />
				</th>
				<th>
					<c:out value="${member.REGISTER_DATE}" />
				</th>
			</tr>
		</table>
		<input type="submit" value="実行">
		<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>

</html>