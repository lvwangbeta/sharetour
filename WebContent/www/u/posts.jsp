<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.sharetour.service.PostService" %>
<%@ page import="com.sharetour.service.SubscriptionService" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
	UserInfo user = (UserInfo)session.getAttribute("user");
	if(user == null)
	{
		response.sendRedirect("/");
		return;
	}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ShareTour</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="index">
    <meta name="author" content="gavin">

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
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div> <!-- end nav bar -->  
    <!--  begin container  -->
    <div class="container">
      <div class="row">
        <%
        	PostService service = new PostService(); 
        	List<Post> postlist = service.getPostsOfAuthor(user.getId());
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        %>
      	<div class="span8">
      	 <div class="media">
      	 	<div class="row">
      	 		<div class="span1">
      	 			<img class="media-object" src="../../img/head.jpg" style="height:64px;width=64px;">
      	 		</div>
      	 		<div class="span7">
	                <div class="media-body">
	                    <div class="thumbnail">

	                    </div>              
	                </div>      	 			
      	 		</div>
      	 	</div>
      	 
      	 </div>
      	 <!-- end media  -->      	
      		<%
      		if(postlist != null && postlist.size() != 0)
      			for(Post post:postlist){ 
      		%>
      		<div class="media">
	            <div class="row">
	              <div class="span1">
	                <a class="pull-left" href="/u/">
	                  <img class="media-object" src="../../img/head.jpg" style="height:64px;width=64px;">
	                </a>                 
	              </div>
	              <!-- end span1 -->
	              <div class="span7">
	                <div class="media-body">
	                    <div class="thumbnail">
	                      <div>
	                        <h4><a href="/post/<%=post.getId() %>"><%=post.getTitle() %></a></h4>
	                      </div>
	                      <%if(post.getCover()!=null) {%>
	                      <img src="<%=post.getCover() %>">
	                      <%}%>
	                      <div class="caption">
	                        <p><%=post.getSummary() %></p>
	                        <%for(String tag:StringUtils.split(post.getTags(), " ")){ %>
		              			<a href="/tag/<%=tag%>"><span class="label label-warning"><%=tag %></span></a>
		              		<%} %>
	                      </div>
	                    </div>              
	                </div>                 
	              </div>
	              <!-- end span7  -->
	            </div>
				<!-- end row -->
      		</div>
      		<!-- end media -->
      		<%} %>
      	</div>
      	<!-- end span8  -->
      	
        <div class="span4">
          <div class="accordion">
          
          
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" href="#">
                  <i class="icon-user"></i>&nbsp;&nbsp;我的游记
                </a>
              </div>
            </div>          
          
          
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" href="#subTags">
                  <i class="icon-tags"></i>&nbsp;&nbsp;	我订阅的标签
                </a>
              </div>
              <div id="subTags" class="accordion-body collapse in">
                <div class="accordion-inner">
                  <%
                  	List<PostTag> tlist = new SubscriptionService().getAllTagsOfUser(user.getId());
                  	if(tlist != null && tlist.size() != 0){
                  %>
                  	<ul class="unstyled">
                  		<%for(PostTag tag:tlist) {%>
                  		<li>
                  			<i class="icon-tag"></i>&nbsp;&nbsp;
                  			<a href="/tag/<%=tag.getTagname() %>"><%=tag.getTagname() %></a> 
                  		</li>
                  		<%} %>
                  	</ul>
                  <%}%>
                </div>
              </div>
            </div>
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" href="#following">
                  <i class="icon-user"></i>&nbsp;&nbsp;我的关注
                </a>
              </div>
              <div id="following" class="accordion-body collapse">
                <div class="accordion-inner">
                  
                </div>
              </div>
            </div>
        </div>
        <!-- end side bar -->
        </div>
        <!-- end span4 -->      	
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
  	
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
  </body>
</body>