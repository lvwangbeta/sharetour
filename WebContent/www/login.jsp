<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("user") != null)
		response.sendRedirect("/");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>java open blog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="register">
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
            	<li><a href="/register">注册</a></li>
            </ul>
                  		
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div> <!-- end navbar -->
	<div class="container">
		<div class="row social_buttons">
			<div class="span3">
				<button class="btn btn-large btn-block btn-primary" type="button">sina</button>
			</div>
			<div class="span3">
				<button class="btn btn-large btn-block" type="button">renren</button>
			</div>
		</div>
		<form class="form-signin">
		  <h2 class="form-signin-heading">Please sign in</h2>
		  <input type="text" class="input-block-level" placeholder="Email address">
		  <input type="password" class="input-block-level" placeholder="Password">
		  <label class="checkbox">
		    <input type="checkbox" value="remember-me"> Remember me
		  </label>
		  <button class="btn btn-large btn-primary" type="submit">Sign in</button>
		</form>			
	</div> <!-- end container -->
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>		
  </body>
</html>