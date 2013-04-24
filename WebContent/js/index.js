$(document).ready(function(){
	$('.liketag').click(function(){
		var action;
		var link = $(this);
		if($(link).hasClass('liked')){
			action = 'dislike';
		}
		else{
			action = 'like';
		}
		$.get($(this).attr('href')+'&action='+action,function(data){
			if(data == 'success'){
				if(action == 'like'){
					$(link).addClass('liked');	
				}
				else{
					$(link).removeClass('liked');
				}
			}
		});
		return false;
	});
});