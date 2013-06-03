<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.sharetour.service.SubscriptionService" %>
<%@ page import="com.sharetour.service.AlbumService" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/magnific-popup.css" />
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .imgshow img{
      	width: 156px;
      	height: 156px;
      	margin: 0;
      	padding: 0;
		margin-bottom: 5px;
      }  
      .mfp-container {
		margin-top: 20px;
	  } 
      .albumdesc{
      	margin-top: 10px; 
      }   
	  .white-popup-block {
		background: #FFF;
		padding: 20px 20px;
		text-align: left;
		max-width: 650px;
		margin: 40px auto;
		position: relative;
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
							<a class="btn btn-large" type="button" href="<%=request.getContextPath()%>/newalbum">新建相册</a>							
	                    </div>              
	                </div>      	 			
      	 		</div>
      	 	</div>
      	 
      	 </div>
      	 <!-- end media  -->      	
      		<%
      		AlbumService albumService = new AlbumService();
      		List<Album> albums = albumService.getAlbumsOfUser(user.getId());
      		if(albums != null){
      			for(Album album : albums){
      		%>
      		<div class="media">
	            <div class="row">
	              <div class="span1">
	                <a class="pull-left" href="/u/">
	                  <img class="media-object" src="<%=request.getContextPath()%>/img/head.jpg" style="height:64px;width=64px;">
	                </a>                 
	              </div>
	              <!-- end span1 -->
	              <div class="span7">
	                <div class="media-body">
	                    <div class="thumbnail">
	                      <div class="caption">
	                      		<h4><%=album.getAlbumname() %></h4>
		                      	<div class="imgshow">
		                      	
								<%
								List<Photo> photos = album.getPhotos();
								if(photos != null){
									for(Photo photo: photos){
								%>
										<a class="pop" href="<%=request.getContextPath()%>/popalbum/<%=album.getId()%>">
										<img alt="<%=photo.getDesc()%>" 
										src="<%=request.getContextPath()%>/imgs?id=<%=photo.getId()%>&height=156&width=156">
										</a>
								<%
									}
								}
								%>
								</div>
								<!-- end imgshow  -->
								<p class="albumdesc"><%=album.getDesc() %></p>
	                      </div>
	                    </div>              
	                </div>                 
	              </div>
	              <!-- end span7  -->
	            </div>
				<!-- end row -->
      		</div>
      		<!-- end media -->
      		<%}} %>
      	</div>
      	<!-- end span8  -->
      	
        <div class="span4">
          <div class="accordion">
          
          
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" href="<%=request.getContextPath()%>/u/posts">
                  <i class="icon-user"></i>&nbsp;&nbsp;我的游记
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
                <a class="accordion-toggle" data-toggle="collapse" href="#subTags">
                  <i class="icon-tags"></i>&nbsp;&nbsp;	我订阅的标签
                </a>
              </div>
              <div id="subTags" class="accordion-body collapse in">
                <div class="accordion-inner">
                  <%
                  	SubscriptionService sub = new SubscriptionService(); 
                  	List<PostTag> tlist = sub.getAllTagsOfUser(user.getId());
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
                <a class="accordion-toggle" data-toggle="collapse" href="#following">
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.magnific-popup.js"></script>
    <script>
    $(document).ready(function(){
        $('.pop').magnificPopup({
            type: 'ajax',
            alignTop: true,
            overflowY: 'scroll' 
          });	
    });
    </script>     
  </body>
</body>