$(document).ready(function(){
	$('#submit').bind('click', function(){
		var titleVal = $('#title').val();
		var summaryVal = $('#summary').val();
		var contentVal = CKEDITOR.instances.content.getData();
		if(titleVal == '')
		{
			alert('标题不能为空');
			return false;
		}
		if(summaryVal == '')
		{
			alert('摘要不能为空');
			return false;
		}
		if(contentVal == '')
		{
			alert('内容不能为空');
			return false;
		}
	});


});