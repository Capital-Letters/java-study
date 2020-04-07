<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人事管理系统</title>
</head>
<body>
	<!-- 
	config对象：<%= config.getServletName() %>
	<br>
	config获取初始化参数:<%= config.getInitParameter("age") %>
	-->
	<h3 align="center">人事管理系统</h3>
	<hr>
	<form action="control.jsp ">
		<table align="center">
			<tr>
				<td>
					账号：
				</td>
				<td>
					<input type="text" name="account"/>
				</td>
			</tr>
			
			<tr>
				<td>
					密码：
				</td>
				<td>
					<input type="password" name="password"/>
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="submit" value="登录">
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>