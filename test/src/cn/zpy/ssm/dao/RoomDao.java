package cn.zpy.ssm.dao;

import cn.zpy.ssm.po.Room;

public interface RoomDao {
	/*
	 * ��������
	 */
	int insertRoom(Room room);
	
	/*
	 * ����
	 */
	int updateRoom(Room room);
}
