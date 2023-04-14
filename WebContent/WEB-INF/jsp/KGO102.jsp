<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
int index = 0;
%>
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
		f.setAttribute('action', 'searchGoods.html');
		document.body.appendChild(f);
		f.submit();
	}
	function goCargo() {
		let f = document.createElement('form');
		f.setAttribute('method', 'get');
		f.setAttribute('action', 'showCargo.html');
		document.body.appendChild(f);
		f.submit();
	}
</script>
<style>
.tr {
	display: block;
	float: left;
}

.th, .td {
	display: block;
}
</style>

</head>
<body>
	<br>
	<h3>以下の商品がお買い物かごに登録されています。</h3>
	<br>
	<h4>・商品一覧</h4>
	<form method="post" action="insertOrder.html">
		<table border="1">
			<tr>
				<td>商品コード</td>
				<td>商品名</td>
				<td>販売元</td>
				<td>価格</td>
				<td>購入数</td>
			</tr>
			<c:forEach var="cargo" items="${sessionCargo}">
				<tr>
					<th>
						<c:out value="${cargo.seletedProduct_code}"></c:out>
					</th>
					<th>
						<c:out value="${cargo.product_name}"></c:out>
					</th>
					<th>
						<c:out value="${cargo.maker}"></c:out>
					</th>
					<th align="right">
						<c:out value="￥${cargo.unit_price}"></c:out>
					</th>
					<th>
						<c:out value="${cargo.product_quantity}"></c:out>
					</th>
				</tr>
			</c:forEach>
		</table>
		<c:out value="${error}"></c:out>
		<table border="1">
			<tr class="tr">
				<td class="td" height="56" align="center">小計</td>
				<td class="td" height="56" align="center">消費税</td>
				<td class="td" height="56" align="center">合計金額</td>
			</tr>
			<tr class="tr">
				<th class="th" align="right">
				<p>￥<fmt:formatNumber value="${charge.TOTAL_SUB}" pattern="###,###"/></p>
				</th>
				<th class="th" align="right">
				<p>￥<fmt:formatNumber value="${charge.TOTAL_TAX}" pattern="###,###"/></p>
				</th>
				<th class="th" align="right">
				<p>￥<fmt:formatNumber value="${charge.TOTAL_MONEY}" pattern="###,###"/></p>
				</th>
			</tr>
		</table>
		<br>
		<input type="submit" value="買い物をやめる" formaction="deleteAllCargo.html">
		<input type="submit" value="注文する">
		<input type="button" value="戻る" onClick="javascript:goCargo()">
	</form>

</body>

</html>