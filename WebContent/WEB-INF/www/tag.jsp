<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.service.*" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%	
	String tag = request.getParameter("p1");
	tag = new String(tag.getBytes("ISO-8859-1"), "UTF-8");
	if(tag == null || tag.length() == 0){
		response.sendRedirect(request.getContextPath()+"/");
	}
	TagService tagservice = new TagService();
	List<Post> postlist = tagservice.getPostsRelatedToTag(tag);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	UserInfo user = (UserInfo)session.getAttribute("user");
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
      .caption img{
      	float: left;
      	margin: 0 10px 10px 0;
      }
      .caption .action{
      	float: right;
      	padding-right:10px;
      	text-align: right;
      }
    </style>
    <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
  </head>
  <body>

	<%@ include file="topbar.jsp" %>    
    <div class="container">
      <div class="row">
        <div class="span8">
      		<%
      		if(postlist != null) {
      			AvatorService avatorService = new AvatorService();
      			for(Post post:postlist){ 
      		%>
      		<div class="media">
	            <div class="row">
	              <div class="span1">
	                <a class="pull-left" href="<%=request.getContextPath()%>/u/">
	                  <img class="media-object img-rounded"
	                  src="<%=request.getContextPath()%>/imgs?id=<%=avatorService.getAvatorOfUser(post.getAuthorid()).getAvatorId()%>&coll=avator_thumb" 
	                  style="height:64px;width:64px;">	                 
	                </a>                 
	              </div>
	              <!-- end span1 -->
	              <div class="span7">
	                <div class="media-body">
	                    <div class="thumbnail">
	                      <div class="caption">
	                        <h4><a href="<%=request.getContextPath()%>/post/<%=post.getId() %>"><%=post.getTitle() %></a></h4>
	                      </div>                      
	                      <div class="caption clearfix">
	                        <%if(post.getCover()!=null) {%>
	                        <img src="<%=post.getCover()%>&height=150&width=150">
	                        <%}%>
	                        <%=post.getSummary() %>
	                      </div>
	                      <div class="caption">
	                      	<div class="row">
	                      		<div class="span4">
			                        <p>
			                        <%for(String t:StringUtils.split(post.getTags(), " ")){ %>
				              			<a href="<%=request.getContextPath()%>/tag/<%=t%>"><span class="label label-warning"><%=t %></span></a>
				              		<%} %>
				              		</p>		                      		
	                      		</div>
	                      		<div class="span2 action">
	                      			<span><a href="">转载</a></span>
	                      			<span><a href="">评论</a></span>
	                      			<span><a href="">like</a></span>
	                      			<span>12</span>
	                      		</div>
	                      	</div>                      
	                      </div>
	                    </div>              
	                </div>                 
	              </div>
	              <!-- end span7  -->
	            </div>
				<!-- end row -->
      		</div>
      		<!-- end media -->
      		<%} }%>         
        </div> <!-- end span8 posts -->
        <div class="span4">		
          <%
          	SubscriptionService sub = new SubscriptionService();
          %>	
          <div class="accordion">
            
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" href="<%=request.getContextPath()%>/u/posts">
                  <i class="icon-file"></i>&nbsp;&nbsp;我的游记
                </a>
              </div>
            </div>          
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" href="<%=request.getContextPath()%>/u/album">
                  <i class="icon-picture"></i>&nbsp;&nbsp;我的相册
                </a>
              </div>
            </div>                 
          	<br />
            <div class="accordion-group">
                <div class="accordion-heading">
                  <div class="accordion-inner">
                    <i class="icon-tag"></i>&nbsp;&nbsp;<%=tag %>
                    <%if(user == null) {%>
                    <a href="<%=request.getContextPath()%>/action/subscribe?action=sub&tagname=<%=tag%>" class="pull-right sub">订阅</a>
                    <%} 
                    else{
                    	if(!sub.checkSubStatus(user.getId(), tag)){%>
                    	<a href="<%=request.getContextPath()%>/action/subscribe?action=sub&tagname=<%=tag%>" class="pull-right sub">订阅</a>
                    	<%}else{%>
                    	<a href="<%=request.getContextPath()%>/action/subscribe?action=undosub&tagname=<%=tag%>" class="pull-right sub">取消订阅</a>
                    	<%} %>
                    <%} %>                           
                  </div>        
                </div>	
            </div> 
            <!-- end sub action -->
            <br />          	
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" href="<%=request.getContextPath()%>#subTags">
                  <i class="icon-tags"></i>&nbsp;&nbsp;	我订阅的标签
                </a>
              </div>
              <div id="subTags" class="accordion-body collapse in">
                <div class="accordion-inner">
                  <%
                  	List<PostTag> tlist = sub.getAllTagsOfUser(user.getId());
                  	if(tlist != null && tlist.size() != 0){
                  %>
                  	<ul class="unstyled">
                  		<%for(PostTag t:tlist) {%>
                  		<li>
                  			<i class="icon-tag"></i>&nbsp;&nbsp;
                  			<a href="<%=request.getContextPath()%>/tag/<%=t.getTagname() %>"><%=t.getTagname() %></a> 
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
          <!-- end accordion -->			
        </div> 
        <!-- end span4 -->
      </div>
      <hr>
      <footer>
        <p>&copy; Company 2013</p>
      </footer>

    </div> <!-- /container -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  </body>
</html>

