<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.UserInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
	UserInfo user = (UserInfo)session.getAttribute("user");
	if(user == null){
		response.sendRedirect(request.getContextPath()+"/");
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
    <link href="<%=request.getContextPath()%>/css/fineuploader-3.5.0.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.Jcrop.css" type="text/css" />    
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .qq-upload-list {
        text-align: left;
      }
 
      /* For the bootstrapped demos */
      li.alert-success {
        background-color: #DFF0D8;
      }
 
      li.alert-error {
        background-color: #F2DEDE;
        margin-bottom: 5px;
      }
 
      .alert-error .qq-upload-failed-text {
        display: inline;
      }    

      .box-header{
        padding-bottom: 20px;
        border-bottom: 1px solid #e1e1e1;
      }
      .box{
        padding: 0;
      }
      .box-header, .box-body, .box-footer{
        padding: 20px;
      }
      .item{
        background-color: rgb(230, 230, 230);
        border: none;
        padding-bottom: 0px;
      }

      .qq-upload-button{
        padding-left: 0;
        margin-left: 0;
        float: left;
      }
      .tips{
        display: block;
        padding-top: 6px;
        float: left;
        margin-left: 20px;
      }

      .newalbum{
        font-size: 24px;
        font-family: "Microsoft Yahei", "微软雅黑", Tahoma, Arial;
      }
      .albumdesc{
        width: 98%;
      }
      #avator{

      }
      #avator_pre{
		max-width: none;
      }
      .clearfix:after { content: "."; display: block; clear: both; visibility: hidden; line-height: 0; height: 0; }
      .clearfix { display: inline-block; }
       <font></font>  
      html[xmlns] .clearfix { display: block; }
      * html .clearfix { height: 1%; }       
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
          <div class="box">
            <div class="box-header">
              <span class="newalbum">设置头像</span>
            </div>
            <!-- box header -->
            <form>
            <div class="box-body">
                <div id="imgshow">
					<div class="row">
						<div class="span4">
							<img id="avator" src="<%=request.getContextPath()%>/img/head.jpg" alt="" />
						</div>
						<div class="span2" style="width:100px;height:100px;overflow:hidden;">
							<img id="avator_pre" src="<%=request.getContextPath()%>/img/head.jpg" alt="" />
						</div>
					</div>
          			
                </div>              
				<!--end imgshow  -->
            </div>
            <!-- end box-body -->
            
            <div class="box-footer">
              <div class="newimg">
                  <div id="imgupbtn" class="clearfix"></div>                        
              </div>
              <!-- end newimg -->
              <div class="action">
                <div class="row">
                  <div class="span1"><input type="submit" id="submit" class="btn btn-primary" value="保存修改"> </div>
                </div>
              </div>
              <!-- end action -->

            </div>
            <!-- end box footer -->
          </form>
          </div>
          <!-- end box -->                           
        </div>
        <!-- end span8  -->

        <div class="span4">       
        </div>        
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.fineuploader-3.5.0.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.Jcrop.min.js"></script>
    <script>
    $(document).ready(function() {
    	
    	var jcrop_api,boundx,boundy;
    	var x,y,x2,y2;
    	var w,h;
    	
        $('#imgupbtn').fineUploader({
          request: {
            endpoint: '<%=request.getContextPath()%>/action/imgupload?attr=avator'
          },
          validation: {
            allowedExtensions: ['jpeg', 'jpg', 'png'],
            sizeLimit: 10240000 
          },
          multiple: false,
          text: {
            uploadButton: '<div>选择图片 </div>'
          },
          template: '<div class="qq-uploader clearfix">' +
	          '<pre class="qq-upload-drop-area"><span>{dragZoneText}</span></pre>' +
	          '<div class="qq-upload-button btn btn-success">{uploadButtonText}</div>' +
	          '<div class="tips">1M, jpg, png, jpeg</div>'+
	          '<span class="qq-drop-processing"><span>{dropProcessingText}</span><span class="qq-drop-processing-spinner"></span></span>' +
	          '</div>'+
	          '<div><ul class="qq-upload-list"></ul></div>',
          classes: {
            success: 'alert alert-success',
            fail: 'alert alert-error'
          }
        }).on('complete', function(event, id, fileName, responseJSON) {
          if (responseJSON.success) {
        	if(jcrop_api != null)
        		jcrop_api.destroy();
            $('#avator').attr('src', responseJSON.url);
            $('#avator_pre').attr('src', responseJSON.url);    
        	$("#avator").Jcrop({
        		onChange:updatePreview,
        		onSelect:updatePreview,
        		aspectRatio:1
        	},function(){
        		var bounds=this.getBounds();
        		boundx=bounds[0];
        		boundy=bounds[1];
        		jcrop_api=this;
        	});
          }
        });

    	function updatePreview(c){
    		if(parseInt(c.w)>0){
    			var rx=100/c.w;
    			var ry=100/c.h;
    			x1=c.x;
    			y1=c.y;
    			x2=c.x2;
    			y2=c.y2;
    			width=c.w;
    			height=c.h;
    			
    			$("#avator_pre").css({
    				width:Math.round(rx*boundx)+"px",
    				height:Math.round(ry*boundy)+"px",
    				marginLeft:"-"+Math.round(rx*c.x)+"px",
    				marginTop:"-"+Math.round(ry*c.y)+"px"
    			});
    		};
    	};
        
        
        $('#submit').click(function(){      	
		  var data = "{\"x1\":"+x1+",\"y1\":"+y1+",\"x2\":"+x2+",\"y2\":"+y2+
			  ",\"width\":"+width+",\"height\":"+height
			  +"}";
			
          $.post('<%=request.getContextPath()%>/action/avator', data, function(data){
        	  if(data=='success'){
        		  window.location.href='<%=request.getContextPath()%>/account/';
        	  }else{
        		  
        	  }
        	  
          });
          return false;
        });

      });
    </script>
  </body>
</body>