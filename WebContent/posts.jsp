<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.service.TagService" %>
<%@ page import="com.sharetour.model.PostTag" %>
<%@ page import="com.sharetour.model.Post" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ShareTour</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
<style type="text/css">
  body {
    padding-bottom: 40px;
  }
    .box {
    border: 5px solid #fff;
    padding: 30px;
    float: left;
    margin: 5px;
    position: relative;
    overflow: hidden;
      -webkit-box-shadow: 1px 1px 1px 1px #ccc;
      -moz-box-shadow: 1px 1px 1px 1px #ccc;
      box-shadow: 1px 1px 1px 1px #ccc;
  }
  .slide{
    border: 5px solid #fff;
    cursor: pointer;
    float: right;
    margin: 5px;
    position: relative;
    overflow: hidden;
      -webkit-box-shadow: 1px 1px 1px 1px #ccc;
      -moz-box-shadow: 1px 1px 1px 1px #ccc;
      box-shadow: 1px 1px 1px 1px #ccc;

  }
</style>
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
    
    <div class="container">
    	<div class="row-fluid">
          <div class="span8">
          <br><br>
			<table class="table">
			
<%
	request.setCharacterEncoding("utf-8");
	String url = request.getRequestURI();
	int index = url.lastIndexOf("/");
	String tag = java.net.URLDecoder.decode(url.substring(index+1), "utf-8");
%>
	<p class="lead">最新游记<button class="btn btn-warning"><%=tag %></button></p>
<%
	PostTag posttag = new PostTag();
	posttag.setTagname(tag);
	List<Post> postlist = new TagService(posttag).getPostsRelatedToTag();
	if(postlist != null)
	{
		Post post = null;
		Iterator<Post> it = postlist.iterator();
		while(it.hasNext())
		{
			post = it.next();
		
%>

	        	<tr>
	        		<td class="sapn8">
	        			<a href="/journal/<%=post.getId() %>"><%=post.getTitle() %></a>
	        		</td>
	        		<td class="span4">
	        			<span><%=post.getLikes() %>&nbsp;</span>
	        			<span><button class="btn btn-danger">likes</button></span> 
	        		</td>
	        	</tr>

<%
		}
	}
	
%>
			</table>
		  </div>
		</div>
	</div>
</body>
</html>