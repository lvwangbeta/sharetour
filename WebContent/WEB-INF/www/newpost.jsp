<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%
	if(session.getAttribute("user") == null)
		response.sendRedirect(request.getContextPath()+"/");
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
	        <div class="span9">
            <form action="<%=request.getContextPath()%>/action/newpost" method="post">
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
  	                <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
  	                <script type="text/javascript">  
  	                    CKEDITOR.replace('content',
  	                    		{ width:700,height:400,
  	                    		  filebrowserImageUploadUrl : '<%=request.getContextPath()%>/action/imgupload?attr=post',width:800,height:400 } );  
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
  	              <td><button class="btn btn-success" id="submit" type="submit">发表</button> </td>
  	            </tr>
  	          </table> 
            </form>
	        </div> <!-- end span9 -->
	      </div> <!-- end row -->
    </div> <!-- end container -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  </body>
</html>