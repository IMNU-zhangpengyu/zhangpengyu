$(function() {
	getlist();
	function getlist(e) {
		$.ajax({
			url : "/20151104815_zhangpengyu_zhibo/roomadmin/getroomlist",
			type : "get",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					handleList(data.roomList);
					handleUser(data.user);
				}
			}
		});
	}
	function handleUser(data){
		$('#user-name').text(data.name);
	}
	function handleList(data){
		var html = '';
		data.map(function(item,index){
			html +='<div class="row row-room"><div class="col-40">'
				+ item.roomName + '</div><div class="col-40">'
				+ roomStatus(item.enableStatus)
				+ '</div><div class="col-20">'
				+ goRoom(item.enableStatus,item.roomId) + '</div></div>';
		});
		$('.room-wrap').html(html);
	}
	function roomStatus(status){
		if(status == 0){
			return '审核中';
		}else if(status == -1){
			return '房间非法';
		}else if(status == 1){
			return '审核通过';
		}
	}
	function goRoom(status,id){
		if(status == 1){
			return '<a href="/20151104815_zhangpengyu_zhibo/roomadmin/roommanagement?roomId='+ id +'">进入</a>';
		}else{
			return '';
		}
	}
});