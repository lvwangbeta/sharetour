<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.sharetour.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tour</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
<style type="text/css">
<style type="text/css">
  body {
    padding-top: 60px;
    padding-bottom: 40px;
  }
</style>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/edit.js"></script>
<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>

</head>
<body>
<%
	UserInfo user = (UserInfo)session.getAttribute("user");
	if(user == null)
	{
		response.sendRedirect("/");
		return;
	}
%>
    <div class="navbar navbar-static-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/index">享途</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="/index">享途</a></li>
              <li><a href="/about">关于享途</a></li>
            </ul>
		    <c:choose>
			  <c:when test="${sessionScope.user != null }">
					<ul class="nav pull-right">
		              <li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.username}<b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                  <li><a href="/u/space">我的空间</a></li>
		                  <li><a href="/edit">写新游记</a></li>
		                  <li><a href="/u/space">我的游记</a></li>
		                  <li class="divider"></li>
		                  <li><a href="/logout">退出</a></li>
		                </ul>
		              </li>
		            </ul>	
              </c:when>
              <c:otherwise>
	              <form class="navbar-form pull-right" method="post" action="login">
	                <input class="span2" type="text" name="username" placeholder="Email">
	                <input class="span2" type="password" name="password" placeholder="Password">
	                <button type="submit" class="btn">登录</button>
	                <a class="btn" href="/registerpage">注册</a>
	              </form>
              </c:otherwise>
		   	</c:choose>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span4">
          <br><br>
          <div class="box span9 offset1">
            <img src="/img/head.jpg" alt="headimg" class="img-circle" style="width:200px;height:200px">
          </div>
          <div class="box span9 offset1">
            <div>
              <label for="">我的资料</label>
            </div>
            <div>
              <label for="">我的游记</label>
            </div>
          </div>   
        </div>
        <div class="span8">
          <form action="newpost" method="post">
            <div>
              <table>
                <tr>
                  <td><legend>写新游记</legend></td>
                </tr>
                <tr>
                  <td>
                    <label for="">标题(必填)</label>
                    <input class="input-xxlarge" id="title" type="text" name="title">
                  </td>
                </tr>
                <tr>
                  <td>
                    <label for="">内容(必填)</label>
                    <textarea class="ckeditor" name="content" id="content"></textarea>
                    <script type="text/javascript">  
                        CKEDITOR.replace('content',{filebrowserImageUploadUrl : 'imgupload',width:800,height:400 });  
                    </script> 
                  </td>
                </tr>
                <tr>
                  <td>
                    <br>
                    <label for="">标签(多个标签用空格隔开)</label>
                    <input class="input-xlarge" id="tags" type="text" name="tags">
                  </td>
                </tr>
                <tr>
                  <td><button class="btn" id="submit" type="submit">发表</button> </td>
                </tr>
              </table>    
            </div>          
          </form>               
        </div>
      </div>
    </div> <!-- /container -->
</body>
</html>