package com.zpy.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zpy.o2o.BaseTest;
import com.zpy.o2o.entity.Area;
import com.zpy.o2o.entity.PersonInfo;

public class AreaDaoTest extends BaseTest{
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	
	@Test
	@Ignore
	public void testInsertPersonInfo() throws Exception{
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("22");
		personInfo.setGender("22");
		personInfo.setUserType(1);
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		personInfo.setEnableStatus(1);
		int e = personInfoDao.insertPersonInfo(personInfo);
		assertEquals(1,e);
	}
	
	@Test
	public void testQueryPersonInfo() throws Exception{
		long userId = 1;
		PersonInfo p = personInfoDao.queryPersonInfoById(userId);
		System.out.println(p.getName());
	}
	
	@Test
	@Ignore
	public void testQueryArea(){
		List<Area> areaList = areaDao.queryArea();
		assertEquals(2,areaList.size());
	}
	
	
}
