<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.service.*" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
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
    </style>
    <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/magnific-popup.css" />
    <style>
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
  </head>
  <body>

	<%@ include file="topbar.jsp" %>
    <div class="container">
      <div class="row">
        <div class="span8">
          <!-- begin gallery billboard -->
          <div class="gallery billboard">
            <ul class="rslides">
              <li>
                <img src="<%=request.getContextPath()%>/img/7.jpg" alt="">
                <p class="caption">This is a caption</p>
              </li>
              <li>
                <img src="<%=request.getContextPath()%>/img/8.jpg" alt="">
                <p class="caption">This is another caption</p>
              </li>
              <li>
                <img src="<%=request.getContextPath()%>/img/9.jpg" alt="">
                <p class="caption">This is another caption</p>
              </li>
            </ul>                   
          </div>
          <!-- end gallery billboard --> 
          
          <!-- begin hot post gallery -->
          <%
          	AlbumService albumService = new AlbumService();
          	if(user != null){
          		List<Album> albums = albumService.getAlbumsOfUser(user.getId());
          		if(albums != null){     		
          %>
          <div>
            <ul class="thumbnails">
            <%
            	for(Album album:albums){
            %>
              <li class="hpbox">
                <div class="thumbnail">
                  <a class="pop" href="<%=request.getContextPath()%>/popalbum/<%=album.getId()%>"><img src="<%=request.getContextPath()%>/imgs?id=<%=album.getCoverid()%>&width=226&height=152"  alt="<%=album.getAlbumname() %>"></a>
                  <h4><%=album.getAlbumname() %></h4>
                  <p><%=album.getDesc() %></p>
                </div>
              </li> 
              <%} %>           
            </ul>            
          </div>  
          <%}} %>
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
                              <img src="<%=request.getContextPath()%>/img/pre.jpg" alt="">
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
                            <h4><strong><a href="<%=request.getContextPath()%>/post/<%=post.getId()%>"><%=post.getTitle() %></a></strong></h4>
                          </div>
                      </div>                   
                    </div>
                    <!-- end row level2 -->
                    <div class="row">
                      <div class="span2">
                        <div class="preimg">
                          <a href="#" class="thumbnail">
                              <img src="<%=request.getContextPath()%>/img/pre.jpg" alt="">
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
                          <a href="<%=request.getContextPath()%>/tag/<%=tag%>"><span class="label label-warning"><%=tag %></span></a>
                        <%} %> 
                            </p>                                                  
                          </div>
                          <div class="span2">
                              <a href="<%=request.getContextPath()%>/action/like?postid=<%=post.getId() %>"  
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
      	            <li><a href="<%=request.getContextPath()%>/tag/<%=tag.getTagname() %>"><%=tag.getTagname()+"  "+ tag.getPostcount() %></a></li>			
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
                  <a class="pull-left" href="<%=request.getContextPath()%>/post/<%=wp.getId() %>">
                    <img class="media-object" src="/img/head.jpg" style="width: 64px; height: 64px;">
                  </a>
                  <div class="media-body">
                    <a href="<%=request.getContextPath()%>/post/<%=wp.getId() %>"><%=wp.getTitle() %></a>
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
                  <a class="pull-left" href="<%=request.getContextPath()%>/post/<%=mp.getId() %>">
                    <img class="media-object" src="<%=request.getContextPath()%>/img/head.jpg" style="width: 64px; height: 64px;">
                  </a>
                  <div class="media-body">
                    <a href="<%=request.getContextPath()%>/post/<%=mp.getId() %>"><%=mp.getTitle() %></a>
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
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/responsiveslides.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.magnific-popup.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>    
    <script>
    $(document).ready(function(){
        $(".rslides").responsiveSlides({
            auto: false,
            pager: false,
            nav: true,
            speed: 500,
            namespace: "callbacks"
          });    
        
        $('.pop').magnificPopup({
            type: 'ajax',
            alignTop: true,
            overflowY: 'scroll' 
          });	
    });
    </script>          
  </body>
</html>

