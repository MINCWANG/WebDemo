<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<%@include file="/WEB-INF/include/manager_navigate.jsp"%>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty requestScope.orderList}">
				<h2>顾客没有下单</h2>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>日期</td>
						<td>金额</td>
						<td>详情</td>
						<td>发货</td>

					</tr>
					<c:forEach items="${requestScope.orderList}" var="order">
						<tr>
							<td>${order.orderTime}</td>
							<td>${order.totalAmount}</td>
							<td>
								<c:if test="${order.state == 0}"><a href="OrderManagerServlet?method=sendGoods&orderId=${order.id}">点击发货</a></c:if>
								<c:if test="${order.state == 1}">已发货</c:if>
								<c:if test="${order.state == 2}">订单完成</c:if>

							<td><a href="#">查看详情</a></td></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>

	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>