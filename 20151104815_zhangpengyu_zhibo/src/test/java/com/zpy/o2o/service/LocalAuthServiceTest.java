package com.zpy.o2o.service;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zpy.o2o.BaseTest;
import com.zpy.o2o.dto.LocalAuthExecution;
import com.zpy.o2o.entity.LocalAuth;
import com.zpy.o2o.entity.PersonInfo;
import com.zpy.o2o.enums.WechatAuthStateEnum;

public class LocalAuthServiceTest extends BaseTest{
	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	@Ignore
	public void testBindLocalAuth() {
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		String username = "123456";
		String password = "123456";
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUserName(username);
		localAuth.setPassword(password);
		LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),lae.getState());
		localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
		System.out.println("昵称："+ localAuth.getPersonInfo().getName());
		System.out.println("密码："+ localAuth.getPassword());
	}
	
	@Test
	public void testModifyLocalAuth() {
		long userId = 1;
		String username = "123456";
		String password = "123456";
		String newPassword = "123456new";
		LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId, username, password, newPassword);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),lae.getState());
		LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(username, newPassword);
		System.out.println(localAuth.getPersonInfo().getName());
	}
}
