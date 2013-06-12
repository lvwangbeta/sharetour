<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="<%=request.getContextPath()%>/">享途</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="<%=request.getContextPath()%>/">Home</a></li>
              <li><a href="<%=request.getContextPath()%>/about">About</a></li>
            </ul>
            <c:choose>
            	<c:when test="${sessionScope.user == null }">
					<form class="navbar-form pull-right" method="post" action="<%=request.getContextPath()%>/action/login">
		              <input class="span2" type="text" name="username" placeholder="用户名">
		              <input class="span2" type="password" name="password" placeholder="密码">
		              <button type="submit" class="btn btn-success">登录</button>
		              <a class="btn" href="<%=request.getContextPath()%>/register">注册</a>
		            </form>            		
            	</c:when>
            	<c:otherwise>
		            <ul class="nav pull-right">
		              <li class="dropdown active">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.username} <b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                  <li><a href="<%=request.getContextPath()%>/u/space">我的空间</a></li>
		                  <li><a href="<%=request.getContextPath()%>/newpost">写新游记</a></li>
                  		  <li><a href="#">消息 <span class="badge badge-important">6</span></a></li>		                  
		                  <li class="divider"></li>
		                  <li><a href="<%=request.getContextPath()%>/account/">账户信息</a></li>
		                  <li><a href="<%=request.getContextPath()%>/action/logout">退出</a></li>
		                </ul>
		              </li>
		            </ul>           	
            	</c:otherwise>
            </c:choose>

          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div> <!-- end nav bar -->