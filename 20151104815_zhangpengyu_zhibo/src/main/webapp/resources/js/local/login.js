$(function(){
	var loginUrl = '/20151104815_zhangpengyu_zhibo/local/logincheck';
	var usertype = getQueryString('usertype');
	var loginCount = 0;
	
	$('#submit').click(function(){
		var userName = $('#userName').val();
		var password = $('#psw').val();
		var verifyCodeActual = $('#j_captcha').val();
		var needVerify = false;
		if(loginCount >=3){
			if(!verifyCodeActual){
				$.toast('请输入验证码！');
				return;
			}else{
				needVerify = true;
			}
		}
		$.ajax({
			url : loginUrl,
			async : false,
			cache : false,
			type : "post",
			dataType : 'json',
			data :{
				userName : userName,
				password : password,
				verifyCodeActual : verifyCodeActual,
				needVerify : needVerify
			},
			success : function(data){
				if(data.success){
					$.toast('登陆成功');
					if(usertype == 1){
						window.location.href = '/20151104815_zhangpengyu_zhibo/NewFile.jsp';
					}else{
						window.location.href = '/20151104815_zhangpengyu_zhibo/roomadmin/roomlist';
					}
				}else{
					$.toast('登陆失败'+ data.errMsg);
					loginCount++;
					if(loginCount >=3){
						$('#verifyPart').show();
					}
				}
			}
		});
	});
});