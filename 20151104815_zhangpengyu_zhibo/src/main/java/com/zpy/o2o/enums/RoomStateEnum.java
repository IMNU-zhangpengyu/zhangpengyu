package com.zpy.o2o.enums;

public enum RoomStateEnum {
	CHECK(0,"审核中"),OFFLINE(-1,"非法房间"),SUCCESS(1,"操作成功"),
	PASS(2,"通过认证"),INNER_ERROR(-1001,"内部系统错误"),
	NULL_ROOMID(-1002,"RoomId为空"),NULL_ROOM(-1003,"room信息为空");
	
	private int state;
	private String stateInfo;
	
	private RoomStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	/**
	 * 根据state返回enum值
	 */
	public static RoomStateEnum stateOf(int state) {
		for(RoomStateEnum stateEnum:values()) {
			if(stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
}
