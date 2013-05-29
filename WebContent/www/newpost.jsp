<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
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
		                  <li><a href="/u/space">我的空间</a></li>
		                  <li><a href="/newpost">写新游记</a></li>
                  		  <li><a href="#">消息 <span class="badge badge-important">6</span></a></li>		                  
		                  <li class="divider"></li>
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
  	                    CKEDITOR.replace('content',
  	                    		{ width:700,height:400,
  	                    		  filebrowserImageUploadUrl : '/action/imgupload?attr=post',width:800,height:400 } );  
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