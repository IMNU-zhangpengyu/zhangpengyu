package com.zpy.o2o.web.roomadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpy.o2o.dto.ImageHolder;
import com.zpy.o2o.dto.RoomExecution;
import com.zpy.o2o.entity.Area;
import com.zpy.o2o.entity.PersonInfo;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.entity.RoomCategory;
import com.zpy.o2o.enums.RoomStateEnum;
import com.zpy.o2o.exceptions.RoomOperationException;
import com.zpy.o2o.service.AreaService;
import com.zpy.o2o.service.RoomCategoryService;
import com.zpy.o2o.service.RoomService;
import com.zpy.o2o.util.CodeUtil;
import com.zpy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/roomadmin")
public class RoomManagementController {
	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomCategoryService roomCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/getroommanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getRoomManagementInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		long roomId = HttpServletRequestUtil.getLong(request,"roomId");
		if(roomId <= 0) {
			Object currentRoomObj = request.getSession().getAttribute("currentRoom");
			if(currentRoomObj == null) {
				modelMap.put("redirect",true);
				modelMap.put("url","/20151104815_zhangpengyu_zhibo/roomadmin/roomlist");
			}else {
				Room currentRoom = (Room) currentRoomObj;
				modelMap.put("redirect",false);
				modelMap.put("roomId",currentRoom.getRoomId());
			}
		}else {
			Room currentRoom = new Room();
			currentRoom.setRoomId(roomId);
			request.getSession().setAttribute("currentRoom",currentRoom);
			modelMap.put("redirect",false);
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/getroomlist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getRoomList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
//		PersonInfo user = new PersonInfo();
//		user.setUserId(1L);
//		user.setName("test");
//		request.getSession().setAttribute("user",user);
//		user = (PersonInfo) request.getSession().getAttribute("user");
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		
		try {
			Room roomCondition = new Room();
			roomCondition.setOwner(user);
			RoomExecution se = roomService.getRoomList(roomCondition, 0, 100);
			modelMap.put("roomList",se.getRoomList());
			//房間放入session
			request.getSession().setAttribute("roomList",se.getRoomList());
			modelMap.put("user",user);
			modelMap.put("success",true);
		}catch(Exception e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/getroombyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getRoomById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long roomId = HttpServletRequestUtil.getLong(request, "roomId");
		if (roomId > -1) {
			try {
				Room room = roomService.getByRoomId(roomId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("room", room);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "空roomId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getroominitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getRoomInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<RoomCategory> roomCategoryList = new ArrayList<RoomCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			roomCategoryList = roomCategoryService.getRoomCategoryList(new RoomCategory());
			areaList = areaService.getAreaList();
			modelMap.put("roomCategoryList", roomCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifyroom", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyRoom(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错的验证码");
			return modelMap;
		}
		// 接收转化参数
		String roomStr = HttpServletRequestUtil.getString(request, "roomStr");
		ObjectMapper mapper = new ObjectMapper();
		Room room = null;
		try {
			room = mapper.readValue(roomStr, Room.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile roomImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			roomImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("roomImg");
		}
		// 修改room信息
		if (room != null && room.getRoomId() != null) {
			RoomExecution se;
			try {
				if (roomImg == null) {
					se = roomService.modifyRoom(room, null);
				} else {
					ImageHolder imageHolder = new ImageHolder(roomImg.getOriginalFilename(),roomImg.getInputStream());
					se = roomService.modifyRoom(room, imageHolder);
				}
				if (se.getState() == RoomStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (RoomOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入房间Id");
			return modelMap;
		}
	}

	@RequestMapping(value = "/registerroom", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerRoom(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错的验证码");
			return modelMap;
		}
		// 接收转化参数
		String roomStr = HttpServletRequestUtil.getString(request, "roomStr");
		ObjectMapper mapper = new ObjectMapper();
		Room room = null;
		try {
			room = mapper.readValue(roomStr, Room.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile roomImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			roomImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("roomImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 注册room
		if (room != null && roomImg != null) {
			/* 通过session获取信息 到owner拥有者*/
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			room.setOwner(owner);
			RoomExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(roomImg.getOriginalFilename(),roomImg.getInputStream());
				se = roomService.addRoom(room,imageHolder);
				if (se.getState() == RoomStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					//session中的owner的room列表
					@SuppressWarnings("unchecked")
					List<Room> roomList = (List<Room>) request.getSession().getAttribute("roomList");
					if(roomList == null || roomList.size() == 0) {
						roomList = new ArrayList<Room>();	
					}
					roomList.add(se.getRoom());
					request.getSession().setAttribute("roomList", roomList);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (RoomOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入房间信息");
			return modelMap;
		}
		// 返回结果(各步骤包含)
	}
	// 转换File类型
//	private static void inputStreamToFile(InputStream ins,File file) {
//		FileOutputStream os = null;
//		try {
//			os = new FileOutputStream(file);
//			int bytesRead = 0;
//			byte[] buffer = new byte[1024];
//			while((bytesRead = ins.read(buffer))!= -1) {
//				os.write(buffer,0,bytesRead);
//			}
//		}catch(Exception e) {
//			throw new RuntimeException("调用inputStreamToFile异常："+ e.getMessage());
//		}finally {
//			try {
//				if(os != null) {
//					os.close();
//				}
//				if(ins != null) {
//					ins.close();
//				}
//			}catch(IOException e) {
//				throw new RuntimeException("inputStreamToFile关闭io异常："+ e.getMessage());
//			}
//		}
//	}
}
