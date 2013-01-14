<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tour</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
<style type="text/css">
<style type="text/css">
  body {
    padding-top: 60px;
    padding-bottom: 40px;
  }
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<form action="register" method="post">
		<table>
			<h3>注册</h3>
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username" placeholder="Email"> </td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password" > </td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" name="conform"> </td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input type="text" name="email"> </td>
			</tr>
			<tr>
				<td><button class="btn" type="submit">注册</button> </td>
				<td></td>
			</tr>

		</table>

	</form>


</div>
</body>
</html>