package com.zpy.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpy.o2o.dto.RoomExecution;
import com.zpy.o2o.entity.Area;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.entity.RoomCategory;
import com.zpy.o2o.service.AreaService;
import com.zpy.o2o.service.RoomCategoryService;
import com.zpy.o2o.service.RoomService;
import com.zpy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class RoomListController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private RoomCategoryService roomCategoryService;
	@Autowired
	private RoomService roomService;

	@RequestMapping(value = "/listroomspageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listRoomsPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<RoomCategory> roomCategoryList = null;
		if (parentId != -1) {
			try {
				RoomCategory roomCategoryCondition = new RoomCategory();
				RoomCategory parent = new RoomCategory();
				parent.setRoomCategoryId(parentId);
				roomCategoryCondition.setParent(parent);
				roomCategoryList = roomCategoryService.getRoomCategoryList(roomCategoryCondition);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else {
			try {
				roomCategoryList = roomCategoryService.getRoomCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		modelMap.put("roomCategoryList", roomCategoryList);
		List<Area> areaList = null;
		try {
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		} 
		return modelMap;
	}

	@RequestMapping(value = "/listrooms", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listRooms(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if ((pageIndex > -1) && (pageSize > -1)) {
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");			
			long roomCategoryId = HttpServletRequestUtil.getLong(request,
					"roomCategoryId");
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			String roomName = HttpServletRequestUtil.getString(request,
					"roomName");
			Room roomCondition = compactRoomCondition4Search(parentId,
					roomCategoryId, areaId, roomName);
			RoomExecution se = roomService.getRoomList(roomCondition,
					pageIndex, pageSize);
			modelMap.put("roomList", se.getRoomList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}

		return modelMap;
	}

	private Room compactRoomCondition4Search(long parentId,
			long roomCategoryId, int areaId, String roomName) {
		Room roomCondition = new Room();
		if (parentId != -1L) {
			RoomCategory childCategory = new RoomCategory();
			RoomCategory parentCategory = new RoomCategory();
			parentCategory.setRoomCategoryId(parentId);
			childCategory.setParent(parentCategory);
			roomCondition.setRoomCategory(childCategory);
		}
		if (roomCategoryId != -1L) {
			RoomCategory roomCategory = new RoomCategory();
			roomCategory.setRoomCategoryId(roomCategoryId);
			roomCondition.setRoomCategory(roomCategory);
		}
		if (areaId != -1L) {
			Area area = new Area();
			area.setAreaId(areaId);
			roomCondition.setArea(area);
		}

		if (roomName != null) {
			roomCondition.setRoomName(roomName);
		}
		roomCondition.setEnableStatus(1);
		return roomCondition;
	}
}
