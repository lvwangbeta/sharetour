<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.sharetour.service.PostService" %>
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
        <div class="span3 offset1">
          <div class="left-side">
            <div class="avatar">
              <img src="../../img/head.jpg" class="img-rounded">
            </div>
            <div>
              <ul class="nav nav-list">
              <li class="active"><a href="#">首页</a></li>
              <li><a href="#">Library</a></li>
                    <li><a href="#">Profile</a></li>
                    <li><a href="#">Messages</a></li>
            </ul>
            </div>  
            <div>
              <ul class="nav nav-list">
              <li class="active"><a href="#">首页</a></li>
              <li><a href="#">Library</a></li>
                    <li><a href="#">Profile</a></li>
                    <li><a href="#">Messages</a></li>
            </ul>
            </div>  
            <div>
              <ul class="nav nav-list">
              <li class="active"><a href="#">首页</a></li>
              <li><a href="#">Library</a></li>
                    <li><a href="#">Profile</a></li>
                    <li><a href="#">Messages</a></li>
            </ul>
            </div>        
          </div>
          <!-- end left-side -->          
        </div>
        <%
        	List<Post> postlist = new PostService().getPostsOfAuthor(user.getId());
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        %>
      	<div class="span7">
      		<%for(Post post:postlist){ %>
      		<div>
      			<h4><a href="/post/<%=post.getId() %>"><%=post.getTitle() %></a></h4>
      			<p><%=post.getSummary() %></p>
	          	<div>
	              <span class="badge badge-success">Posted <%=format.format(post.getCtime().getTime()) %></span>
	              <div class="pull-right">
	              <%for(String tag:StringUtils.split(post.getTags(), " ")){ %>
	              	<a href="/tag/<%=tag%>"><span class="label label-warning"><%=tag %></span></a>
	              <%} %>
	              </div>
	          	</div> 
	          	<hr>
      		</div>
      		<%} %>
	        <div>
	          <h1>Alice in Wonderland, part dos</h1>
	          <p>'You ought to be ashamed of yourself for asking such a simple question,' added the Gryphon; and then they both sat silent and looked at poor Alice, who felt ready to sink into the earth. At last the Gryphon said to the Mock Turtle, 'Drive on, old fellow! Don't be all day about it!' and he went on in these words:
	          'Yes, we went to school in the sea, though you mayn't believe it—'
	          'I never said I didn't!' interrupted Alice.
	          'You did,' said the Mock Turtle.</p>
	          <div>
	              <span class="badge badge-success">Posted 2012-08-02 20:47:04</span><div class="pull-right"><span class="label">alice</span> <span class="label">story</span> <span class="label">blog</span> <span class="label">personal</span></div>
	          </div>
	          <hr> 
	        </div>
	        <div>
	          <h1>Revolution has begun!</h1>
	          <p>'I am bound to Tahiti for more men.'
	              'Very good. Let me board you a moment—I come in peace.' With that he leaped from the canoe, swam to the boat; and climbing the gunwale, stood face to face with the captain.
	              'Cross your arms, sir; throw back your head. Now, repeat after me. As soon as Steelkilt leaves me, I swear to beach this boat on yonder island, and remain there six days. If I do not, may lightning strike me!'A pretty scholar,' laughed the Lakeman. 'Adios, Senor!' and leaping into the sea, he swam back to his comrades.</p>
	          <div>
	              <span class="badge badge-success">Posted 2012-08-02 20:47:04</span><div class="pull-right"><span class="label">alice</span> <span class="label">story</span> <span class="label">blog</span> <span class="label">personal</span></div>
	          </div>     
	          <hr>
	        </div>
      	</div>
      	<!-- end span8 post list -->
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
  	
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
  </body>
</body>