<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sharetour.model.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	if(session.getAttribute("user") != null)
		response.sendRedirect(request.getContextPath()+"/u/space");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ShareTour</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="register">
    <meta name="author" content="gavin">

    <!-- Le styles -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      label.error{
      	display: inline;
      	color: red;
      	margin-left: 10px;
      }
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
		              <button type="submit" class="btn">登录</button>
		            </form>            		
            	</c:when>
            	<c:otherwise>
		            <ul class="nav pull-right">
		              <li class="dropdown active">
		                <a href="<%=request.getContextPath()%>#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.username} <b class="caret"></b></a>
		                <ul class="dropdown-menu">
		                  <li><a href="<%=request.getContextPath()%>/u/space">我的空间</a></li>
		                  <li><a href="<%=request.getContextPath()%>/newpost">写新游记</a></li>
                  		  <li><a href="<%=request.getContextPath()%>#">消息 <span class="badge badge-important">6</span></a></li>		                  
		                  <li class="divider"></li>
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
    
    
	<div class="container">
		<form id="regiform" action="<%=request.getContextPath()%>/action/register" method="post">
		  <fieldset>
		    <legend>注册</legend>
		    <label for="email">Email</label>
		    <input type="text" name="email" id="email" class="span3" placeholder="email">
		    <label for="username">用户名</label>
		    <input type="text" name="username" id="username" class="span3" placeholder="username">
		    <label for="password">密码</label>
		    <input type="password" name="password" id="password" class="span3" placeholder="password">
		    <label for="confirm">确认密码</label>
		    <input type="password" name="confirm" id="confirm" class="span3" placeholder="confirm">
		    <label>性别</label>
		    <label for="male" class="radio inline">
		    	<input type="radio" id="male" name="gender" value="0" checked>男
		    </label>
		    <label for="female" class="radio inline">
		    	<input type="radio" id="female" name="gender" value="1">女
		    </label>
		    <label for="year">生日</label>
			<select name="year" id="year" class="span1">
				<option value="" disabled="">年</option>
				<option value="2013">2013</option>
				<option value="2012">2012</option>
				<option value="2011">2011</option>
				<option value="2010">2010</option>
				<option value="2009">2009</option>
				<option value="2008">2008</option>
				<option value="2007">2007</option>
				<option value="2006">2006</option>
				<option value="2005">2005</option>
				<option value="2004">2004</option>
				<option value="2003">2003</option>
				<option value="2002">2002</option>
				<option value="2001">2001</option>
				<option value="2000">2000</option>
				<option value="1999">1999</option>
				<option value="1998">1998</option>
				<option value="1997">1997</option>
				<option value="1996">1996</option>
				<option value="1995">1995</option>
				<option value="1994">1994</option>
				<option value="1993">1993</option>
				<option value="1992">1992</option>
				<option value="1991">1991</option>
				<option value="1990">1990</option>
				<option value="1989">1989</option>
				<option value="1988">1988</option>
				<option value="1987">1987</option>
				<option value="1986">1986</option>
				<option value="1985">1985</option>
				<option value="1984">1984</option>
				<option value="1983">1983</option>
				<option value="1982">1982</option>
				<option value="1981">1981</option>
				<option value="1980">1980</option>
				<option value="1979">1979</option>
				<option value="1978">1978</option>
				<option value="1977">1977</option>
				<option value="1976">1976</option>
				<option value="1975">1975</option>
				<option value="1974">1974</option>
				<option value="1973">1973</option>
				<option value="1972">1972</option>
				<option value="1971">1971</option>
				<option value="1970">1970</option>
				<option value="1969">1969</option>
				<option value="1968">1968</option>
				<option value="1967">1967</option>
				<option value="1966">1966</option>
				<option value="1965">1965</option>
				<option value="1964">1964</option>
				<option value="1963">1963</option>
				<option value="1962">1962</option>
				<option value="1961">1961</option>
				<option value="1960">1960</option>
				<option value="1959">1959</option>
				<option value="1958">1958</option>
				<option value="1957">1957</option>
				<option value="1956">1956</option>
				<option value="1955">1955</option>
				<option value="1954">1954</option>
				<option value="1953">1953</option>
				<option value="1952">1952</option>
				<option value="1951">1951</option>
				<option value="1950">1950</option>
				<option value="1949">1949</option>
				<option value="1948">1948</option>
				<option value="1947">1947</option>
				<option value="1946">1946</option>
				<option value="1945">1945</option>
				<option value="1944">1944</option>
				<option value="1943">1943</option>
				<option value="1942">1942</option>
				<option value="1941">1941</option>
				<option value="1940">1940</option>
				<option value="1939">1939</option>
				<option value="1938">1938</option>
				<option value="1937">1937</option>
				<option value="1936">1936</option>
				<option value="1935">1935</option>
				<option value="1934">1934</option>
				<option value="1933">1933</option>
				<option value="1932">1932</option>
				<option value="1931">1931</option>
				<option value="1930">1930</option>
				<option value="1929">1929</option>
				<option value="1928">1928</option>
				<option value="1927">1927</option>
				<option value="1926">1926</option>
				<option value="1925">1925</option>
				<option value="1924">1924</option>
				<option value="1923">1923</option>
				<option value="1922">1922</option>
				<option value="1921">1921</option>
				<option value="1920">1920</option>
				<option value="1919">1919</option>
				<option value="1918">1918</option>
				<option value="1917">1917</option>
				<option value="1916">1916</option>
				<option value="1915">1915</option>
				<option value="1914">1914</option>
				<option value="1913">1913</option>
			</select>
			<select name="month" id="month" class="span2">
				<option value="" disabled="">月份</option>
				<option value="1">1月</option>
				<option value="2">2月</option>
				<option value="3">3月</option>
				<option value="4">4月</option>
				<option value="5">5月</option>
				<option value="6">6月</option>
				<option value="7">7月</option>
				<option value="8">8月</option>
				<option value="9">9月</option>
				<option value="10">10月</option>
				<option value="11">11月</option>
				<option value="12">12月</option>
			</select> <!-- end month select -->
			<select name="day" id="day" class="span1">
				<option value="" disabled="">日</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
				<option value="21">21</option>
				<option value="22">22</option>
				<option value="23">23</option>
				<option value="24">24</option>
				<option value="25">25</option>
				<option value="26">26</option>
				<option value="27">27</option>
				<option value="28">28</option>
				<option value="29">29</option>
				<option value="30">30</option>
				<option value="31">31</option>
			</select>
			<label></label>
		    <button class="btn btn-success" type="submit" id="submit" class="btn">注册</button>
		  </fieldset>
		</form>				
	</div> <!-- end contqiner -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
	<script type="text/javascript">
	$("#regiform").validate({
			rules: {
				username: {
					required: true,
					minlength: 5
				},
				password: {
					required: true,
					minlength: 5
				},
				confirm: {
					required: true,
					minlength: 5,
					equalTo: "#password"
				},
				email: {
					required: true,
					email: true
				}
			},
			messages: {
				username: {
					required: "请输入用户名",
					minlength: "用户名长度至少大于5"
				},
				password: {
					required: "请输入密码",
					minlength: "密码至少5位"
				},
				confirm: {
					required: "请确认密码",
					minlength: "确认密码至少5位",
					equalTo: "密码不一致，请确认"
				},
				email: "请输入合法的email地址",
				agree: "Please accept our policy"
			}
		});
	</script>	
</body>
</html>