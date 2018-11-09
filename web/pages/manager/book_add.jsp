<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加图书</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<script>
		$(function () {
            $("#submit").click(function () {
                var $title =$(this).parent().prevAll(':last').children(':first').val();
                // var $title = $('[name=title]').val();
                alert($title);
                if (!confirm("你确定要添加《"+$title+"》吗？")){
                    return false;
                }
            });
        })
	</script>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">添加图书</span>

			<%@include file="/WEB-INF/include/manager_navigate.jsp"%>
		</div>
		
		<div id="main">
			<%-- from表单中携带参数，使用表单项
			 		如果get请求提交参数，anction的url?后面拼接的对象会被覆盖
			 --%>
			<form action="manager/BookManagerServlet" method="post" onsubmit="return ">
				<input type="hidden" name="method" value="addBook" >
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value="时间简史"/></td>
						<td><input name="price" type="text" value="30.00"/></td>
						<td><input name="author" type="text" value="霍金"/></td>
						<td><input name="sales" type="text" value="200"/></td>
						<td><input name="stock" type="text" value="300"/></td>
						<td><input id="submit" type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>