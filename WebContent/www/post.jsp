<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.sharetour.service.PostService" %>
<%@ page import="com.sharetour.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("p1");
	if(id == null || id.length() == 0)
		response.sendRedirect("/");
	PostService postservice = new PostService(new Post(Long.parseLong(id)));
	Post post = postservice.getPost();
	if(post == null)
		response.sendRedirect("/"); 
	String postid = id;
	List<PostComment> comments = postservice.getPostComment();
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
    		<!-- main wrap -->
    		<div class="span8">
    			<div class="singlepost">
					<div class="title"><%=post.getTitle()%></div>
					<div class="content"><%=post.getContent() %></div>
    			</div>
    			<div class="comments-area" id="comments">
    				<h3>5 commets to this article</h3>
					<ol class="comments-list">
					<%if(comments != null) {%>
						<c:forEach var="comment" items="<%=comments %>">
							<li>
								<div>
									<p>
										<span><a href="#">${comment.name}</a></span>
										<span>${comment.ctime }</span>
									</p>
									<p>
										${comment.content }
									</p>
								</div>
							</li>												
						</c:forEach>
					<%} %>
					</ol>   
					<div id="commentdiv">
						<h3>Comment</h3>
						<form action="/action/comment" id="commentform">
							<input type="hidden" name="postid" value="<%=post.getId() %>" />
							<p>
								<input type="text" name="author" id="author" class="inline" />
								<label for="author" class="inline">author *</label>
							</p>
							<p>
								<input type="text" name="email" id="email" />
								<label for="email">email *</label>
							</p>
							<p>
								<textarea name="content" id="content" cols="30" rows="10"></textarea>
							</p>
							<p>
								<input type="submit" class="btn btn-primary" />
							</p>									
						</form>
					</div> 				
  			</div>
    		</div> <!-- end span8 -->
    		<!-- side bar -->
    		<div class="span4">
    			<div>
    				<div class="box">
    				<% String[] tags = StringUtils.split(post.getTags(), " "); %>
  					<% if(tags != null && tags.length != 0) {%>
    				<p>Tags:
    				<%for(String tag: tags) {%>
    					<a href="/tag/<%=tag%>"><span class="label label-warning"><%=tag %></span></a>
    				<%} %>
    				</p>
    				<%} %>    				
    				</div>				
    			</div>
    		</div> <!-- end span4 -->
    	</div> <!-- end row -->
    </div> <!-- end container -->
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
	</body>
</html>