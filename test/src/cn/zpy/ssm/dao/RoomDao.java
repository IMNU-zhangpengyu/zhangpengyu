package cn.zpy.ssm.dao;

import cn.zpy.ssm.po.Room;

public interface RoomDao {
	/*
	 * 新增房间
	 */
	int insertRoom(Room room);
	
	/*
	 * 更新
	 */
	int updateRoom(Room room);
}
