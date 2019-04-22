package com.zpy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zpy.o2o.entity.RoomCategory;

public interface RoomCategoryDao {
	List<RoomCategory> queryRoomCategory(@Param("roomCategoryCondition") RoomCategory roomCategoryCondition);
}
