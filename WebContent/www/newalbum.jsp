<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="/<%=request.getContextPath()%>">享途</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="<%=request.getContextPath()%>">Home</a></li>
              <li><a href="<%=request.getContextPath()%>/about">About</a></li>
            </ul>
            <ul class="nav pull-right">
              <li class="dropdown active">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.username} <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">${sessionScope.user.email }</a></li>
                  <li><a href="#">${sessionScope.user.birth }</a></li>
                  <li><a href="#">${sessionScope.user.gender }</a></li>
                  <li><a href="#">info <span class="badge badge-important">6</span></a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Nav header</li>
                  <li><a href="<%=request.getContextPath()%>/action/logout">退出</a></li>
                </ul>
              </li>
            </ul>             
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div> <!-- end nav bar -->  
    <!--  begin container  -->
    <div class="container">
      <div class="row">
        <div class="span8">
          <div class="box">
            <div class="box-header">
              <span class="newalbum">新相册</span>
            </div>
            <!-- box header -->
            <form>
            <div class="box-body">
                <label for="albumname">相册名：</label>
                <input type="text" style="width:98%" name="albumname" id="albumname">
                <div id="imgshow">
                    <div class="alert item">
                      <button type="button" class="close" data-dismiss="alert">&times;</button>
                      <div class="row">
                      <div class="span2"><img src="<%=request.getContextPath()%>/img/head.jpg" style="height:100px;width=100xp" alt=""> </div>
                      <div class="span4"><textarea name="" class="span4" id="" rows="4"></textarea> </div>                              
                      </div>
                    </div>            
                </div>              

            </div>
            <!-- end box-body -->
            
            <div class="box-footer">
              <div class="newimg">
                  <div id="imgupbtn" class="clearfix"></div>
                            
              </div>
              <!-- end newimg -->
              <div>
                <label for="albumdesc">描述:</label>
                <textarea class="albumdesc" name="albumdesc" id="albumdesc"  rows="6"></textarea>
              </div>
              <div class="action">
                <div class="row">
                  <div class="span1"><input type="submit" id="submit" class="btn btn-primary" value="发布"> </div>
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
          <div>
            <div class="accordion">
              <div class="accordion-group">
                <div class="accordion-heading">
                  <a class="accordion-toggle" data-toggle="collapse" href="#collapseOne">
                    Collapsible Group Item #1
                  </a>
                </div>
                <div id="collapseOne" class="accordion-body collapse in">
                  <div class="accordion-inner">
                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS
                  </div>
                </div>
              </div>
              <div class="accordion-group">
                <div class="accordion-heading">
                  <div class="accordion-inner">
                    <i class="icon-tag"></i>&nbsp;&nbsp;sdf
                    <a href="#" class="pull-right sub">订阅</a>                                    
                  </div>
                
                </div>
              </div>
              <div class="accordion-group">
                <div class="accordion-heading">
                  <a class="accordion-toggle"  href="/u/space">
                    <i class="icon-user"></i>&nbsp;&nbsp;我的游记
                  </a>
                </div>
              </div>  


            </div>            
          </div>        
        </div>        
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.fineuploader-3.5.0.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script>
    $(document).ready(function() {
        $('#imgupbtn').fineUploader({
          request: {
            endpoint: '<%=request.getContextPath()%>/action/imgupload?attr=album'
          },
          validation: {
            allowedExtensions: ['jpeg', 'jpg', 'png'],
            sizeLimit: 512000 
          },
          multiple: false,
          text: {
            uploadButton: '<div><i class="icon-upload icon-white"></i> Test</div>'
          },
          template: '<div class="qq-uploader clearfix">' +
	          '<pre class="qq-upload-drop-area"><span>{dragZoneText}</span></pre>' +
	          '<div class="qq-upload-button btn btn-success">{uploadButtonText}</div>' +
	          '<div class="tips">5M, jpg, png, jpeg</div>'+
	          '<span class="qq-drop-processing"><span>{dropProcessingText}</span><span class="qq-drop-processing-spinner"></span></span>' +
	          '</div>'+
	          '<div><ul class="qq-upload-list"></ul></div>',
          classes: {
            success: 'alert alert-success',
            fail: 'alert alert-error'
          }
        }).on('complete', function(event, id, fileName, responseJSON) {
          if (responseJSON.success) {
            $('#imgshow').append(
                    '<div class="alert item" id="'+responseJSON.id+'">'+
                      '<button type="button" class="close" data-dismiss="alert">&times;</button>'+
                      '<div class="row">'+
                        '<div class="span2">'+
                          '<img src="<%=request.getContextPath()%>'+responseJSON.url+'" style="height:100px;width:100xp" alt="">'+
                        '</div>'+
                        '<div class="span4">'+
                          '<textarea name="desc" id="'+responseJSON.id+"-desc"+'" class="span4 photodesc" rows="5"></textarea>'+ 
                        '</div>'+
                      '</div>'+
                    '</div>');            
          }
        });


        $('#submit').click(function(){
          var data = '{"albumname":';
          var albumname = $('#albumname').val();
          data = data+'"'+albumname+'",';
          data += '"photos":['
          $('.item').each(function(index){
            var id=$(this).attr('id');
            var photodesc=$('#'+id+'-desc').val();
            if(index != 0)
              data+=','
            data+='{'
            data+='"id":';
            data = data+'"'+id+'","desc":'
            data = data+'"'+photodesc+'"';
            data+='}'
          });
          data += '],'
          var albumdesc = $('#albumdesc').val();
          data += '"desc":';
          data = data + '"' + albumdesc + '"}'
          alert(data);

          $.post('<%=request.getContextPath()%>/action/albumup', data, function(data){
        	  if(data=='success'){
        		  window.location.href='<%=request.getContextPath()%>/u/space';
        	  }else{
        		  
        	  }
        	  
          });
          return false;
        });

      });
    </script>
  </body>
</body>