<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/WEB-INF/include/base.jsp"%>
	<script>
		$(function () {
		    // 给文本输入框设置改变监听，内容改变时，将修改数量之后的购物项交给cartServlet
			$(".countIpt").change(function () {
                var count = this.value;
                var bookId = this.id;
                if (isNaN(count)) {
                     this.value = this.defaultValue;
                    alert("请输入一个正确的数字");
				}else {
                    <%--alert('${pageContext.request.contextPath}/CartServlet?method=updateCount&bookId="+'+bookId+'+"&count="+'+count);--%>
                    window.location = "${pageContext.request.contextPath}/CartServlet?method=updateCount&bookId="+bookId+"&count="+count;

                }
            })
        })
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/WEB-INF/include/user_header.jsp"%>
	</div>
	
	<div id="main">
	<c:choose>
		<c:when test="${!empty cart.cartItemList}">
			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${cart.cartItemList}" var="item">
					<tr>
						<td>${item.book.title}</td>
						<td><input type="text" id="${item.book.id}" class="countIpt"  value="${item.count}" style="width: 20px;text-align: center"></td>
						<td>${item.book.price}</td>
						<td>${item.amount}</td>
						<td><a href="CartServlet?method=delItem&bookId=${item.book.id}">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${cart.totalAmount}</span>元</span>
				<span class="cart_span"> <a href="CartServlet?method=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="checkout.jsp">去结账</a></span>
			</div>
		</c:when>
		<c:otherwise>
			<h3>一干二净，赶紧去买吧！！！</h3>
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