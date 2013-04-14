<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="com.sharetour.service.PostService" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ShareTour</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
<style type="text/css">
  body {
    padding-bottom: 40px;
  }
  .box {
    border: 5px solid #fff;
    padding: 30px;
    float: left;
    margin: 5px;
    position: relative;
    overflow: hidden;
      -webkit-box-shadow: 1px 1px 1px 1px #ccc;
      -moz-box-shadow: 1px 1px 1px 1px #ccc;
      box-shadow: 1px 1px 1px 1px #ccc;
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
<script type="text/javascript" src="/js/journal.js"></script>
</head>
<body>
    <!--navbar  -->
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

        
<%
	//pid 是文章id 
	request.setCharacterEncoding("utf-8");
	String url = request.getRequestURI();
	int index = url.lastIndexOf("/");
	String pid = url.substring(index+1);
	PostService postservice = new PostService(new Post(Long.parseLong(pid)));
	Post post = postservice.getPost();
%>
<div class="container">
	<div class="row-fluid">
		<br><br>
		<div class="span8 box">  
			<div class="span12 post">    
<%
	if(post != null)
	{	
%>
	             <br><br>
	             <div class="title">
	                <p class="lead" align="center"><%=post.getTitle() %></p>
	             </div>
	             <div class="content">
	              	<%=post.getContent() %>
	             </div>
<%
	}
%>
			</div>
<%
	List<PostComment> clist = postservice.getPostComment();
	if(clist != null)
	{
		Iterator<PostComment> cit = clist.iterator();
%>
      <br><br>
      <legend>评论</legend>
	  <div class="span12" id="comment-list">
            <div class="comment-form" id="comment-form">
              <div class="head-img span1">
                 <img src="/img/head.jpg" class="img-rounded">
              </div>
              <div class="comment-form span11">
                <form>
                  <input name="username" id="username" type="hidden" value="${sessionScope.user.username }" />	
                  <input name="pid" id="pid" type="hidden" value="<%=pid %>" />
                  <textarea name="comment" id="comment" rows="7" class="span12"></textarea>
                  <button class="btn btn-primary" id="submit">提交</button>
                </form>                
              </div>          
            </div>
            
<%
			while(cit.hasNext())
			{
				PostComment comment = cit.next();
%>
			<div>
	            <div class="head-img span1">
	             	<img src="/img/head.jpg" class="img-rounded">
	            </div>

				<div class="span11">
					<p><%=comment.getName() %>评论：</p>
					<p><%=comment.getContent() %></p>
				</div>
			</div>
			<br>
<%				
			}
%>	
	</div>
<%		
	}
%>
		</div> 
		<!-- end span8 box -->
		<div class="span3">
			<br>
			<div class="well">
				<legend>同城评价</legend>
				<p>推荐这里，还不错，便宜又实惠</p>
				<p>不一定非要称缆车，下面有小路的~</p>
				<p>还是淡季去吧，旺季人太多了..</p>
				<p>虽然生活在这里，不过我也没去过哎</p>
			</div>
		</div>
	</div>
</div>

</body>
</html>