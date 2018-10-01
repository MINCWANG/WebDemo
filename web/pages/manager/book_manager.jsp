<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@include file="/pages/include/base.jsp" %>
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

    <%@include file="/pages/include/manager_navigate.jsp" %>
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
    <div id="page_nav">
        <a href="manager/BookManagerServlet?method=findPage&pageNumber=1">首页</a>
        <a href="manager/BookManagerServlet?method=findPage&pageNumber=${page.pageNumber-1}">上一页</a>
        <%-- 控制分页页码显示，一页只显示5个页码
                begin和end只能使用变量，动态计算起始和结束索引
                1.判断总页码
                    page.totalPage<=5 begin：1  end：totalPage
                                                                begin       end
                                          总页码《=5                   1            totalPage
                                        总页码》5
                                          pageNumber《3               1            5
                                          pageNumber》=3             pageNumber-2 pageNumber+2
                                            end》=totalPage          totalPage-4  totalPage
                                            end《totalPage
        --%>
        <c:choose>
            <c:when test="${page.totalPage<=5}">
                <c:set var="begin" value="1"></c:set>
                <c:set var="end" value="${page.totalPage}"></c:set>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${page.pageNumber<=3}">
                        <c:set var="begin" value="1"></c:set>
                        <c:set var="end" value="5"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${page.pageNumber-2}"></c:set>
                        <c:set var="end" value="${page.pageNumber+2}"></c:set>
                        <c:if test="${end >= page.totalPage}">
                            <c:set var="begin" value="${page.totalPage-4}"></c:set>
                            <c:set var="end" value="${page.totalPage}"></c:set>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        <%-- 遍历导航索引栏 --%>
        <c:forEach begin="${begin}" end="${end}" var="index">
            <c:choose>
                <c:when test="${index==page.pageNumber}">
                    <span style="color: red">【${index}】</span>
                </c:when>
                <c:otherwise>

                    <a href="manager/BookManagerServlet?method=findPage&pageNumber=${index}">${index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <a href="manager/BookManagerServlet?method=findPage&pageNumber=${page.pageNumber+1}">下一页</a>
        <a href="manager/BookManagerServlet?method=findPage&pageNumber=${page.totalPage}">末页</a>
        共${page.totalPage }页，${page.totalCount }条记录 到第<input value="${page.pageNumber }" name="pn" id="pn_input"/>页
        <input type="button" value="确定">
    </div>
    <br>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>