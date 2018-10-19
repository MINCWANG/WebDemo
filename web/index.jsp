<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <!--  通过include 引入 base.jsp -->
    <%@include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
    <jsp:forward page="/BookClientServlet?method=findPage"></jsp:forward>
</body>
</html>