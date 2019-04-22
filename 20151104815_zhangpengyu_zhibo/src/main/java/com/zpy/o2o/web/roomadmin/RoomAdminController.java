package com.zpy.o2o.web.roomadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="roomadmin",method= {RequestMethod.GET})
public class RoomAdminController {
	@RequestMapping(value ="/roomoperation")
	public String roomOperation() {
		//spring-web.xml中设置前后缀
		return "room/roomoperation";
	}
	@RequestMapping(value ="/roomlist")
	public String roomList() {
		//spring-web.xml中设置前后缀
		return "room/roomlist";
	}
	@RequestMapping(value ="/roommanagement")
	public String roomManagement() {
		//spring-web.xml中设置前后缀
		return "room/roommanagement";
	}
}
