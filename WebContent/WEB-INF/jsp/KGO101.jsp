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
		f.setAttribute('action', 'menu.html');
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
</head>
<body>
	<br>
	<h3>以下の商品がお買い物かごに登録されています。</h3>
	<br>
	<h4>・商品一覧</h4>
	<form method="post" action="confirmOrder.html">
		<table border="1">
			<tr>
				<th>選択</th>
				<th>商品コード</th>
				<th>商品名</th>
				<th>販売元</th>
				<th>価格</th>
				<th>購入数</th>
			</tr>
			<c:forEach var="cargo" items="${sessionCargo}">
				<tr>
					<td>
						<input type="checkbox" name="cargoList[<%=index%>].product_code" value="${cargo.product_code}">
					</td>
					<td>
						<c:out value="${cargo.product_code}"></c:out>
					</td>
					<td>
						<c:out value="${cargo.product_name}"></c:out>
					</td>
					<td>
						<c:out value="${cargo.maker}"></c:out>
					</td>
					<td align="right">
						<p>￥<fmt:formatNumber value="${cargo.unit_price}" pattern="###,###"/></p>
					</td>
					<td>
						<input type="text" name="cargoList[<%=index%>].product_quantity" value="${cargo.product_quantity}" />
					</td>
				</tr>
				<input type="hidden" name="cargoList[<%=index%>].seletedProduct_code" value="${cargo.product_code}">
				<%
				index += 1;
				%>
			</c:forEach>
		</table>
		<c:out value="${error}"></c:out>
		<br>
		<input type="submit" value="取り消し" formaction="deleteCargo.html">
		<input type="submit" value="買い物をやめる" formaction="deleteAllCargo.html">
		<input type="submit" value="注文する">
		<input type="button" value="戻る" onClick="javascript:goBack()">
	</form>

</body>

</html>