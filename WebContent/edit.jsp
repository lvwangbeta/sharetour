<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.sharetour.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tour</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
<style type="text/css">
<style type="text/css">
  body {
    padding-top: 60px;
    padding-bottom: 40px;
  }
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/tour.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

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

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span4">
          <p>hello slide left</p>      
        </div>
        <form action="" method="post">
          <div>
            <table>
              <tr>
                <td><legend>写新游记</legend></td>
              </tr>
              <tr>
                <td>
                  <label for="">标题(必填)</label>
                  <input class="input-xxlarge" type="text" name="title">
                </td>
              </tr>
              <tr>
                <td>
                  <label for="">内容(必填)</label>
                  <textarea class="ckeditor" name="editor" id="editor">hello</textarea>
                  <script type="text/javascript">  
                      CKEDITOR.replace('editor',{filebrowserImageUploadUrl : 'imgupload',width:800,height:400 });  
                  </script> 
                </td>
              </tr>
              <tr>
                <td>
                  <br>
                  <label for="">标签(多个标签用空格隔开)</label>
                  <input class="input-xlarge" type="text" name="tags">
                </td>
              </tr>
              <tr>
                <td><button class="btn" type="submit">发表</button> </td>
              </tr>
            </table>    
          </div>          
        </form>


      </div>


      <footer>
        <p>&copy; Tour 2013</p>
      </footer>

    </div> <!-- /container -->
</body>
</html>