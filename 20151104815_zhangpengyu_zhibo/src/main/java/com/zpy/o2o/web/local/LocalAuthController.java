package com.zpy.o2o.web.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpy.o2o.dto.LocalAuthExecution;
import com.zpy.o2o.entity.LocalAuth;
import com.zpy.o2o.entity.PersonInfo;
import com.zpy.o2o.enums.LocalAuthStateEnum;
import com.zpy.o2o.service.LocalAuthService;
import com.zpy.o2o.util.CodeUtil;
import com.zpy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value="local",method= {RequestMethod.GET,RequestMethod.POST})
public class LocalAuthController {
	@Autowired
	private LocalAuthService localAuthService;
	
	@RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> bindLocalAuth(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success",false);
			modelMap.put("errMsg","验证码错误");
			return modelMap;
		}
		String userName = HttpServletRequestUtil.getString(request,"userName");
		String password = HttpServletRequestUtil.getString(request,"password");
		PersonInfo user =(PersonInfo) request.getSession().getAttribute("user");
		if(userName != null && password != null && user != null && user.getUserId() != null) {
			LocalAuth localAuth = new LocalAuth();
			localAuth.setUserName(userName);
			localAuth.setPassword(password);
			localAuth.setPersonInfo(user);
			LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
			if(le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
				modelMap.put("success",true);
			}else {
				modelMap.put("success",false);
				modelMap.put("errMsg",le.getStateInfo());
			}
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","用户名密码不能为空");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> changeLocalPwd(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success",false);
			modelMap.put("errMsg","验证码错误");
			return modelMap;
		}
		String username = HttpServletRequestUtil.getString(request,"userName");
		String password = HttpServletRequestUtil.getString(request,"password");
		String newPassword = HttpServletRequestUtil.getString(request,"newPassword");
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if(username != null && password != null && newPassword != null && user != null && user.getUserId() != null
				&& !password.equals(newPassword)) {
			try {
				LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
				if(localAuth == null || !localAuth.getUserName().equals(username)) {
					modelMap.put("success",false);
					modelMap.put("errMsg","请输入正确账号");
					return modelMap;
				}
				LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), username, password, newPassword);
				if(le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success",true);
				}else {
					modelMap.put("success",false);
					modelMap.put("errMsg",le.getStateInfo());
				}
			}catch(RuntimeException e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
				return modelMap;
			}
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","请输入密码");
		}
		return modelMap;		
	}
	
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> logincheck(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
		if(needVerify && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success",false);
			modelMap.put("errMsg","验证码错误");
			return modelMap;
		}
		String username = HttpServletRequestUtil.getString(request,"userName");
		String password = HttpServletRequestUtil.getString(request,"password");
		if(username != null && password != null) {
			LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(username, password);
			if(localAuth != null) {
				modelMap.put("success",true);
				request.getSession().setAttribute("user",localAuth.getPersonInfo());
			}else {
				modelMap.put("success",false);
				modelMap.put("errMsg","用户名或密码错误");
			}
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","用户名或密码不能为空");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> logout(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		//将session设置为空
		request.getSession().setAttribute("user",null);
		modelMap.put("success",true);
		return modelMap;
	}
}
