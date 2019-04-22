/**
 * 
 */
$(function() {
	var roomId = getQueryString('roomId');
	var isEdit = roomId?true : false;
	var initUrl = '/20151104815_zhangpengyu_zhibo/roomadmin/getroominitinfo';
	var registerRoomUrl = '/20151104815_zhangpengyu_zhibo/roomadmin/registerroom';
	var roomInfoUrl = "/20151104815_zhangpengyu_zhibo/roomadmin/getroombyid?roomId=" + roomId;
	var editRoomUrl = '/20151104815_zhangpengyu_zhibo/roomadmin/modifyroom';
	if(!isEdit){
		getRoomInitInfo();
	}else{
		getRoomInfo(roomId);
	}
	

	function getRoomInfo(roomId) {
		$.getJSON(roomInfoUrl, function(data) {
			if (data.success) {
				var room = data.room;
				$('#room-name').val(room.roomName);
				$('#room-addr').val(room.roomAddr);
				$('#room-phone').val(room.phone);
				$('#room-desc').val(room.roomDesc);
				var roomCategory = '<option data-id="'
						+ room.roomCategory.roomCategoryId + '"selected>'
						+ room.roomCategory.roomCategoryName + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#room-category').html(roomCategory);
				$('#room-category').attr('disabled', 'disabled');
				$('#area').html(tempAreaHtml);
				$("#area option[data-id='"+room.area.areaId+"']").attr("selected","selected");
			}
		});
	}

	function getRoomInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.roomCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.roomCategoryId
							+ '">' + item.roomCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#room-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}
		// 提交按钮room注册页面的信息
		$('#submit').click(
				function() {
					var room = {};
					if(isEdit){
						room.roomId = roomId;
					}
					room.roomName = $('#room-name').val();
					room.roomAddr = $('#room-addr').val();
					room.phone = $('#room-phone').val();
					room.roomDesc = $('#room-desc').val();
					room.roomCategory = {
						roomCategoryId : $('#room-category').find('option')
								.not(function() {
									return !this.selected;
								}).data('id')
					};
					room.area = {
						areaId : $('#area').find('option').not(function() {
							return !this.selected;
						}).data('id')
					};
					var roomImg = $('#room-img')[0].files[0];
					var formData = new FormData();
					formData.append('roomImg', roomImg);
					formData.append('roomStr', JSON.stringify(room));
					// 验证码
					var verifyCodeActual = $('#j_captcha').val();
					if (!verifyCodeActual) {
						$.toast('请输入验证码！');
						return;
					}
					formData.append('verifyCodeActual', verifyCodeActual);
					$.ajax({
						url : (isEdit ? editRoomUrl : registerRoomUrl),
						type : 'POST',
						data : formData,
						contentType : false,
						processData : false,
						cache : false,
						success : function(data) {
							if (data.success) {
								$.toast('提交成功！');
							} else {
								$.toast('提交失败！' + data.errMsg);
							}
							// 点击更换
							$('#captcha_img').click();
						}
					});

				});
	
});