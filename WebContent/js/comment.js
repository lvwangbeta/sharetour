$(document).ready(function(){
	$('#submit').bind('click', function(){
		var MAX_LIMIT = 140;
		var postid = $('#postid').val();
		var content = $('#content').val();
		if(content.length == 0 || content == null){
			$('#tip').text('评论不能为空');
			$('#tip').removeClass('warningblock');
			return false;
		}
		else if(content.length > 140){
			$('#tip').text('只能评论140字哦~');
			$('#tip').removeClass('warningblock');
			return false;
		}
		$('#submit').addClass('disabled');
		$('#tip').addClass('warningblock');
		$.post('/action/comment', 
			{'postid': postid, 'content': content},
			function(data){
			if(data == 'success'){
				var username = $('#username').val();
				var d = new Date();
				var datetime = d.getFullYear()+'-'+d.getMonth()+'-'+d.getDay()+' '+d.getHours()+':'+d.getMinutes();
				var comment = '<li><div><p><i class="icon-user"></i><a href="#">'+username+'</a> '+
							  '&nbsp;<i class="icon-calendar"></i>'+datetime+'</p><p>'+content +
							  '</p></div></li>';
				$('#comments-list').append(comment);
				$('#content').val('');				
			}
			else if(data == 'nologin'){
				$('#tip').text('请先登录');
				$('#tip').removeClass('warningblock');
				return false;
			}
		});
		$('#submit').removeClass('disabled');
		return false;
	});
});