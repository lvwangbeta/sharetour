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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>java open blog</title>
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
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Project name</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
            <ul class="nav pull-right">
              <li class="dropdown active">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.username} <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">${sessionScope.user.email }</a></li>
                  <li><a href="#">${sessionScope.user.birth }</a></li>
                  <li><a href="#">${sessionScope.user.gender }</a></li>
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
        	List<Post> postlist = PostService.getPostsOfAuthor((int)user.getId());
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        %>
      	<div class="span7">
      		<%for(Post post:postlist){ %>
      		<div>
      			<h1><a href="/post/<%=post.getId() %>"><%=post.getTitle() %></a></h1>
      			<p><%=post.getSummary() %></p>
	          	<div>
	              <span class="badge badge-success">Posted <%=format.format(post.getCtime()) %></span>
	              <div class="pull-right">
	              <%for(String tag:StringUtils.split(post.getTags(), " ")){ %>
	              	<span class="label"><a href="/tag/<%=tag%>"><%=tag %></a></span> 
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