<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>尚硅谷会员登录页面</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<%--<%--%>
									<%--// 获取错误信息--%>
									<%--String errorMsg = (String) request.getAttribute("errorMsg");--%>
									<%--if (errorMsg == null) {--%>
										<%--// first open login.jsp page--%>
										<%--errorMsg="请输入用户名和密码";--%>
									<%--}--%>
								<%--%>--%>
								<span class="errorMsg">${empty requestScope.errorMsg?"请输入用户名和密码":requestScope.errorMsg}</span>
							</div>
							<div class="form">
								<form action="UserServlet">
									<%--  设置隐藏参数  method 值--%>
									<input type="hidden" name="method" value="login"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>