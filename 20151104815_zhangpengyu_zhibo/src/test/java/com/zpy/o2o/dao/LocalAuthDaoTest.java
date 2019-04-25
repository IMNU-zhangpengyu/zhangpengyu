package com.zpy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.zpy.o2o.BaseTest;
import com.zpy.o2o.entity.LocalAuth;
import com.zpy.o2o.entity.PersonInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest  extends BaseTest{
	@Autowired
	private LocalAuthDao localAuthDao;
	private static final String username = "123456";
	private static final String password = "123456";
	
	@Test
	public void testAInsertLocal() throws Exception {
		LocalAuth localAuth = new LocalAuth();
		
		localAuth.setUserName(username);
		localAuth.setPassword(password);
		localAuth.setCreateTime(new Date());
		int effectedNum = localAuthDao.insertLocal(localAuth);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testAInsertLocalAuth() throws Exception {
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUserName(username);
		localAuth.setPassword(password);
		localAuth.setCreateTime(new Date());
		int effectedNum = localAuthDao.insertLocalAuth(localAuth);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryLocalByUserNameAndPwd()throws Exception {
		LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username, password);
		assertEquals("测试",localAuth.getPersonInfo().getName());
	}
	
	@Test
	@Ignore
	public void testQueryLocalByUserId()throws Exception {
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
		assertEquals("测试",localAuth.getPersonInfo().getName());
	}
	
	@Test
	@Ignore
	public void testUpdateLocalAuth()throws Exception {
		Date now = new Date();
		int effectedNum = localAuthDao.updateLocalAuth(1L, username, password, password + "new", now);
		assertEquals(1,effectedNum);
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
		System.out.println(localAuth.getPassword());
		
	}
}
