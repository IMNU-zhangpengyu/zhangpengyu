package com.zpy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zpy.o2o.BaseTest;
import com.zpy.o2o.dto.ImageHolder;
import com.zpy.o2o.dto.RoomExecution;
import com.zpy.o2o.entity.Area;
import com.zpy.o2o.entity.PersonInfo;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.entity.RoomCategory;
import com.zpy.o2o.enums.RoomStateEnum;
import com.zpy.o2o.exceptions.RoomOperationException;

public class RoomServiceTest extends BaseTest{
	@Autowired
	private RoomService roomService;
	
	@Test
	public void testGetRoomList() {
		Room roomCondition = new Room();
		RoomCategory sc = new RoomCategory();
		sc.setRoomCategoryId(1L);
		roomCondition.setRoomCategory(sc);
		RoomExecution se = roomService.getRoomList(roomCondition, 2, 2);
		System.out.println("列表数: " + se.getRoomList().size() + "  总数：" + se.getCount());
	}
	
	
	@Test
	@Ignore
	public void testModifyRoom() throws RoomOperationException,FileNotFoundException{
		Room room = new Room();
		room.setRoomId(2L);
		room.setRoomName("修改后的房间名");
		File roomImg = new File("D:/git_work/zhangpengyu/temp/3.jpg");
		InputStream is = new FileInputStream(roomImg);
		ImageHolder imageHolder = new ImageHolder("3.jpg",is);
		RoomExecution roomExecution = roomService.modifyRoom(room, imageHolder );
		System.out.println(roomExecution.getRoom().getRoomImg());
	}
	
	@Test
	@Ignore
	public void testAddRoom() throws RoomOperationException,FileNotFoundException {
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
		room.setRoomName("测试的房间3");
		room.setRoomDesc("test3");
		room.setRoomAddr("test3");
		room.setPhone("1233");
		room.setCreateTime(new Date());
		room.setEnableStatus(RoomStateEnum.CHECK.getState());
		room.setAdvice("审核中");
		File roomImg = new File("D:/git_work/zhangpengyu/temp/2.jpg");
		InputStream is = new FileInputStream(roomImg);
		ImageHolder imageHolder = new ImageHolder(roomImg.getName(),is);
		RoomExecution se = roomService.addRoom(room,imageHolder);
		assertEquals(RoomStateEnum.CHECK.getState(),se.getState());
	}
}
