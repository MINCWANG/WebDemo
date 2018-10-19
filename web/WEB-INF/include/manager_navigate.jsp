<%--
  Created by IntelliJ IDEA.
  User: Wang MC
  Date: 2018/9/20
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
	<%-- 请求交给BookManagerServetl查询图书集合时需要一个参数 method=方法名
		模拟get请求携带参数
	--%>
	<a href="manager/BookManagerServlet?method=findPage&pageNumber=1">图书管理</a>
	<a href="pages/manager/order_manager.jsp">订单管理</a>
	<a href="index.jsp">返回商城</a>
</div>