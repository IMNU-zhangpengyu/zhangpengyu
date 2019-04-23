package com.zpy.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpy.o2o.entity.Product;
import com.zpy.o2o.entity.ProductCategory;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.service.RoomService;
import com.zpy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class RoomDetailController {
//	@Autowired
//	private RoomService roomService;
//	@Autowired
//	private ProductService productService;
//	@Autowired
//	private ProductCategoryService productCategoryService;
//
//	@RequestMapping(value = "/listroomdetailpageinfo", method = RequestMethod.GET)
//	@ResponseBody
//	private Map<String, Object> listRoomDetailPageInfo(
//			HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		long roomId = HttpServletRequestUtil.getLong(request, "roomId");
//		Room room = null;
//		List<ProductCategory> productCategoryList = null;
//		if (roomId != -1) {
//			room = roomService.getByRoomId(roomId);
//			productCategoryList = productCategoryService.getByRoomId(roomId);
//			modelMap.put("room", room);
//			modelMap.put("productCategoryList", productCategoryList);
//			modelMap.put("success", true);
//		} else {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "empty roomId");
//		}
//		return modelMap;
//	}
//
//	@RequestMapping(value = "/listproductsbyroom", method = RequestMethod.GET)
//	@ResponseBody
//	private Map<String, Object> listProductsByRoom(HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
//		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
//		long roomId = HttpServletRequestUtil.getLong(request, "roomId");
//		if ((pageIndex > -1) && (pageSize > -1) && (roomId > -1)) {
//			long productCategoryId = HttpServletRequestUtil.getLong(request,
//					"productCategoryId");
//			String productName = HttpServletRequestUtil.getString(request,
//					"productName");
//			Product productCondition = compactProductCondition4Search(roomId,
//					productCategoryId, productName);
//			ProductExecution pe = productService.getProductList(
//					productCondition, pageIndex, pageSize);
//			modelMap.put("productList", pe.getProductList());
//			modelMap.put("count", pe.getCount());
//			modelMap.put("success", true);
//		} else {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "empty pageSize or pageIndex or roomId");
//		}
//		return modelMap;
//	}
//
//	private Product compactProductCondition4Search(long roomId,
//			long productCategoryId, String productName) {
//		Product productCondition = new Product();
//		Room room = new Room();
//		room.setRoomId(roomId);
//		productCondition.setRoom(room);
//		if (productCategoryId != -1L) {
//			ProductCategory productCategory = new ProductCategory();
//			productCategory.setProductCategoryId(productCategoryId);
//			productCondition.setProductCategory(productCategory);
//		}
//		if (productName != null) {
//			productCondition.setProductName(productName);
//		}
//		productCondition.setEnableStatus(1);
//		return productCondition;
//	}
}
