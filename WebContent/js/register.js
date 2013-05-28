$(document).ready(function(){
	$('#submit').bind('click', function(){
		var usernameVal = $('#username').val();
		var passwordVal = $('#password').val();
		var conformVal = $('#conform').val();
		var emailVal = $('#email').val();

		if(usernameVal == '')
		{
			alert('用户名不能为空');
			return false;
		}
		if(passwordVal == '')
		{
			alert('密码不能为空');
			return false;
		}
		if(conformVal == '')
		{
			alert('请输入确认密码');
			return false;
		}
		if(passwordVal != conformVal)
		{
			alert('两次密码输入不匹配');
			return false;
		}
		if(emailVal == '')
		{
			alert('请输入邮箱');
			return false;
		}

	});


});