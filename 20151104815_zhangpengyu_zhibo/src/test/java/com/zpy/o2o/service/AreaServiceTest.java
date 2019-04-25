package com.zpy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zpy.o2o.BaseTest;
import com.zpy.o2o.entity.Area;
import com.zpy.o2o.entity.PersonInfo;


public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	@Autowired
	private PersonInfoService personInfoService;
	
	@Test
	public void testInsert() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("33");
		personInfo.setUserType(1);
		personInfo.setEnableStatus(1);
		personInfoService.insertPersonInfo(personInfo);
	}
	
	
	@Test
	@Ignore
	public void testGetAreaList(){
		List<Area> areaList = areaService.getAreaList();
		assertEquals("二",areaList.get(0).getAreaName());
	}
	
}
