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

    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/fineuploader-3.5.0.css" rel="stylesheet">
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
    </style>
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">
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
                  <li><a href="/action/logout">退出</a></li>
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
          <div class="thumbnail">
            <form>
              <legend>相册</legend>
              <div id="imgshow">
                  <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <div class="row">
                    <div class="span2"><img src="../img/head.jpg" style="height:100px;width=100xp" alt=""> </div>
                    <div class="span5"><textarea name="" class="span4" id="" rows="5"></textarea> </div>                              
                    </div>
                </div>                
              </div>

              
              <div class="operate-area">
                <div class="newimg">
                  <div class="row">
                    <div id="imgupbtn"></div>
                    <div class="span3">limit 5M</div> 
                  </div>
                  
                                       
                </div>
                <!-- end newimg -->
                <div class="desc">
                  <textarea class="span7" name="albumdesc" id="albumdesc" rows="8"></textarea>
                </div>
                <!-- end desc -->
                <div class="action">
                </div>
                <!-- end action area -->
              </div>  
              <!-- end opeate-area -->
            </form>                  
          </div>                            
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
    
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script src="/js/jquery.fineuploader-3.5.0.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script>
    $(document).ready(function() {
        $('#imgupbtn').fineUploader({
          request: {
            endpoint: '/action/imgupload?attr=album'
          },
          validation: {
            allowedExtensions: ['jpeg', 'jpg', 'png'],
            sizeLimit: 512000 
          },
          text: {
            uploadButton: '<div><i class="icon-upload icon-white"></i> Test</div>'
          },
          template: '<div class="qq-uploader">' +
                      '<pre class="qq-upload-drop-area"><span>{dragZoneText}</span></pre>' +
                      '<div class="qq-upload-button btn btn-success span2">{uploadButtonText}</div>' +
                      '<span class="qq-drop-processing"><span>{dropProcessingText}</span><span class="qq-drop-processing-spinner"></span></span>' +
                      '<ul class="qq-upload-list span7"></ul>' +
                    '</div>',
          classes: {
            success: 'alert alert-success',
            fail: 'alert alert-error'
          }
        }).on('complete', function(event, id, fileName, responseJSON) {
          if (responseJSON.success) {
            $('.qq-upload-list').hide();
            $('#imgshow').append(
                    '<div class="alert alert-success id="'+responseJSON.id+'">'+
                      '<button type="button" class="close" data-dismiss="alert">&times;</button>'+
                      '<div class="row">'+
                        '<div class="span2">'+
                          '<img src="'+responseJSON.url+'" style="height:100px;width:100xp" alt="">'+
                        '</div>'+
                        '<div class="span5">'+
                          '<textarea name="desc" class="span4" id="" rows="5"></textarea>'+ 
                        '</div>'+
                      '</div>'+
                    '</div>');            
          }else {

          }
          
        });
      });
    </script>
  </body>
</body>