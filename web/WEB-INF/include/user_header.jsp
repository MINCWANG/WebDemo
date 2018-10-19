<%--
  Created by IntelliJ IDEA.
  User: Wang MC
  Date: 2018/9/20
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
	<c:when test="${!empty user}">
		<div>
			<span>欢迎 <span class="um_span">${user.username}</span>光临书城</span>

			<a href="UserServlet?method=logout">注销</a> &nbsp;&nbsp;
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="pages/manager/manager.jsp">后台管理</a>
		</div>

	</c:when>
	<c:otherwise>
		<div>
			<a href="pages/user/login.jsp">登录</a> |
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="pages/manager/manager.jsp">后台管理</a>
		</div>

	</c:otherwise>
</c:choose>

