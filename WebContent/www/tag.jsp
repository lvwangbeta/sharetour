<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.service.TagService" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	
	String tag = request.getParameter("p1");
	tag = new String(tag.getBytes("ISO-8859-1"), "UTF-8");
	if(tag == null || tag.length() == 0){
		response.sendRedirect("/");
	}
	PostTag posttag = new PostTag();
	posttag.setTagname(tag);
	TagService tagservice = new TagService(posttag);
	List<Post> postlist = tagservice.getPostsRelatedToTag();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ShareTour</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="index">
    <meta name="author" content="gavin">

    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
  </head>
  <body>
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="/">享途</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="/">Home</a></li>
              <li><a href="/about">About</a></li>
            </ul>
            <c:choose>
            	<c:when test="${sessionScope.user == null }">
					<form class="navbar-form pull-right" method="post" action="/action/login">
		              <input class="span2" type="text" name="username" placeholder="Email">
		              <input class="span2" type="password" name="password" placeholder="Password">
		              <button type="submit" class="btn">Sign in</button>
		              <a class="btn" href="/register">注册</a>
		            </form>            		
            	</c:when>
            	<c:otherwise>
		            <ul class="nav pull-right">
		              <li class="dropdown active">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.username} <b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                  <li><a href="#">${sessionScope.user.email }</a></li>
		                  <li><a href="#">${sessionScope.user.birth }</a></li>
		                  <li><a href="#">${sessionScope.user.gender }</a></li>
                  		  <li><a href="#">info <span class="badge badge-important">6</span></a></li>		                  
		                  <li class="divider"></li>
		                  <li class="nav-header">Nav header</li>
		                  <li><a href="/action/logout">退出</a></li>
		                </ul>
		              </li>
		            </ul>           	
            	</c:otherwise>
            </c:choose>

          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div> <!-- end nav bar -->

    <div class="container">
      <div class="row">
        <div class="span8">
      		<%
      		if(postlist != null)
      			for(Post post:postlist){ 
      		%>
      		<div>
      			<h4><a href="/post/<%=post.getId() %>"><%=post.getTitle() %></a></h4>
      			<p><%=post.getSummary() %></p>
	          	<div>
	              <span class="badge badge-success">Posted <%=format.format(post.getCtime()) %></span>
	              <div class="pull-right">
	              <%for(String Tag:StringUtils.split(post.getTags(), " ")){ %>
	              	<span class="label label-warning"><%=Tag %></span> 
	              <%} %>
	              </div>
	          	</div> 
	          	<hr>
      		</div>
      		<%} %>         
        </div> <!-- end span8 posts -->
        <div class="span4">

        </div> <!-- end span4 -->
      </div>
      <hr>
      <footer>
        <p>&copy; Company 2013</p>
      </footer>

    </div> <!-- /container -->
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
  </body>
</html>

