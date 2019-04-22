package com.zpy.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpy.o2o.dao.RoomCategoryDao;
import com.zpy.o2o.entity.RoomCategory;
import com.zpy.o2o.service.RoomCategoryService;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService{

	@Autowired
	private RoomCategoryDao roomCategoryDao;
	@Override
	public List<RoomCategory> getRoomCategoryList(RoomCategory roomCategoryCondition) {
		
		return roomCategoryDao.queryRoomCategory(roomCategoryCondition);
	}

}
