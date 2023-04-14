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
</head>
<style>
tr {
	display: block;
	float: left;
}

th, td {
	display: block;
}
</style>
<script>
	function goBack() {
		let f = document.createElement('form');
		f.setAttribute('method', 'get');
		f.setAttribute('action', 'menu.html');
		document.body.appendChild(f);
		f.submit();
	}
	function resetBtn() {
		let f = document.createElement('form');
		f.setAttribute('method', 'get');
		f.setAttribute('action', 'join.html');
		document.body.appendChild(f);
		f.submit();
		}
</script>

<body>
	<br>
	<h3>会員情報を入力してください。</h3>
	<br>
	<h4>・会員情報</h4>
	<form method="get" action="confirmJoin.html" name="form">
		<form:errors cssClass="error" path="mem101DTO.*" />
		<table border="1">
			<tr>
				<td>氏名</td>
				<td>パスワード</td>
				<td>確認用パスワード</td>
				<td>年齢</td>
				<td>性別</td>
				<td>郵便番号</td>
				<td height="63">住所</td>
				<td>電話番号</td>
			</tr>
			<tr>
				<th>
					<input type="text" id="name" name="name" value="${mem101DTO.name}">
				</th>
				<th>
					<input type="password" id="password" name="password">
				</th>
				<th>
					<input type="password" id="confirmPassword" name="confirmPassword">
				</th>
				<th>
					<input type="text" id="age" name="age" value="${mem101DTO.age}">
				</th>
				<th>
					<select name="sex">
						<option value='m' <c:if test="${fn:contains(mem101DTO.sex, 'm')}">selected</c:if>>男性</option>
						<option value='f' <c:if test="${fn:contains(mem101DTO.sex, 'f')}">selected</c:if>>女性</option>
					</select>
				</th>
				<th>
					<input type="text" id="zip" name="zip" value="${mem101DTO.zip}">
				</th>
				<th height="63">
					<textarea id="address" name="address" rows="4" cols="40">${mem101DTO.address}</textarea>
				</th>
				<th>
					<input id="tel" name="tel" value="${mem101DTO.tel}">
				</th>
			</tr>
		</table>
		<input type="submit" value="確認">
		<input type="button" value="戻る" onClick="javascript:goBack()">
		<input type="reset" value="クリア" onClick="javascript:resetBtn()">
	</form>
</body>

</html>