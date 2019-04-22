package com.zpy.o2o.service;

import java.util.List;

import com.zpy.o2o.entity.RoomCategory;

public interface RoomCategoryService {
	List<RoomCategory> getRoomCategoryList(RoomCategory roomCategoryCondition);
}
