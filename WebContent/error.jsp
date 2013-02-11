<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.sharetour.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ShareTour</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="navbar navbar-static-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/index">享途</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="/index">享途</a></li>
              <li><a href="/about">关于享途</a></li>
            </ul>
		    <c:choose>
			  <c:when test="${sessionScope.user != null }">
					<ul class="nav pull-right">
		              <li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.username}<b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                  <li><a href="/u/space">我的空间</a></li>
		                  <li><a href="/edit">写新游记</a></li>
		                  <li><a href="/u/space">我的游记</a></li>
		                  <li class="divider"></li>
		                  <li><a href="/logout">退出</a></li>
		                </ul>
		              </li>
		            </ul>	
              </c:when>
              <c:otherwise>
	              <form class="navbar-form pull-right" method="post" action="/login">
	                <input class="span2" type="text" name="username" placeholder="Email">
	                <input class="span2" type="password" name="password" placeholder="Password">
	                <button type="submit" class="btn">登录</button>
	                <a class="btn" href="/registerpage">注册</a>
	              </form>
              </c:otherwise>
		   	</c:choose>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
<div class="container well" align="center">
	<p>Error</p>
</div>
</body>
</html>
