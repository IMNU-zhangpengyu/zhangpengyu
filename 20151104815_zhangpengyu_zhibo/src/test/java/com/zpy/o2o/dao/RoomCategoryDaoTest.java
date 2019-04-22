package com.zpy.o2o.dao;

//import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zpy.o2o.BaseTest;
import com.zpy.o2o.entity.RoomCategory;

public class RoomCategoryDaoTest extends BaseTest{
	@Autowired
	private RoomCategoryDao roomCategoryDao;
	
	@Test
	public void testQueryRoomCategory(){
//		List<RoomCategory> roomCategoryList = roomCategoryDao.queryRoomCategory(new RoomCategory());
//		assertEquals(2,roomCategoryList.size());
//		RoomCategory testCategory = new RoomCategory();
//		RoomCategory parentCategory = new RoomCategory();
//		parentCategory.setRoomCategoryId(1L);
//		testCategory.setParent(parentCategory);
//		roomCategoryList = roomCategoryDao.queryRoomCategory(testCategory);
//		assertEquals(1,roomCategoryList.size());
//		System.out.println(roomCategoryList.get(0).getRoomCategoryName());
		List<RoomCategory> roomCategoryList = roomCategoryDao.queryRoomCategory(null);
		System.out.println(roomCategoryList.size());
	}
}
