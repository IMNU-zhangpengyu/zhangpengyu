package com.zpy.o2o.service;

import com.zpy.o2o.dto.ImageHolder;
import com.zpy.o2o.dto.RoomExecution;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.exceptions.RoomOperationException;

public interface RoomService {
	/**
	 * 根据rommComdition分页返回相应列表
	 * @param rommComdition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public RoomExecution getRoomList(Room roomCondition,int pageIndex,int pageSize);
	
	/**
	 *通过Id获取房间信息 
	 */
	Room getByRoomId(long roomId);
	
	/**
	 * 更新房间信息，包括图片
	 */
	RoomExecution modifyRoom(Room room,ImageHolder thumbnail)throws RoomOperationException;

	/**
	 * 新建房间
	 */
	RoomExecution addRoom(Room room, ImageHolder thumbnail)throws RoomOperationException;

}
