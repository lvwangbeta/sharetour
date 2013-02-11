<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.sharetour.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tour</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
<style type="text/css">
<style type="text/css">
  body {
    padding-top: 60px;
    padding-bottom: 40px;
  }
</style>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/register.js"></script>
</head>
<body>
<%
	User user = (User)session.getAttribute("user");
	if(user != null)
	{
		response.sendRedirect("/index");
		return;
	}
%>
<div class="container" align="center">
	<form action="register" method="post">
		<table>
			<h3>注册</h3>
<%
		request.setCharacterEncoding("utf-8");
		String tips = (String)request.getAttribute("tips");
		if(tips != null)
		{
%>
			<tr>
				<td>
					<div class="alert alert-error">
  						<button type="button" class="close" data-dismiss="alert">×</button>
  						<strong>Error!</strong><%=tips %>
  						
					</div>
				</td>
			</tr>
<%
		}
%>
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username" id="username"> </td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password" id="password"> </td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" name="conform" id="conform"> </td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input type="text" name="email" id="email"> </td>
			</tr>
			<tr>
				<td></td>
				<td><button class="btn btn-primary" type="submit" id="submit">注册</button> </td>
			</tr>

		</table>

	</form>


</div>
</body>
</html>