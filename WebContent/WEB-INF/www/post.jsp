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
		response.sendRedirect(request.getContextPath()+"/");
	PostService postservice = new PostService();
	Post post = postservice.getPostById(Long.parseLong(id));
	if(post == null)
		response.sendRedirect(request.getContextPath()+"/"); 
	String postid = id;
	List<PostComment> comments = postservice.getCommentsByPostId(Long.parseLong(id));
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
      <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
      <style type="text/css">
        body {
          padding-top: 60px;
          padding-bottom: 40px;
        }
      </style>
      <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" rel="stylesheet">
      <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    </head>
	<body>
	<%@ include file="topbar.jsp" %>
   
    <div class="container">
    	<div class="row">
    		<!-- main wrap -->
    		<div class="span8">
    			<div class="singlepost">
					<div class="title"><%=post.getTitle()%></div>
					<div class="content"><%=post.getContent() %></div>
    			</div>
    			<div class="comments-area" id="comments"> 
					<div id="commentdiv">
						<h3>Comment</h3>
						<form action="<%=request.getContextPath()%>/action/comment" id="commentform" method="post">
							<input type="hidden" name="postid" id="postid" value="<%=post.getId() %>" />
							<input type="hidden" name="username" id="username" value="${sessionScope.user.username }" />
							<p>
								<textarea name="content" id="content" cols="30" rows="8"></textarea>
							</p>
							<div class="alert alert-error warningblock" id="tip">
							</div>								
							<p>
								<button type="submit" class="btn btn-primary" id="submit">评论</button>
							</p>									
						</form>
					</div> 	
					<ol class="comments-list" id="comments-list">
					<%if(comments != null && comments.size()!=0) {%>
						<c:forEach var="comment" items="<%=comments %>">
							<li>
								<div>
									<p>
										<i class="icon-user"></i> <a href="<%=request.getContextPath()%>#">${comment.name}</a> 
										&nbsp;<i class="icon-calendar"></i> ${comment.ctime }
									</p>
									<p>
										${comment.content }
									</p>
								</div>
							</li>												
						</c:forEach>
					<%} %>
					</ol>  			
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
    					<a href="<%=request.getContextPath()%>/tag/<%=tag%>"><span class="label label-warning"><%=tag %></span></a>
    				<%} %>
    				</p>
    				<%} %>    				
    				</div>				
    			</div>
    		</div> <!-- end span4 -->
    	</div> <!-- end row -->
    </div> <!-- end container -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/comment.js"></script>
	</body>
</html>