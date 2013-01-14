<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="com.sharetour.db.ConnectionPool" %>
<%@ page import="com.sharetour.service.PostListDAO" %>
<%@ page import="com.sharetour.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tour</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="css/elastislide.css">
<style type="text/css">
<style type="text/css">
  body {
    padding-top: 60px;
    padding-bottom: 40px;
  }
  .box {
    border: 5px solid #fff;
    cursor: pointer;
    height: 220px;
    float: left;
    margin: 5px;
    position: relative;
    overflow: hidden;
      -webkit-box-shadow: 1px 1px 1px 1px #ccc;
      -moz-box-shadow: 1px 1px 1px 1px #ccc;
      box-shadow: 1px 1px 1px 1px #ccc;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="js/modernizr.custom.17475.js"></script>
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/tour.js"></script>

</head>
<body>

<%
System.out.println(java.lang.System.getenv("VCAP_SERVICES"));
%>
    <div class="navbar navbar-inverse">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
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
			<c:when test="${sessionScope.user != null }">
			<p class="navbar-text pull-right">Hi~ ${sessionScope.user.username}</p>	
            </c:when>
            <c:otherwise>
            <form class="navbar-form pull-right" method="post" action="login">
              <input class="span2" type="text" name="username" placeholder="Email">
              <input class="span2" type="password" name="password" placeholder="Password">
              <button type="submit" class="btn">登录</button>
              <a class="btn" href="register.jsp">注册</a>
            </form>
            </c:otherwise>
		</c:choose>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
      <!-- head gallery -->
      <ul id="carousel" class="elastislide-list">
<%
		List<Article> hotlist = new PostListDAO().getHot();
		if(hotlist != null)
		{
			
			Iterator<Article> it = hotlist.iterator();
			for(int i = 0; i < 6 && it.hasNext(); i++)
			{
%>
				<li><a href="#"><%=it.next().getCover()%></a></li>
<%
			}
		}
%>        
      </ul>
      
      <div class="row-fluid">
      <!-- other hot post -->
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <h1>Hello, world!</h1>
        <p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
        <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
      </div>

      <!-- Example row of columns -->
      <div class="row-fluid">
      	<div class="span8">
	        <div>
	          <h2>Heading</h2>
	          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	          <p><a class="btn" href="#">View details &raquo;</a></p>
	        </div>
	        <div>
	          <h2>Heading</h2>
	          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	          <p><a class="btn" href="#">View details &raquo;</a></p>
	        </div>
	        <div>
	          <h2>Heading</h2>
	          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
	          <p><a class="btn" href="#">View details &raquo;</a></p>
	        </div>     		
      	</div>
      	<!-- tag list -->
      	<div class="span4">
      		<div>
      			<button class="btn btn-large btn-primary offset2" type="button">写游记</button>
      		</div>
      		<div>
      	      <h2>Heading</h2>
	          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	          <p><a class="btn" href="#">View details &raquo;</a></p>

      		</div>

      	</div>

      </div>
	  <script type="text/javascript" src="js/jquery.js"></script>
	  <script type="text/javascript" src="js/jquerypp.custom.js"></script>
	  <script type="text/javascript" src="js/jquery.elastislide.js"></script>
	  <script type="text/javascript">
	      
	    $( '#carousel' ).elastislide();
	      
	  </script>
      <hr>

      <footer>
        <p>&copy; Tour 2013</p>
      </footer>

    </div> <!-- /container -->
</body>
</html>