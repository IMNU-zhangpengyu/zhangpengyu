package com.zpy.o2o.entity;

import java.util.Date;

public class RoomCategory {
	private Long roomCategoryId;
	private String roomCategoryName;
	private String roomCategoryDesc;
	private String roomCategoryImg;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	private RoomCategory parent;
	
	public Long getRoomCategoryId() {
		return roomCategoryId;
	}
	public void setRoomCategoryId(Long roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}
	public String getRoomCategoryName() {
		return roomCategoryName;
	}
	public void setRoomCategoryName(String roomCategoryName) {
		this.roomCategoryName = roomCategoryName;
	}
	public String getRoomCategoryDesc() {
		return roomCategoryDesc;
	}
	public void setRoomCategoryDesc(String roomCategoryDesc) {
		this.roomCategoryDesc = roomCategoryDesc;
	}
	public String getRoomCategoryImg() {
		return roomCategoryImg;
	}
	public void setRoomCategoryImg(String roomCategoryImg) {
		this.roomCategoryImg = roomCategoryImg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public RoomCategory getParent() {
		return parent;
	}
	public void setParent(RoomCategory parent) {
		this.parent = parent;
	}
}
