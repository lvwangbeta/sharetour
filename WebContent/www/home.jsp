<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="com.sharetour.service.PostService" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ShareTour</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
<style type="text/css">
<style type="text/css">
  body {
    padding-top: 60px;
    padding-bottom: 40px;
  }
  .slide{
    border: 5px solid #fff;
    cursor: pointer;
    float: right;
    margin: 5px;
    position: relative;
    overflow: hidden;
      -webkit-box-shadow: 1px 1px 1px 1px #ccc;
      -moz-box-shadow: 1px 1px 1px 1px #ccc;
      box-shadow: 1px 1px 1px 1px #ccc;

  }
</style>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<%
	UserInfo user = (UserInfo)session.getAttribute("user");
	if(user == null)
	{
		response.sendRedirect("/index");
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
	              <form class="navbar-form pull-right" method="post" action="/login">
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
      <div class="row-fluid" id="content">
        <div class="span3 slide" id="slidebar">
          <br><br>        	
          <div class="box span9 offset1">
            <img src="/img/head.jpg" alt="headimg" class="img-circle" style="width:200px;height:200px">
          </div>
          <br><br>
          <div class="box span9 offset1">
            <div>
              <label for="">我的资料</label>
              
            </div>
            <div>
              <label for="">我的游记分类</label>
            </div>
          </div>   
        </div>
        <div class="span8" id="posts">
          <br>
          <div>
            <p><h3>我的游记</h3> </p>
          </div>
          <br>
          <div id="postslist">
            <table class="table">
<%
	List<Post> postslist = PostService.getPostList(0, 10);
    if(postslist != null)
    {
    	Iterator<Post> it = postslist.iterator();
    	while(it.hasNext())
    	{
    		Post post = it.next();
%>
	            <tr id="<%=post.getId() %>">
	              <td>
	                <div class="posttitle">
	                  <p><a href="/journal/<%=post.getId() %>"><%=post.getTitle() %></a></p> 
<%
					String[] tags = StringUtils.split(post.getTags(), " ");
					for(int i=0; i<tags.length; i++)
					{
%>
						<span class="label label-info"><%=tags[i] %></span>
<%						
					}
%>	                                
	                </div>
	              </td>
	              <td class="span2">
	                <div class="likes">
                  	  <span class="badge badge-important"><%=post.getLikes() %></span>
                  	  <span>likes</span>	
	                </div>
	              </td>
	            </tr>     
<%
    	}
    }            		  
%>      
            </table>

          </div>
        </div>

      </div>

</div> <!-- /container -->

</body>
</html>