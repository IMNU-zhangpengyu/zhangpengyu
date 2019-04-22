$(function(){
	var roomId = getQueryString('roomId');
	var roomInfoUrl = '/20151104815_zhangpengyu_zhibo/roomadmin/getroommanagementinfo?roomId=' + roomId;
	$.getJSON(roomInfoUrl,function(data){
		if(data.redirect){
			window.location.href = data.url;
		}else{
			if(data.roomId != undefined && data.roomId != null){
				roomId = data.roomId;
			}
			$('#roomInfo')
				.attr('href','/20151104815_zhangpengyu_zhibo/roomadmin/roomoperation?roomId=' + roomId);
		}
	});
});