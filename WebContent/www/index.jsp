<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.service.HotPostService" %>
<%@ page import="com.sharetour.service.TagService" %>
<%@ page import="com.sharetour.service.PostLikeService" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ShareTour</title>
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
        <div class="span8">
          <!-- begin gallery billboard -->
          <div class="gallery billboard">
            <ul class="rslides">
              <li>
                <img src="/img/7.jpg" alt="">
                <p class="caption">This is a caption</p>
              </li>
              <li>
                <img src="/img/8.jpg" alt="">
                <p class="caption">This is another caption</p>
              </li>
              <li>
                <img src="/img/9.jpg" alt="">
                <p class="caption">This is another caption</p>
              </li>
            </ul>                   
          </div>
          <!-- end gallery billboard --> 
          
          <!-- begin hot post gallery -->
          <div>
            <ul class="thumbnails">
              <li class="hpbox">
                <div class="thumbnail">
                  <img src="../img/pre.jpg" alt="">
                  <h3>Thumbnail label</h3>
                  <p>Thumbnail caption...</p>
                </div>
              </li>
              <li class="hpbox">
                <div class="thumbnail">
                  <img src="../img/pre.jpg" alt="">
                  <h3>Thumbnail label</h3>
                  <p>Thumbnail caption...</p>
                </div>
              </li>          
              <li class="hpbox">
                <div class="thumbnail">
                  <img src="../img/pre.jpg" alt="">
                  <h3>Thumbnail label</h3>
                  <p>Thumbnail caption...</p>
                </div>
              </li>          
              <li class="hpbox">
                <div class="thumbnail">
                  <img src="../img/pre.jpg" alt="">
                  <h3>Thumbnail label</h3>
                  <p>Thumbnail caption...</p>
                </div>
              </li>  
              <li class="hpbox">
                <div class="thumbnail">
                  <img src="../img/pre.jpg" alt="">
                  <h3>Thumbnail label</h3>
                  <p>Thumbnail caption...</p>
                </div>
              </li>          
              <li class="hpbox">
                <div class="thumbnail">
                  <img src="../img/pre.jpg" alt="">
                  <h3>Thumbnail label</h3>
                  <p>Thumbnail caption...</p>
                </div>
              </li>                        
            </ul>            
          </div>  
          <!-- end hotposts gallery -->    
		      <!-- begin btn group leader -->
          <p class="lead">Bare minimum radio button tabs example:</p>
          <div id="tab" class="btn-group" data-toggle="buttons-radio">
            <a href="#hotposts" class="btn active" data-toggle="tab">热门游记</a>
            <a href="#newposts" class="btn" data-toggle="tab">最新游记</a>
            <a href="#requests" class="btn" data-toggle="tab">Requests</a>
          </div>
          <!-- end btn group leader -->          
     
          <!-- begin hotposts tab-content -->
          <div class="tab-content">
            <div class="tab-pane active" id="hotposts">
            <div class="post">
                <div class="row">
                  <div class="span8">
                    <div class="row">
                      <div class="span8">
                        <div class="posttitle">
                            <h4><strong><a href="#">Title of the post</a></strong></h4>
                          </div>
                      </div>                   
                    </div>
                    <!-- end row level2 -->
                    <div class="row">
                      <div class="span2">
                        <div class="preimg">
                          <a href="#" class="thumbnail">
                              <img src="/img/pre.jpg" alt="">
                          </a>                      
                        </div>

                      </div>
                      <div class="span6">     
                        <p>
                          <i class="icon-user"></i> by <a href="#">Mark</a> 
                          | <i class="icon-calendar"></i> Sept 16th, 2012
                          | <i class="icon-comment"></i> <a href="#">3 Comments</a>
                        </p> 
                        <p>
                          Lorem ipsum dolor sit amet, id nec conceptam conclusionemque. Et eam tation option. Utinam salutatus ex eum. Ne mea dicit tibique facilisi, ea mei omittam explicari conclusionemque, ad nobis propriae quaerendum sea.
                        </p>
                        <div class="row">
                          <div class="span4">
                            <p>
                              <i class="icon-tags"></i> Tags : <a href="#"><span class="label label-info">Snipp</span></a> 
                              <a href="#"><span class="label label-warning">Bootstrap</span></a> 
                              <a href="#"><span class="label label-warning">UI</span></a> 
                              <a href="#"><span class="label label-warning">growth</span></a>
                            </p>                                                  
                          </div>
                          <div class="span2">
                              <a href="#"><span class="label label-important">6 like</span></a> 
                              <a href="#"><span class="label label-success">35 share</span></a>
                          </div>

                        </div>

                      </div>
                    </div>
                    <!-- end row level 2 -->
                  </div>
                  <!-- end span8 -->
                </div>  
              <!-- end row level1 -->
              </div>
              <!-- end sample post -->
              <%
              HotPostService hotpostservice = new HotPostService();
              List<Post> postlist = hotpostservice.getHotPost();
              Iterator<Post> it = postlist.iterator();
              SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
              UserInfo user = (UserInfo)session.getAttribute("user");
              PostLikeService postlikeservice = new PostLikeService();
              while(it.hasNext()){
                 Post post = it.next(); 
              %>
              <div class="post">         
                <div class="row">
                  <div class="span8">
                    <div class="row">
                      <div class="span8">
                        <div class="posttitle">
                            <h4><strong><a href="/post/<%=post.getId()%>"><%=post.getTitle() %></a></strong></h4>
                          </div>
                      </div>                   
                    </div>
                    <!-- end row level2 -->
                    <div class="row">
                      <div class="span2">
                        <div class="preimg">
                          <a href="#" class="thumbnail">
                              <img src="../img/pre.jpg" alt="">
                          </a>
                        </div>
                      </div>
                      <div class="span6">     
                        <p>
                      <i class="icon-user"></i> by <a href="#">John</a> 
                      | <i class="icon-calendar"></i> <%=format.format(post.getCtime()) %>
                      | <i class="icon-comment"></i> <a href="#">3 Comments</a>
                        </p> 
                    <p>
                	<%=post.getSummary() %>                        
                    </p>
                        <div class="row">
                          <div class="span4">
                            <p>
                        <i class="icon-tags"></i> 
                          Tags : 
                        <%for(String tag:StringUtils.split(post.getTags(), ' ')) {%>
                          <a href="/tag/<%=tag%>"><span class="label label-warning"><%=tag %></span></a>
                        <%} %> 
                            </p>                                                  
                          </div>
                          <div class="span2">
                              <a href="/action/like?postid=<%=post.getId() %>"  
                              <%
                                if(user != null && 
                                postlikeservice.checkPostLike(user.getId(), post.getId())){
                              %>
                                class="liketag liked">  
                              <%
                                }
                                else{
                              %>
                                class="liketag">
                              <%
                                }
                              %>
                                <span class="likecount"><%=postlikeservice.getPostLikeCount(post.getId()) %></span>
                                <span>&nbsp;like</span>
                              </a> 
                              <a href="#"  class="sharetag"><span>35 share</span></a>
                          </div>

                        </div>

                      </div>
                    </div>
                    <!-- end row level 2 -->
                  </div>
                  <!-- end span8 -->
                </div>  
              <!-- end row level1 -->
              </div>
              <!-- end post -->     
              <%} %>                            
            </div>
            <!-- end hotposts -->
          </div>
          <!-- begin hotposts tab-content -->
        </div>
        <!-- end span8  -->
          
        <div class="span4">
          <div class="hottags">
      			<ul class="taglist">
      				<li><a href="#" class="taglisttitle">HotTags</a></li>
      	           <% 
      	            List<PostTag> hottags = TagService.getHotTag();
      	            if(hottags != null)
      	            {
      					for(PostTag tag: hottags) {%>				
      	            <li><a href="/tag/<%=tag.getTagname() %>"><%=tag.getTagname()+"  "+ tag.getPostcount() %></a></li>			
      					<%}
      	            } %>  			
      			</ul>
          </div> 
          <!-- end hottags -->    
          
          <!-- begin week and month hot post nav -->
          <div class="tabbable">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#weekhotposts" data-toggle="tab">week</a></li>
              <li><a href="#monthhotposts" data-toggle="tab">month</a></li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane active" id="weekhotposts">
              <%
                List<Post> whotposts = hotpostservice.getHostPostOfThisWeek(1, 10);
                if(whotposts != null && whotposts.size() != 0){
					for(Post wp: whotposts) {
                  		String summary = wp.getSummary();
                %>
                <div class="media">
                  <a class="pull-left" href="/post/<%=wp.getId() %>">
                    <img class="media-object" src="../img/head.jpg" style="width: 64px; height: 64px;">
                  </a>
                  <div class="media-body">
                    <a href="/post/<%=wp.getId() %>"><%=wp.getTitle() %></a>
                    <p><%=summary.length()>40?summary.substring(0, 40):summary %></p>
                  </div>
                </div>
                <%} %>
              <%} %>
              </div>
              <!-- end week hot posts -->
              <div class="tab-pane" id="monthhotposts">
              <%
              	List<Post> mhotposts = hotpostservice.getHotPostOfMonth(1, 10);
              	if(mhotposts != null && mhotposts.size() != 0){
              		for(Post mp: mhotposts){
              			String summary = mp.getSummary();
              %>
                <div class="media">
                  <a class="pull-left" href="/post/<%=mp.getId() %>">
                    <img class="media-object" src="../img/head.jpg" style="width: 64px; height: 64px;">
                  </a>
                  <div class="media-body">
                    <a href="/post/<%=mp.getId() %>"><%=mp.getTitle() %></a>
                    <p><%=summary.length()>40?summary.substring(0, 40):summary %></p>
                  </div>
                </div>              	
              <%
              		}
              	}
              %>
              </div>
              <!-- end month hot posts -->
            </div>
          </div>
        </div>
        <!-- end span4 -->        
      </div>
      <!-- end row -->
    </div>
    <!-- end container  -->
    
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/responsiveslides.min.js"></script>
    <script type="text/javascript" src="/js/index.js"></script>
    <script type="text/javascript">
     $(".rslides").responsiveSlides({
          auto: false,
          pager: false,
          nav: true,
          speed: 500,
          namespace: "callbacks"
        });
    </script>      
  </body>
</html>

