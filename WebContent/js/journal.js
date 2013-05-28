$(document).ready(function(){	
	$('#submit').bind('click', function(){
		var commentVal = $('#comment').val();
		var pidVal = $('#pid').val();

		if(commentVal == '')
		{
			alert('评论不能为空');
			return false;
		}
		$.post('/comment', 
			{
				comment:commentVal,
				pid:pidVal
			}, function(data){
				if(data == '-1')
				{
					alert('请先登录');
					return false;
				}
				else if(data != '0')
				{
					var username = $('#username').val();
					var $newcomment = $('<div><div class="head-img span1">'+
										'<img src="/img/head.jpg" class="img-rounded"></div>'+
										'<div class="span11"><p>'+username+'评论：</p>'+
										'<p>'+commentVal+'</p></div></div><br>');
					$('#comment-list').append($newcomment);
					$('#comment').val('');
				}

			});
		return false;
	});


});