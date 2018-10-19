<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@include file="/WEB-INF/include/base.jsp" %>
    <script>
        $(function () {
            $('.delBook').click(function () {
                // 点击时的图书名称
                var $title = $(this).parents().children('td:first').text();
                if (!confirm("你确定要删除<<" + $title + ">>吗？")) {
                    return false;
                }
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>

    <%@include file="/WEB-INF/include/manager_navigate.jsp" %>
</div>

<div id="main">
    <table>
        <%--判断集合是否为空--%>
        <c:choose>
            <c:when test="${empty requestScope.page.data}">
                <%-- 集合为空显示 --%>
                <tr>
                    <td colspan="7" align="center"><h3>大佬,请上架新图书！</h3></td>
                </tr>
            </c:when>
            <c:otherwise>
                <%-- 集合不为空，有图书时显示 --%>
                <tr>
                    <td>名称</td>
                    <td>价格</td>
                    <td>作者</td>
                    <td>销量</td>
                    <td>库存</td>
                    <td colspan="2">操作</td>
                </tr>
                <%-- 获取图书集合遍历展示 --%>
                <c:forEach items="${page.data}" var="booklist">

                    <tr>
                        <td>${booklist.title}</td>
                        <td>${booklist.price}</td>
                        <td>${booklist.author}</td>
                        <td>${booklist.sales}</td>
                        <td>${booklist.stock}</td>
                        <td><a href="manager/BookManagerServlet?method=getBook&bookId=${booklist.id}">修改</a></td>
                        <td><a class="delBook"
                               href="manager/BookManagerServlet?method=deleteBook&bookId=${booklist.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <%-- 获取图书集合遍历展示 --%>


        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_add.jsp">添加图书</a></td>
        </tr>
    </table>
    <br>
    <%--引入分页导航栏 --%>
    <%@include file="/WEB-INF/include/navigate.jsp"%>
    <br>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>