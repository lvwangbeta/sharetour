<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.sharetour.model.*, com.sharetour.service.AlbumService" %>
<%
	request.setCharacterEncoding("utf-8");
	String albumId = request.getParameter("p1");
	AlbumService albumService = new AlbumService();
	Album album = albumService.getAlbum(albumId);
	if(album != null){
		List<Photo> photos = album.getPhotos();
		if(photos != null){
%>
<div id="custom-content" class="white-popup-block" style="max-width:500px; margin: 20px auto;">
    <h2><%=album.getAlbumname() %></h2>
    <style>
    #custom-content img {max-width: 100%;margin-bottom: 10px;}
    </style>
    <%
    	for(Photo photo: photos){
    %>
    	<div class="item" style="display:block">
	    	<img src="/imgs?id=<%=photo.getId()%>" alt="" />
	    	<p><%=photo.getDesc() %></p>
    	</div>
    <%
    	}
    %>
</div>
<%}}%>