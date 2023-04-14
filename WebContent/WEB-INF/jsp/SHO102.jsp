<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
<script>
function goBack() {
	let f = document.createElement('form');
	f.setAttribute('method', 'get');
	f.setAttribute('action', 'searchGoods.html');
	document.body.appendChild(f);
	f.submit();
}
</script>
</head>
<body>
	<br>
	<h4>・会員情報</h4>
	<form method="get" action="insertCargoByRetrieve.html">
		<table border="1">
			<tr>
				<td>商品名</td>
				<td height="200">画像</td>
				<td>商品説明</td>
				<td height="56">価格</td>
				<td>購入数</td>
			</tr>
			<tr>
				<th>
					<c:out value="${product.PRODUCT_NAME}"></c:out>
				</th>
				<th height="200">
					<img src="pic/${product.PICTURE_NAME}.jpg" height="200" />
				</th>
				<th>
					<c:out value="${product.MEMO}"></c:out>
				</th>
				<th align="right">
					<p>￥<fmt:formatNumber value="${product.UNIT_PRICE}" pattern="###,###"/></p>
				</th>
				<th>
					<input type="text" name="product_quantity" value="${product_quantity}" oninvalid="個数を入力してください" />
				</th>
			</tr>
		</table>
		<c:out value="${error}" />
		<br>
		<input type="hidden" name="product_code" value="${product.PRODUCT_CODE}" />

		<input type="submit" value="買い物かごに入れる">
		<input type="button" value="戻る" onClick="javascript:goBack()">
	</form>
</body>

</html>