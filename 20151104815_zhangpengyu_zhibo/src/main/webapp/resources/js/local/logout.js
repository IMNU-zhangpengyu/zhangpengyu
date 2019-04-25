$(function(){
	$('#log-out').click(function(){
		$.ajax({
			url : "/20151104815_zhangpengyu_zhibo/local/logout",
			type : "post",
			async : false,
			cache : false,
			dataType : 'json',
			success : function(data){
				if(data.success){
					var usertype = $("#log-out").attr("usertype");
					window.location.href = "/20151104815_zhangpengyu_zhibo/local/login?usertype="+usertype;
					return false;
				}
			},
			error : function(data,error){
				alert(error);
			}
		});
	});
});