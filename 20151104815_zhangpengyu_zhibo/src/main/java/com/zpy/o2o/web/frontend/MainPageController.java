package com.zpy.o2o.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpy.o2o.entity.HeadLine;
import com.zpy.o2o.entity.RoomCategory;
import com.zpy.o2o.service.HeadLineService;
import com.zpy.o2o.service.RoomCategoryService;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
	@Autowired
	private RoomCategoryService roomCategoryService;
	@Autowired
	private HeadLineService headLineService;
	
	@RequestMapping(value ="/listmainpageinfo",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object>listMainPageInfo(){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<RoomCategory> roomCategoryList = new ArrayList<RoomCategory>();
		try {
			//获取一级房间类别列表（parentId为空的ShopCategory）
			roomCategoryList = roomCategoryService.getRoomCategoryList(null);
			modelMap.put("roomCategoryList", roomCategoryList);
		}catch(Exception e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		try {
			//获取状态为可用（1）的头条列表
			HeadLine headLineCondition = new HeadLine();
			headLineCondition.setEnableStatus(1);
			headLineList = headLineService.getHeadLineList(headLineCondition);
			modelMap.put("headLineList", headLineList);
		}catch(Exception e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		modelMap.put("success",true);
		return modelMap;
	}
}
