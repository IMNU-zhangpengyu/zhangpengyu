package com.zpy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zpy.o2o.entity.Room;

public interface RoomDao {
	/**
	 * 分页查询，模糊查询
	 * @param roomCondition
	 * @param rowIndex 从第几行开始
	 * @param pageSize 几条
	 * @return
	 */
	List<Room> queryRoomList(@Param("roomCondition")Room roomCondition,
			@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	
	/**
	 * 返回queryRoomList总数
	 * @param roomCondition
	 * @return
	 */
	int queryRoomCount(@Param("roomCondition")Room roomCondition);
	
	/**
	 * 通过id查找房间
	 * @param room
	 * @return
	 */
	Room queryByRoomId(long roomId);
	
	/**
	 * 新增房间
	 * @param room
	 * @return
	 */
	int insertRoom(Room room);
	
	/**
	 * 更新房间
	 * @param room
	 * @return
	 */
	int updateRoom(Room room);
}
