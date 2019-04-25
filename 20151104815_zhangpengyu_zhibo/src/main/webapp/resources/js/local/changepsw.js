$(function() {
	var url = '/20151104815_zhangpengyu_zhibo/local/changelocalpwd';
	var usertype = getQueryString('usertype');
	$('#submit').click(function() {
		var userName = $('#userName').val();
		var password = $('#password').val();
		var newPassword = $('#newPassword').val();
		var confirmPassword = $('#confirmPassword').val();
		if(newPassword != confirmPassword){
			$.toast('两次输入新密码不一致');
			return;
		}
		//添加表单数据
		var formData = new FormData();
		formData.append('userName', userName);
		formData.append('password', password);
		formData.append('newPassword', newPassword);
		var verifyCodeActual = $('#j_captcha').val();
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		formData.append("verifyCodeActual", verifyCodeActual);
		$.ajax({
			url : url,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
					if(usertype == 1){
						window.location.href = '/20151104815_zhangpengyu_zhibo/fromtend/index';
					}else{
						window.location.href = '/20151104815_zhangpengyu_zhibo/roomadmin/roomlist';
					}
				} else {
					$.toast('账号或密码输入错误');
					$('#captcha_img').click();
				}
			}
		});
	});

	$('#back').click(function() {
		window.location.href = '/20151104815_zhangpengyu_zhibo/roomadmin/roomlist';
	});
});
