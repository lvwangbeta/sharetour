<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.sharetour.service.PostService" %>
<%@ page import="com.sharetour.service.SubscriptionService" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
	UserInfo user = (UserInfo)session.getAttribute("user");
	if(user == null)
	{
		response.sendRedirect(request.getContextPath()+"/");
		return;
	}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ShareTour</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="index">
    <meta name="author" content="gavin">

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
	<%@ include file="../topbar.jsp" %>
    <!--  begin container  -->
    <div class="container">
      <div class="row">
      	<div class="span8">
      	 <div class="media">
      	 	<div class="row">
      	 		<div class="span1">
      	 			<img class="media-object" src="<%=request.getContextPath()%>/img/head.jpg" style="height:64px;width=64px;">
      	 		</div>
      	 		<div class="span7">
	                <div class="media-body">
	                    <div class="thumbnail">
							<a class="btn btn-large" type="button" href="<%=request.getContextPath()%>/newpost">新游记</a>
							<a class="btn btn-large" type="button" href="<%=request.getContextPath()%>/album">新建相册</a>							
	                    </div>              
	                </div>      	 			
      	 		</div>
      	 	</div>
      	 
      	 </div>
      	 <!-- end media  -->     	
      	<%
        	PostService service = new PostService(); 
        	List<Post> postlist = service.getPostsOfAuthor(user.getId());
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    		
      		if(postlist != null && postlist.size() != 0)
      			for(Post post:postlist){ 
      	%>
      		<div class="media">
	            <div class="row">
	              <div class="span1">
	                <a class="pull-left" href="<%=request.getContextPath()%>/u/">
	                  <img class="media-object" src="<%=request.getContextPath()%>/img/head.jpg" style="height:64px;width=64px;">
	                </a>                 
	              </div>
	              <!-- end span1 -->
	              <div class="span7">
	                <div class="media-body">
	                    <div class="thumbnail">
	                      <div>
	                        <h4><a href="<%=request.getContextPath()%>/post/<%=post.getId() %>"><%=post.getTitle() %></a></h4>
	                      </div>
	                      <%if(post.getCover()!=null) {%>
	                      <img src="<%=request.getContextPath()%><%=post.getCover() %>">
	                      <%}%>
	                      <div class="caption">
	                        <p><%=post.getSummary() %></p>
	                        <%for(String tag:StringUtils.split(post.getTags(), " ")){ %>
		              			<a href="<%=request.getContextPath()%>/tag/<%=tag%>"><span class="label label-warning"><%=tag %></span></a>
		              		<%} %>
	                      </div>
	                    </div>              
	                </div>                 
	              </div>
	              <!-- end span7  -->
	            </div>
				<!-- end row -->
      		</div>
      		<!-- end media -->
      		<%} %>
      	</div>
      	<!-- end span8  -->
      	
        <div class="span4">
          <div class="accordion">
          
          
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" href="<%=request.getContextPath()%>#">
                  <i class="icon-user"></i>&nbsp;&nbsp;我的游记
                </a>
              </div>
            </div>          
          
          
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" href="<%=request.getContextPath()%>#subTags">
                  <i class="icon-tags"></i>&nbsp;&nbsp;	我订阅的标签
                </a>
              </div>
              <div id="subTags" class="accordion-body collapse in">
                <div class="accordion-inner">
                  <%
                  	List<PostTag> tlist = new SubscriptionService().getAllTagsOfUser(user.getId());
                  	if(tlist != null && tlist.size() != 0){
                  %>
                  	<ul class="unstyled">
                  		<%for(PostTag tag:tlist) {%>
                  		<li>
                  			<i class="icon-tag"></i>&nbsp;&nbsp;
                  			<a href="<%=request.getContextPath()%>/tag/<%=tag.getTagname() %>"><%=tag.getTagname() %></a> 
                  		</li>
                  		<%} %>
                  	</ul>
                  <%}%>
                </div>
              </div>
            </div>
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" href="<%=request.getContextPath()%>#following">
                  <i class="icon-user"></i>&nbsp;&nbsp;我的关注
                </a>
              </div>
              <div id="following" class="accordion-body collapse">
                <div class="accordion-inner">
                  
                </div>
              </div>
            </div>
        </div>
        <!-- end side bar -->
        </div>
        <!-- end span4 -->      	
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
  	
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  </body>
</body>