package cn.zpy.ssm.service;

import java.io.File;

import cn.zpy.ssm.dto.RoomExecution;
import cn.zpy.ssm.po.Room;

public interface RoomService {
	RoomExecution addRoom(Room room,File roomimg);
}
