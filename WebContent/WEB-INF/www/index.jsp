<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharetour.service.*" %>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="com.sharetour.model.Avator" %>
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
    <meta property="qc:admins" content="7150557211254752634754675642163757" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="index">
    <meta name="author" content="gavin">

    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/magnific-popup.css" />
    <style>
      body {
        padding-top: 60px;
        padding-bottom: 40px;
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
	  .albumbox{
	  	height: 150px;
	  	padding: 0;
	  	overflow: hidden;
	  }   
	  .albumpre{
	  	position: relative;
	  }
	  .overlay{
		position: absolute;
		color: #fff;
		left: 0;
		bottom: -1px;
		background: #000;
		height: 40px;
		width: 100%;
		overflow: hidden;
		filter: Alpha(opacity=60);    
	    -moz-opacity:.6;    
	    opacity:0.6;
	    padding:10px;
	  }	
	  .overlay a{
		color: #fff;
		text-decoration: none;
	  }	   
	  #popuser{
	  	margin-bottom: 30px;
	  }
	  #avatars img{
	  	height:64px;
	  	width:64px;
	  }
	  .avator{
	  	float: left;
	  	margin-right: 30px;
	  	margin-bottom: 10px;
	  }
	  .album-action {
	  	border-top: 1px solid #ddd;
	  	height: 40px;
	  	width: 100%;
	  }
	  .album-action div{
	  	float: left;
	  	height: 40px;
	  	width:32%;
	  	text-align: center;
	  	padding-top: 10px;
	  }
	  .album-visit, .album-share{
	  	border-right: 1px solid #ddd;
	  }
	  .icon-hearted {
	  	background: url("<%=request.getContextPath()%>/img/heart.png");
	  	width: 16px;
	  	height: 16px;
	  }
	  .side-widget{
	  	margin-bottom: 20px;
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
          <%
          	AlbumService albumService = new AlbumService();
          	List<Album> albums = albumService.getHotAlbums("visit", 1, 7);
          	Iterator<Album> it = albums.iterator();
          	if(it.hasNext()) {
          		Album gallery = it.next();
          		List<Photo> photos = gallery.getPhotos();
          		if(photos != null && photos.size() != 0){
          %>
            <ul class="rslides">
            <%
            	for(Photo photo : photos){
            %>
              <li>
                <img src="<%=request.getContextPath()%>/imgs?id=<%=photo.getId()%>&width=770&height=370" alt="">
                <p class="caption"><%=photo.getDesc()%></p>
              </li>
            <%} %>
            </ul>    
            <%} }%>               
          </div>
          <!-- end gallery billboard --> 
          
          <!-- begin hot post gallery -->
          <%
          	if(albums != null){     		
          %>
          <div>
            <ul class="thumbnails">
            <%
            	while(it.hasNext()){
            		Album album = it.next();
            %>
              <li class="hpbox">
                <div class="thumbnail albumbox">
                  <div class="albumpre">
                  	<a class="pop" href="<%=request.getContextPath()%>/popalbum/<%=album.getId()%>"><img src="<%=request.getContextPath()%>/imgs?id=<%=album.getCoverid()%>&width=236&height=150"  alt="<%=album.getAlbumname() %>"></a>
		            <div class="overlay">
		            	<p><%=album.getAlbumname()%></p>
		            </div>                    
                  </div>
				  
                </div>
              </li> 
              <%} %>           
            </ul>            
          </div>  
          <%} %>
          <!-- end hotposts gallery -->
          
          
          <!-- begin posts list -->
          <%
          HotPostService hotpostservice = new HotPostService();
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
          PostLikeService postlikeservice = new PostLikeService();
          UserService userService = new UserService();
          PostService postService = new PostService();                 
          %>        
		  <div class="tabbable">
		  	<ul class="nav nav-tabs">
		    	<li class="active"><a href="#hotposts" data-toggle="tab">热门游记</a></li>
		    	<li><a href="#newposts" data-toggle="tab">最新游记</a></li>
		    </ul>
		    <div class="tab-content">
	            <div class="tab-pane active" id="hotposts">
	              <%
	              List<Post> hotpostlist = hotpostservice.getHotPost();
	              Iterator<Post> postit = hotpostlist.iterator();
	              while(postit.hasNext()){
	                 Post post = postit.next(); 
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
	                          <a href="<%=request.getContextPath()%>/post/<%=post.getId()%>" class="thumbnail">
	                              <img src="<%=post.getCover()%>&width=160&height=128" alt="preimg">
	                          </a>
	                        </div>
	                      </div>
	                      <div class="span6">     
	                        <p>
	                      <i class="icon-user"></i> by <a href="<%=request.getContextPath()%>/u/<%=post.getAuthorid()%>">
	                      <%=userService.findUserById(post.getAuthorid()).getUsername()%></a> 
	                      | <i class="icon-calendar"></i> <%=format.format(post.getCtime()) %>
	                      | <i class="icon-comment"></i> <a href="#"><%=postService.getCommentsByPostId(post.getId()).size() %> Comments</a>
	                        </p> 
	                    <p>
	                	<%=post.getSummary() %>                        
	                    </p>
	                        <div class="row">
	                          <div class="span4">
	                            <p>
	                        <i class="icon-tags"></i> 
	                         
	                        <%for(String tag:StringUtils.split(post.getTags(), ' ')) {%>
	                          <a href="<%=request.getContextPath()%>/tag/<%=tag%>"><span class="label deepbluelabel"><%=tag %></span></a>
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
		    	<div class="tab-pane" id="newposts">
		    	<%
		    		List<Post> newposts = postService.getPostList(1, 10, "ctime");
		    		Iterator<Post> newpostsit = newposts.iterator();
		    		while(newpostsit.hasNext()) {
		    			Post newpost = newpostsit.next();
		    	%>
	              <div class="post">         
	                <div class="row">
	                  <div class="span8">
	                    <div class="row">
	                      <div class="span8">
	                        <div class="posttitle">
	                            <h4><strong><a href="<%=request.getContextPath()%>/post/<%=newpost.getId()%>"><%=newpost.getTitle() %></a></strong></h4>
	                          </div>
	                      </div>                   
	                    </div>
	                    <!-- end row level2 -->
	                    <div class="row">
	                      <div class="span2">
	                        <div class="preimg">
	                          <a href="<%=request.getContextPath()%>/post/<%=newpost.getId()%>" class="thumbnail">
	                              <img src="<%=newpost.getCover()%>&width=160&height=128" alt="preimg">
	                          </a>
	                        </div>
	                      </div>
	                      <div class="span6">     
	                        <p>
	                      <i class="icon-user"></i> by <a href="<%=request.getContextPath()%>/u/<%=newpost.getAuthorid()%>">
	                      <%=userService.findUserById(newpost.getAuthorid()).getUsername()%></a> 
	                      | <i class="icon-calendar"></i> <%=format.format(newpost.getCtime()) %>
	                      | <i class="icon-comment"></i> <a href="#"><%=postService.getCommentsByPostId(newpost.getId()).size() %> Comments</a>
	                        </p> 
	                    <p>
	                	<%=newpost.getSummary() %>                        
	                    </p>
	                        <div class="row">
	                          <div class="span4">
	                            <p>
	                        <i class="icon-tags"></i> 
	                         
	                        <%for(String tag:StringUtils.split(newpost.getTags(), ' ')) {%>
	                          <a href="<%=request.getContextPath()%>/tag/<%=tag%>"><span class="label deepbluelabel"><%=tag %></span></a>
	                        <%} %> 
	                            </p>                                                  
	                          </div>
	                          <div class="span2">
	                              <a href="<%=request.getContextPath()%>/action/like?postid=<%=newpost.getId() %>"  
	                              <%
	                                if(user != null && 
	                                postlikeservice.checkPostLike(user.getId(), newpost.getId())){
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
	                                <span class="likecount"><%=postlikeservice.getPostLikeCount(newpost.getId()) %></span>
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
		    </div>
		    <!-- end tab content -->
		  </div>          
          
                    
        </div>
        <!-- end span8  -->
          
        <div class="span4">  
          <div id="hottag-area" class="side-widget">
			  <div class="tabbable">
			    <ul class="nav nav-tabs">
			      <li class="active"><a href="hottags" data-toggle="tab">热门标签</a></li>
			    </ul>
			    <div class="tab-content">
			      <div class="tab-pane active" id="hottags">
	      	           <% 
	      	            List<PostTag> hottags = TagService.getHotTag();
	      	            if(hottags != null)
	      	            {
	      					for(PostTag tag: hottags) {%>				
	      	            <a class="tag redtag" href="<%=request.getContextPath()%>/tag/<%=tag.getTagname() %>"><%=tag.getTagname()+"  "+ tag.getPostcount() %></a>			
	      					<%}
	      	            } %>  
			      </div>
			    </div>
			  </div>          
          </div>       
          <!-- end hot tag area -->
          
          <div id="popuser" class="side-widget">
			<div class="tabbable">
			  <ul class="nav nav-tabs">
			    <li class="active"><a href="#avatars" data-toggle="tab">热门用户</a></li>
			  </ul>
			  <div class="tab-content">
			    <div class="tab-pane active" id="avatars">
			      <div>
			      <%
			      	AvatorService avatorService = new AvatorService();
			       	FollowService followService = new FollowService();
			      	List<UserInfo> popusers = userService.getPopUsers(6);
			      	for(UserInfo p : popusers){
			      		if(user != null){
			      			if(p.getId() == user.getId() || 
			      			   followService.checkFollowingRelation(user.getId(), p.getId())) 
			      				continue;
			      		}	
			      		Avator avator = avatorService.getAvatorOfUser(p.getId());
			      		if(avator != null) {
			      %>
			      	<div class="avator">
			      		<span>
			      		<a href="#"><img class="img-rounded" src="<%=request.getContextPath()%>/imgs?id=<%=avator.getAvatorId() %>&coll=avator_thumb" alt="" /></a>
			      		</span>
			      		<span>
			      		<a href="<%=request.getContextPath()%>/action/follow?followingid=<%=avator.getUid()%>" class="tag bluetag follow">+关注</a>
			      		</span>	      		
			      	</div>	
			      <%} } %>	      				      				      	
			      </div>
			    </div>
			  </div>
			</div>			              
          </div>
          <!-- end popuser -->
          
          
          <!-- begin week and month hot post nav -->
          <div id="hotposts" class="side-widget">
	          <div class="tabbable">
	            <ul class="nav nav-tabs">
	              <li class="active"><a href="#weekhotposts" data-toggle="tab">本周热门</a></li>
	              <li><a href="#monthhotposts" data-toggle="tab">本月热门</a></li>
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
	                    <img class="media-object img-rounded" 
	                    src="<%=request.getContextPath()%>/imgs?id=<%=avatorService.getAvatorOfUser(wp.getAuthorid()).getAvatorId()%>&coll=avator_thumb" 
	                    style="width: 64px; height: 64px;">
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
	                    <img class="media-object img-rounded" 
	                    src="<%=request.getContextPath()%>/imgs?id=<%=avatorService.getAvatorOfUser(mp.getAuthorid()).getAvatorId()%>&coll=avator_thumb" 
	                    style="width: 64px; height: 64px;">
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
          <!-- end hotposts area  -->
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
    	$('.follow').click(function(){
    		var link = $(this);    	
    		if(!$(link).hasClass('followed')){
        		$.post($(link).attr('href'),{'action':'follow'}, function(data){
        			if('success' == data) {
        				$(link).removeClass('bluetag');
        				$(link).addClass('redtag followed');
        				$(link).text('已关注');
        			}
        			
        		});    			
    		} else if($(link).hasClass('followed')) {
    			$.post($(link).attr('href'), {'action':'undofollow'}, function(data){
    				if('success' == data) {
    					$(link).removeClass('redtag followed');
    					$(link).addClass('bluetag');
    					$(link).text('+关注');
    				}
    				
    			});
    		}

    		return false;
    		
    	});
    	
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

