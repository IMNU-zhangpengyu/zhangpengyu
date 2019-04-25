package com.zpy.o2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/local")
public class LocalController {
	
	@RequestMapping(value = "/accountbind",method = RequestMethod.GET)
	private String accountbind() {
		return "local/accountbind";
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/changepsw",method = RequestMethod.GET)
	private String changepsw() {
		return "local/changepsw";
	}
	
	/**
	 * 登陆页面
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	private String login() {
		return "local/login";
	}
}
