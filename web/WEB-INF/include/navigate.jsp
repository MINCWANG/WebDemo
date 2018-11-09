<%--
  Created by IntelliJ IDEA.
  User: Wang MC
  Date: 2018/10/6
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <a href="${page.path}&pageNumber=1">首页</a>
    <a href="${page.path}&pageNumber=${page.pageNumber-1}">上一页</a>
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

                <a href="${page.path}&pageNumber=${index}">${index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <a href="${page.path}&pageNumber=${page.pageNumber+1}">下一页</a>
    <a href="${page.path}&pageNumber=${page.totalPage}">末页</a>
    共${page.totalPage }页，${page.totalCount }条记录 到第<input value="${page.pageNumber }" name="pn" id="pn_input"/>页
    <input id="send_btn" type="button" value="确定">
    <script>
        $('#send_btn').click(function () {
            // 点击确定按钮，跳转到pn_input表单项中用户输入的页面
            // 点击时获取页码
            var $number = $('#pn_input').val();
            // 跳转到manager/BookManagerServlet?method=findPage&pageNumber=$number
            // 要使用绝对路径，浏览器端解析需要添加ContextName
            // 判断用户在输入框是不是一个数字
            if (isNaN($number)){
                // jsdom.defaultValue() 代表input输入框的之前一个值
                // 当输入不是数字是，给出tip 并且 重置输入框的值
                alert('请输入正确的数字');
                $('#pn_input').val($('#pn_input')[0].defaultValue);
            }else {
                // 使用EL表达式获取项目名。在js中写在引号中
                window.location = '${page.path}&pageNumber='+$number;
            }
        })
    </script>

</div>