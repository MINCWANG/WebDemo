<%--
  Created by IntelliJ IDEA.
  User: Wang MC
  Date: 2018/9/20
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 动态获取访问地址--%>

<base href="http://<%= request.getServerName()%>:<%= request.getServerPort()%><%= request.getContextPath()%>/"/>
<link type="text/css" rel="stylesheet" href="static/cs/c.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>