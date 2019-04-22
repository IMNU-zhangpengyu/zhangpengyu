package com.zpy.o2o.dto;

import java.util.List;

import com.zpy.o2o.entity.Room;
import com.zpy.o2o.enums.RoomStateEnum;

public class RoomExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 数量
	private int count;

	// 操作的room
	private Room room;

	// room列表
	private List<Room> roomList;

	public RoomExecution() {

	}

	// 失败构造器
	public RoomExecution(RoomStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功构造器
	public RoomExecution(RoomStateEnum stateEnum, Room room) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.room = room;
	}

	// 成功构造器
	public RoomExecution(RoomStateEnum stateEnum,List<Room> roomList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.roomList = roomList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}
}
