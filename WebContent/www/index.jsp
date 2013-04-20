<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.service.HotPostService" %>
<%@ page import="com.sharetour.service.TagService" %>
<%@ page import="com.sharetour.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
          <div class="posts">
            <div class="post">
              <h2><a href="/post/2">Heading</a></h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div>
            <!-- <hr class="divider"> -->
            <div class="post">
              <h2><a href="#">Heading</a></h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div>
            <div class="post">
              <h2><a href="#">Heading</a></h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div>  
            <%
            List<Post> postlist = new HotPostService().getHotPost();
            Iterator<Post> it = postlist.iterator();
            while(it.hasNext()){
            %>
            <div class="post">
			   <%
			    Post post = it.next();		    
    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		   %>
              <h3><strong><a href="/post/<%=post.getId()%>"><%=post.getTitle() %></a></strong></h3>
              <p>
    		   <%=post.getSummary() %>                        
              </p>
              <p><a class="btn" href="#">Read more</a></p>
              <p>
                <i class="icon-user"></i> by <a href="#">John</a> 
                | <i class="icon-calendar"></i> <%=format.format(post.getCtime()) %>
                | <i class="icon-comment"></i> <a href="#">3 Comments</a>
                | <i class="icon-share"></i> <a href="#">39 Shares</a>
                | <i class="icon-tags"></i> Tags : <a href="#"><span class="label label-info">Snipp</span></a> 
                <a href="#"><span class="label label-info">Bootstrap</span></a> 
                <a href="#"><span class="label label-info">UI</span></a> 
                <a href="#"><span class="label label-info">growth</span></a>
              </p>                 
            </div>     <!-- end post -->            
            <%
            }
            %>    

          </div> <!-- end posts -->
         
        </div> <!-- end span8 posts -->
        <div class="span4">
          <div class="side">
            <div>
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div> 
            <% 
            List<PostTag> hottags = TagService.getHotTag();
            if(hottags != null)
            {
            %>  
            <div>
            	<% for(PostTag tag: hottags) {%>
            		<span class="label label-info"><%=tag.getTagname() %></span>  	
            	<%} %>
            </div>
            <%} %>                 
          </div> <!-- end side -->
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

