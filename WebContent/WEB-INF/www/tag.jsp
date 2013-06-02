<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.service.TagService" %>
<%@ page import="com.sharetour.service.SubscriptionService" %>
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
        <div class="span8">
      		<%
      		if(postlist != null)
      			for(Post post:postlist){ 
      		%>
      		<div class="media">
	            <div class="row">
	              <div class="span1">
	                <a class="pull-left" href="<%=request.getContextPath()%>/u/">
	                  <img class="media-object" src="<%=request.getContextPath()%>../../img/head.jpg" style="height:64px;width=64px;">
	                </a>                 
	              </div>
	              <!-- end span1 -->
	              <div class="span7">
	                <div class="media-body">
	                    <div class="thumbnail">
	                      <div>
	                        <h4><a href="<%=request.getContextPath()%>/post/<%=post.getId() %>"><%=post.getTitle() %></a></h4>
	                      </div>
	                      <img src="<%=request.getContextPath()%>../../img/7.jpg">
	                      <div class="caption">
	                        <p><%=post.getSummary() %></p>
	                        <%for(String t:StringUtils.split(post.getTags(), " ")){ %>
		              			<a href="<%=request.getContextPath()%>/tag/<%=t%>"><span class="label label-warning"><%=t %></span></a>
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
        </div> <!-- end span8 posts -->
        <div class="span4">		
          <%
          	SubscriptionService subservice = new SubscriptionService();
          %>	
          <div class="accordion">
            <div class="accordion-group">
                <div class="accordion-heading">
                  <div class="accordion-inner">
                    <i class="icon-tag"></i>&nbsp;&nbsp;<%=tag %>
                    <%if(session.getAttribute("user") == null) {%>
                    <a href="<%=request.getContextPath()%>/action/subscribe?action=sub&tagname=<%=tag%>" class="pull-right sub">订阅</a>
                    <%} 
                    else{
                    	UserInfo user = (UserInfo)session.getAttribute("user");
                    	if(!subservice.checkSubStatus(user.getId(), tag)){%>
                    	<a href="<%=request.getContextPath()%>/action/subscribe?action=sub&tagname=<%=tag%>" class="pull-right sub">订阅</a>
                    	<%}else{%>
                    	<a href="<%=request.getContextPath()%>/action/subscribe?action=undosub&tagname=<%=tag%>" class="pull-right sub">取消订阅</a>
                    	<%} %>
                    <%} %>                           
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

