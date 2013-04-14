<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="com.sharetour.db.ConnectionPool" %>
<%@ page import="com.sharetour.service.HotPostService" %>
<%@ page import="com.sharetour.service.TagService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ShareTour</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="/css/elastislide.css">
<style type="text/css">

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
  .box-shadow{
    background: url(img/bg.png);
    box-shadow: 0px 7px 10px #000000 inset;

  }
  .foot{
	    height: 200px;
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
 .box img{
  height: 240px;
  width: 250px;

 }
</style>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script src="/js/modernizr.custom.17475.js"></script>

</head>
<body>
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

    <div class="container">
      <!-- head gallery -->
      <ul id="carousel" class="elastislide-list">
<%
		List<Post> hotlist = new HotPostService().getHotPost();
		Iterator<Post> it = null;
		if(hotlist != null)
		{			
			it = hotlist.iterator();
			for(int i = 0; i < 6 && it.hasNext(); i++)
			{
				Post post = it.next();
%>
				<li>
					<a href="/journal/<%=post.getId() %>">
					<%=post.getCover()%>
					</a>
				</li>
<%
			}
		}
%>        
      </ul>
      
      <div class="row-fluid">
      <!-- other hot post -->
      <br><br>
      	<div class="span8">
      	<%
      		if(it != null)
      		{
      			while(it.hasNext())
      			{    				
      				Post post = it.next();
      				String cover = post.getCover();
      				if(cover == null || cover.length() == 0)
      					continue;
      	%>
            <div class="box">
              <div class="row">
                  <div class="span4">
                      <%=cover%>
                  </div>
                  <div class="span6">
                    <a href="/journal/<%=post.getId() %>"><h4><%=post.getTitle() %></h4></a>
                    <p><%=post.getSummary() %></p>                               
                  </div>                
                  <div class="span2" align="center" id="<%=post.getId() %>">
                  	<br><br><br>
                    <span>
                    	<button class="btn btn-danger like">like</button>
                    </span>
                    <span><%=post.getLikes() %></span>
                  </div>
              </div>          
            </div>
          <br>
<%
      			}
      		}    		
%>   		
      	</div>
      	<!-- tag list -->
      	<div class="span3 slide">
      		<div>
	          <ul style="list-style-type:none;">
              <li><a href="edit"><button class="btn btn-large btn-primary" type="button" id="np">写游记</button></a></li><br>
              <legend>热门标签</legend>
<%
			List<PostTag> taglist = TagService.getHotTag();
			if(taglist != null)
			{
				Iterator<PostTag> tagit = taglist.iterator();
				PostTag tag = null;
				while(tagit.hasNext())
				{
					tag = tagit.next();
%>
					<li><a href="/tag/<%=tag.getTagname() %>"><button class="btn btn-warning"><%=tag.getTablename() %></button></a></li><br>
<%					
				}
			}
%>
            </ul>
      		</div>
      	</div>
      </div>
	  <script type="text/javascript" src="/js/jquery.js"></script>
	  <script type="text/javascript" src="/js/jquerypp.custom.js"></script>
	  <script type="text/javascript" src="/js/jquery.elastislide.js"></script>
	  <script type="text/javascript">      
	    $( '#carousel' ).elastislide();  
	    $('.like').bind('click', function(){
	    	var $likespan = $(this).parent().next('span');
	    	var likecount = $likespan.text();
	    	var pidVal = $(this).parent().parent().attr('id');
	    	$.post('/like', {
	    		pid:pidVal
	    	}, function(data){
	    		if(data == "1")
	    		{    			
	    			$likespan.text(Number(likecount)+1);
	    		}
	    		else if(data == '-1')
	    		{
	    			alert('请先登录 ');
	    		}
	    	});
	    });
	  </script>
    </div> <!-- /container -->
    <div class="foot box-shadow">
      <br><br>
      <div class="span12">
        <p align="center">&copy; ShareTour 2013</p>
      </div>
      <div class="span12" style="text-align: center">
        <span><img src="img/cf_logo.png" alt="cloud foundry"> </span>
        <span><img src="img/mongo.png" alt="MongoDB"> </span>
      </div>

    </div>
</body>
</html>