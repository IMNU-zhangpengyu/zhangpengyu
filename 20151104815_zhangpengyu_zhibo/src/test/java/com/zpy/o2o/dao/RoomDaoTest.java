package com.zpy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zpy.o2o.BaseTest;
import com.zpy.o2o.entity.Area;
import com.zpy.o2o.entity.PersonInfo;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.entity.RoomCategory;

public class RoomDaoTest extends BaseTest{
	@Autowired
	private RoomDao roomDao;
	
	@Test
	public void testQueryRoomListAndCount() {
		Room roomCondition = new Room();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		roomCondition.setOwner(owner);
		List<Room> roomList = roomDao.queryRoomList(roomCondition, 0, 2);
		int count = roomDao.queryRoomCount(roomCondition);
		System.out.println("大小: " + roomList.size() + "  总数：" + count);
		RoomCategory sc = new RoomCategory();
		sc.setRoomCategoryId(1L);
		roomCondition.setRoomCategory(sc);
		roomList = roomDao.queryRoomList(roomCondition, 0, 2);
		count = roomDao.queryRoomCount(roomCondition);
		System.out.println("大小: " + roomList.size() + "  总数：" + count);
	}
	
	@Test
	@Ignore
	public void testQueryByRoomId() {
		long roomId = 1L;
		Room room = roomDao.queryByRoomId(roomId);
		System.out.println(room.getArea().getAreaId());
		System.out.println(room.getArea().getAreaName());
	}
	
	@Test
	@Ignore
	public void testInsertRoom() {
		Room room = new Room();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		RoomCategory roomCategory = new RoomCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		roomCategory.setRoomCategoryId(1L);
		room.setOwner(owner);
		room.setArea(area);
		room.setRoomCategory(roomCategory);
		room.setRoomName("测试的房间");
		room.setRoomDesc("test");
		room.setRoomAddr("test");
		room.setPhone("123");
		room.setRoomImg("test");
		room.setCreateTime(new Date());
		room.setEnableStatus(1);
		room.setAdvice("审核中");
		int effectedNum = roomDao.insertRoom(room);
		assertEquals(1,effectedNum);
	}
	@Test
	@Ignore
	public void testUpdateRoom() {
		Room room = new Room();
		room.setRoomId(1L);
		room.setRoomDesc("测试描述");
		room.setRoomAddr("测试地址");
		room.setLastEditTime(new Date());
		int effectedNum = roomDao.updateRoom(room);
		assertEquals(1,effectedNum);
	}
}
