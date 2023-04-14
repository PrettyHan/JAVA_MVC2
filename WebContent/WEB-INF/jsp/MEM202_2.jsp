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
<script>
	function goBack() {
		let f = document.createElement('form');
		f.setAttribute('method', 'get');
		f.setAttribute('action', 'updateMember.html');
		document.body.appendChild(f);
		f.submit();
	}
	function goReset() {
		let f = document.createElement('form');
		f.setAttribute('method', 'get');
		f.setAttribute('action', 'changeMember.html');
		document.body.appendChild(f);
		f.submit();
	}
</script>
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
	<h3>会員情報を入力してください。</h3>
	<h4>・会員情報</h4>
	<form method="get" action="confirmChange.html">
		<table border="1">
			<tr>
				<td>会員NO</td>
				<td>氏名</td>
				<td>パスワード</td>
				<td>確認用パスワード</td>
				<td>年齢</td>
				<td>性別</td>
				<td>郵便番号</td>
				<td height="63">住所</td>
				<td>電話番号</td>
				<td>登録日</td>
			</tr>
			<tr>
				<form:errors cssClass="error" path="mem202DTO.*" />
				<c:out value="${error}" />
				<th>
					<c:out value="${member.MEMBER_NO}"></c:out>
					<input type="hidden" id="member_no" name="member_no" value="${member.MEMBER_NO}">
				</th>
				<th>
					<input type="text" id="name" name="name" value="${mem202DTO.name}">
				</th>
				<th>
					<input type="password" id="password" name="password">
				</th>
				<th>
					<input type="password" id="confirmPassword" name="confirmPassword">
				</th>
				<th>
					<input type="text" id="age" name="age" value="${mem202DTO.age}">
				</th>
				<th>
					<select name="sex">
						<option value='m' <c:if test="${fn:contains(mem202DTO.sex, 'm')}">selected</c:if>>男性</option>
						<option value='f' <c:if test="${fn:contains(mem202DTO.sex, 'f')}">selected</c:if>>女性</option>
					</select>
				</th>
				<th>
					<input type="text" id="zip" name="zip" value="${mem202DTO.zip}">
				</th>
				<th>
					<textarea id="address" name="address" rows="4" style="width: 100%; border: 0; resize: none;">${mem202DTO.address}</textarea>
				</th>
				<th>
					<input type="text" id="tel" name="tel" value="${mem202DTO.tel}">
				</th>
				<th>
					<c:out value="${member.REGISTER_DATE}"></c:out>
					<input type="hidden" id="register_date" name="register_date" value="${member.REGISTER_DATE}">
				</th>
			</tr>
		</table>
		<input type="submit" value="確認">
		<input type="button" value="戻る" onClick="javascript:goBack()">
		<input type="reset" value="クリア" onClick="javascript:goReset()">
	</form>
</body>

</html>