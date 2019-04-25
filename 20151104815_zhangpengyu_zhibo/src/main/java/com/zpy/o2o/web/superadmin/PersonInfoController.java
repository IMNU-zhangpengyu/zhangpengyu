package com.zpy.o2o.web.superadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpy.o2o.dto.PersonInfoExecution;

import com.zpy.o2o.entity.PersonInfo;
import com.zpy.o2o.enums.PersonInfoStateEnum;
import com.zpy.o2o.service.PersonInfoService;
import com.zpy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class PersonInfoController {
	@Autowired
	private PersonInfoService personInfoService;



	@RequestMapping(value = "/modifypersonInfo", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyPersonInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long userId = HttpServletRequestUtil.getLong(request, "userId");
		int enableStatus = HttpServletRequestUtil.getInt(request,
				"enableStatus");
		if (userId > 0 && enableStatus > 0) {
			try {
				PersonInfo pi = new PersonInfo();
				pi.setUserId(userId);
				pi.setEnableStatus(enableStatus);
				PersonInfoExecution ae = personInfoService.modifyPersonInfo(pi);
				if (ae.getState() == PersonInfoStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ae.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入需要修改的帐号信息");
		}
		return modelMap;
	}

}
