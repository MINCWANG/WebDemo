<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <!--  通过include 引入 base.jsp -->
    <%@include file="/WEB-INF/include/base.jsp" %>
    <script>
        $(function () {
            $('#queryBtn').click(function () {
                // 获取输入框的值
                var $min = $('[name=min]').val();
                var $max = $('[name=max]').val();
                if (isNaN($min) || isNaN($max) || ($max - 0) < 0 || ($min - 0) < 0) {
                    // 回显之前的输入框里的值
                    alert("价格输入不正确");
                    $('[name=min]').val($('[name=min]')[0].defaultValue);
                    $('[name=max]').val($('[name=max]')[0].defaultValue);
                    return false;
                } else if (($max - $min) < 0) {
                    alert("请输入正确的价格区间");
                    return false;
                }
            });

            $('.addA').click(function () {
            //    通过jQuery发送AJAX请求
                var url = "${pageContext.request.contextPath}/CartServlet";
                var data = {"method":"addBook2Cart","bookId":this.id};
                var callback = function (result) {
                    $('#cartSpan').text("您的购物车中有"+result.count+"件商品");
                    $('#titleDiv').html("您刚刚将<span style=\"color: red\" id=\"titleSpan\">"+result.title+"</span>加入到了购物车中");
                };
                $.getJSON(url,data,callback);

                // 取消a的默认事件
                return false;
            });

        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <!--  引入 用户界面导航栏 -->
    <%@include file="/WEB-INF/include/user_header.jsp" %>
</div>

<div id="main">
    <div id="book">
        <c:choose>
            <c:when test="${ empty cart.cartItemList}">
                <div style="text-align: center">
                    <span id="cartSpan">空的一批，毛都没有！</span>
                    <div id="titleDiv"></div>
                </div>
            </c:when>
            <c:otherwise>
                <div style="text-align: center">
                    <span id="cartSpan">您的购物车中有 ${cart.totalCount}</span> 件商品</span>
                    <div id="titleDiv">
                        您刚刚将<span style="color: red" id="titleSpan">${title}</span>加入到了购物车中
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="book_cond">
            <form action="BookClientServlet">
                <input type="hidden" name="method" value="findPageByPrice">
                价格：<input type="text" name="min" value="${param.min}"> 元 - <input type="text" name="max" value="${param.max}"> 元
                <input type="submit" id="queryBtn" value="查询">
            </form>
        </div>

        <c:choose>
            <c:when test="${empty page.data}">
                <h2>已经抢完了，下次再来</h2>
                <%-- 图书集合为空 --%>
            </c:when>
            <c:otherwise>
                <%-- 遍历展示分页图书 --%>
                <c:forEach items="${page.data}" var="book">
                    <div class="b_list">
                        <div class="img_div">
                            <img class="book_img" alt="" src="${pageContext.request.contextPath}${book.imgPath}"/>
                        </div>
                        <div class="book_info">
                            <div class="book_name">
                                <span class="sp1">书名:</span>
                                <span class="sp2">${book.title}</span>
                            </div>
                            <div class="book_author">
                                <span class="sp1">作者:</span>
                                <span class="sp2">${book.author}</span>
                            </div>
                            <div class="book_price">
                                <span class="sp1">价格:</span>
                                <span class="sp2">￥${book.price}</span>
                            </div>
                            <div class="book_sales">
                                <span class="sp1">销量:</span>
                                <span class="sp2">${book.sales}</span>
                            </div>
                            <div class="book_amount">
                                <span class="sp1">库存:</span>
                                <span class="sp2">${book.stock}</span>
                            </div>
                            <div class="book_add">
                                <a  class="addA" id="${book.id}" href="CartServlet?method=addBook2Cart&bookId=${book.id}">加入购物车</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>

    </div>

    <%@ include file="/WEB-INF/include/navigate.jsp" %>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>