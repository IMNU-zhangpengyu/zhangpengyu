package com.zpy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zpy.o2o.BaseTest;
import com.zpy.o2o.dto.ImageHolder;
import com.zpy.o2o.dto.ProductExecution;
import com.zpy.o2o.entity.Product;
import com.zpy.o2o.entity.ProductCategory;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.enums.ProductStateEnum;

public class ProductServiceTest extends BaseTest{
	@Autowired
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws FileNotFoundException{
		Product product = new Product();
		Room room = new Room();
		room.setRoomId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(1L);
		product.setRoom(room);
		product.setProductCategory(pc);
		product.setProductName("测试1");
		product.setProductDesc("测试1");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		File thumbnailFile = new File("D:/git_work/zhangpengyu/temp/2.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(),is);
		File productImg1 = new File("D:/git_work/zhangpengyu/temp/2.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("D:/git_work/zhangpengyu/temp/3.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(),is1));
		productImgList.add(new ImageHolder(productImg2.getName(),is2));
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
		
	}
}
