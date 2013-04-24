<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("user") == null)
		response.sendRedirect("");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>java open blog</title>
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
    <div class="container">
	      <div class="row">
	        <div class="span3">
	          <div class="side">
	            hello side
	
	          </div>
	        </div> <!-- end span3 -->
	        <div class="span9">
            <form action="/action/newpost" method="post">
  	          <table>
  	            <tr>
  	              <td><legend>写新文章</legend></td>
  	            </tr>
  	            <tr>
  	              <td>
  	                <label for="title">标题(必填)</label>
  	                <input class="span6" id="title" type="text" name="title">
  	              </td>
  	            </tr>
  	
  	            <tr>
  	              <td>
  	                <label for="content">内容(必填)</label>
  	                <textarea class="ckeditor span8" name="content" id="content"></textarea>
  	                <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
  	                <script type="text/javascript">  
  	                    CKEDITOR.replace('content',{ width:700,height:400 });  
  	                </script> 
  	              </td>
  	            </tr>
  	            <tr>
  	              <td>
  	                <br>
  	                <label for="tags">标签(多个标签用空格隔开)</label>
  	                <input class="span6" id="tags" type="text" name="tags">
  	              </td>
  	            </tr>
  	            <tr>
  	              <td><button class="btn" id="submit" type="submit">发表</button> </td>
  	            </tr>
  	          </table> 
            </form>
	        </div> <!-- end span9 -->
	      </div> <!-- end row -->
    </div> <!-- end container -->
	<script type="text/javascript" src="/js/jquery.js"></script> 
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
  </body>
</html>