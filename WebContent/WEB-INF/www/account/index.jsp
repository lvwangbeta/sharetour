<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.sharetour.service.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
	UserInfo user = (UserInfo)session.getAttribute("user");
	if(user == null) {
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
      .caption img{
      	float: left;
      	margin: 0 10px 10px 0;
      }
      .caption .action{
      	float: right;
      	padding-right:10px;
      	text-align: right;
      }    
      #avator{
      	width: 100px;
      	height:100px;
      }  
      .account-info{
      	margin-top: 30px;
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
        	<div>
        		<h3>账户设置</h3>
        	</div>
        	<div class="box">
				<form class="form-horizontal account-info" id="account-set">
				  <div class="control-group">
				  	<label class="control-label" for="nickname">我的大名</label>
				    <div class="controls">
				    	<input type="text" name="nickname" id="nickname" 
				    	value="<%= user.getNickname()==null?"":user.getNickname() %>" />
				    </div>
				  </div>
				  <hr />				
				  <div class="control-group">
				    <label class="control-label" for="avator">头像</label>
				    <div class="controls">
        			<%
        				AvatorService avatorService = new AvatorService();
        				Avator avator = avatorService.getAvatorOfUser(user.getId());
        				if(avator != null) {
        			%>
        				<img  class="img-rounded" id="avator" src="<%=request.getContextPath()%>/imgs?id=<%=avator.getAvatorId()%>&coll=avator_thumb"
        				alt="avator" />
        			<%		
        				
        				}
        			%>  
        				<span><a class="btn" href="<%=request.getContextPath()%>/account/avator">更改头像</a></span>				      
				    </div>
				  </div>
				  <hr />
				  <div class="control-group">
				    <label class="control-label" for="intro">个性签名</label>
				    <div class="controls">
				      <%if(user.getIntro() == null) {%>
				      <textarea class="span4" name="intro" id="intro" rows="5"></textarea>
				      <%}else {%>
				      <textarea class="span4" name="intro" id="intro" rows="5"><%=user.getIntro() %></textarea>
				      <%} %>
				    </div>
				  </div>
				  <div class="control-group">
				    <div class="controls">
				      <button type="submit" id="submit" class="btn btn-success">保存</button>
				    </div>
				  </div>
				</form>        	
        	</div>       
        </div>
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
  	
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	$('#submit').click(function(){
    		var nickname = $('#nickname').val();
    		var intro = $('#intro').val();
    		$.post('<%=request.getContextPath()%>/action/accountsetting', 
    			   {'nickname': nickname, 'intro': intro}, 
    			   function(data){
    				if("success"==data) {
    					window.location.href='<%=request.getContextPath()%>/u/space';
    				}
    		});
    		return false;
    	});
    	
    	
    });
    
    </script>
  </body>
</body>