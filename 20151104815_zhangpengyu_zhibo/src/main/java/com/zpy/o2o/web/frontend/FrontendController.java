package com.zpy.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendController {
	
	@RequestMapping(value = "/roomlist",method = RequestMethod.GET)
	private String showRoomList() {
		return "frontend/roomlist";
	}
	
	@RequestMapping(value = "/roomdetail",method = RequestMethod.GET)
	private String showRoomDetail() {
		return "frontend/roomdetail";
	}
	
	@RequestMapping(value = "/productdetail",method = RequestMethod.GET)
	private String showProductDetail() {
		return "frontend/productdetail";
	}
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	private String index() {
		return "frontend/index";
	}
}
