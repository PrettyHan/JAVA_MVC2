<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
int index = 0;
%>
<fmt:setLocale value="ja_JP" />
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
	function resetBtn() {
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
	<h4>検索条件を入力してください。</h4>
	<form action="resultGoods.html" method="get">
		<form:errors cssClass="error" path="sho101DTO.*" />
		<div>
			<label for="category_name">カテゴリ</label>
			<select name="category_name">
				<option value="">全体</option>
				<c:forEach var="category" items="${categorys}">
					<option value="${category.NAME}" <c:if test="${sho101DTO.category_name eq category.NAME}">selected</c:if>>${category.NAME}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="goods_name">商品名</label>
			<input id="goods_name" name="goods_name" value="${sho101DTO.goods_name}">
		</div>
		<div>
			<label for="goods_maker">販売元</label>
			<input id="goods_maker" name="goods_maker" value="${sho101DTO.goods_maker}">
		</div>
		<div>
			<label>金額上限</label>
			<input name="upper_limit" value="${sho101DTO.upper_limit}">
		</div>
		<div>
			<label>金額下限</label>
			<input name="lower_limit" value="${sho101DTO.lower_limit}">
		</div>
		<input type="submit" value="検索">
		<input type="reset" value="クリア" onClick="javascript:resetBtn()">
		<input type="button" value="戻る" onClick="javascript:goBack()">
	</form>
	<c:if test="${fn:length(productList) > 0}">
		<div>
			<form>
				<input type="hidden" name="returnPage" value="${returnPage}" />
				<input type="hidden" name="nextPage" value="${nextPage}" />
				<c:choose>
					<c:when test="${currentPage eq 1}">
						<input formmethod="get" formaction="frontPage.html" type="submit" value="＜＜" disabled="disabled" />
						<input formmethod="get" formaction="return.html" type="submit" value="＜" disabled="disabled" />
					</c:when>
					<c:otherwise>
						<input formmethod="get" formaction="frontPage.html" type="submit" value="＜＜" />
						<input formmethod="get" formaction="return.html" type="submit" value="＜" />
					</c:otherwise>
				</c:choose>
				<c:out value="[${currentPage} / ${endPage}]" />
				<c:choose>
					<c:when test="${currentPage eq endPage || endPage eq 1}">
						<input formmethod="get" formaction="next.html" type="submit" value="＞" disabled="disabled" />
						<input formmethod="get" formaction="backPage.html" type="submit" value="＞＞" disabled="disabled" />
					</c:when>
					<c:otherwise>
						<input formmethod="get" formaction="next.html" type="submit" value="＞" />
						<input formmethod="get" formaction="backPage.html" type="submit" value="＞＞" />
					</c:otherwise>
				</c:choose>
			</form>
		</div>
		<form action="insertCargo.html" method="get">
			<table border="1">
				<tr>
					<th>選択</th>
					<th>商品コード</th>
					<th>商品名</th>
					<th>販売元</th>
					<th>金額（単価）</th>
					<th>メモ</th>
					<th>購入数</th>
				</tr>
				<c:forEach var="PRODUCT" items="${productList}">
					<tr>
						<td>
							<input type="checkbox" name="cargoList[<%=index%>].product_code" value="${PRODUCT.PRODUCT_CODE}" />
						</td>
						<td>
							<c:out value="${PRODUCT.PRODUCT_CODE}"></c:out>
						</td>
						<td>
							<a href="retrieveGoods.html?product_code=${PRODUCT.PRODUCT_CODE}">${PRODUCT.PRODUCT_NAME}</a>
						</td>
						<td>
							<c:out value="${PRODUCT.MAKER}"></c:out>
						</td>
						<td align="right">
							<p>
								￥
								<fmt:formatNumber value="${PRODUCT.UNIT_PRICE}" pattern="###,###" />
							</p>
						</td>
						<td>
							<c:if test="${fn:length(PRODUCT.MEMO) <= 20}">
								<c:out value="${PRODUCT.MEMO}"></c:out>
							</c:if>
							<c:if test="${fn:length(PRODUCT.MEMO) > 20}">
								<c:out value="${fn:substring(PRODUCT.MEMO,0,20)}・・・"></c:out>
							</c:if>
						</td>
						<td>
							<input type="text" name="cargoList[<%=index%>].product_quantity" />
							<form:errors cssClass="error" path="cargoDTO.*" />
						</td>
					</tr>
					<%
					index += 1;
					%>
				</c:forEach>
			</table>
	</c:if>
	<c:out value="${error}"></c:out>
	<br>
	<c:if test="${fn:length(productList) > 0}">
		<input type="submit" value="お買い物かごに入れる">
	</c:if>
	<br>
	<c:if test="${fn:length(productList) == 0 and error eq null and productList ne null}">
		<c:out value="条件に該当する商品は０件です。"></c:out>
	</c:if>
	</form>
</body>
</html>