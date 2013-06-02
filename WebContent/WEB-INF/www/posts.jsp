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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css">
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>
<body>
	
    <%@ include file="topbar.jsp" %>
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
	List<Post> postlist = new TagService().getPostsRelatedToTag(tag);
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
	        			<a href="<%=request.getContextPath()%>/journal/<%=post.getId() %>"><%=post.getTitle() %></a>
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