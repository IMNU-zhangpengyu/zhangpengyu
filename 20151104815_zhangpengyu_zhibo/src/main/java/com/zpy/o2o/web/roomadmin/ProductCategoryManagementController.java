package com.zpy.o2o.web.roomadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpy.o2o.dto.ProductCategoryExecution;
import com.zpy.o2o.dto.Result;
import com.zpy.o2o.entity.ProductCategory;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.enums.ProductCategoryStateEnum;
import com.zpy.o2o.service.ProductCategoryService;

@Controller
@RequestMapping("/roomadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryList(
			HttpServletRequest request) {
		Room currentRoom = (Room) request.getSession().getAttribute(
				"currentRoom");
		List<ProductCategory> list = null;
		if (currentRoom != null && currentRoom.getRoomId() > 0) {
			list = productCategoryService.getByRoomId(currentRoom.getRoomId());
			return new Result<List<ProductCategory>>(true, list);// WEB-INF/html/"roomlist".html
		} else {
			ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
			return new Result<List<ProductCategory>>(false, ps.getState(),
					ps.getStateInfo());
		}
	}

	@RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProductCategorys(
			@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Room currentRoom = (Room) request.getSession().getAttribute(
				"currentRoom");
		for (ProductCategory pc : productCategoryList) {
			pc.setRoomId(currentRoom.getRoomId());
		}
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService
						.batchAddProductCategory(productCategoryList);
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS
						.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}

	@RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeProductCategory(Long productCategoryId,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				Room currentRoom = (Room) request.getSession().getAttribute(
						"currentRoom");
				ProductCategoryExecution pe = productCategoryService
						.deleteProductCategory(productCategoryId,
								currentRoom.getRoomId());
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS
						.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类别");
		}
		return modelMap;
	}

}
