package com.zpy.o2o.web.roomadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpy.o2o.dto.ImageHolder;
import com.zpy.o2o.dto.ProductExecution;
import com.zpy.o2o.entity.Product;
import com.zpy.o2o.entity.ProductCategory;
import com.zpy.o2o.entity.Room;
import com.zpy.o2o.enums.ProductStateEnum;
import com.zpy.o2o.service.ProductCategoryService;
import com.zpy.o2o.service.ProductService;
import com.zpy.o2o.util.CodeUtil;
import com.zpy.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/roomadmin")
public class ProductManagementController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	private static final int IMAGEMAXCOUNT = 6;

	@RequestMapping(value = "/listproductsbyroom", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductsByRoom(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Room currentRoom = (Room) request.getSession().getAttribute(
				"currentRoom");
		if ((pageIndex > -1) && (pageSize > -1) && (currentRoom != null)
				&& (currentRoom.getRoomId() != null)) {
			long productCategoryId = HttpServletRequestUtil.getLong(request,
					"productCategoryId");
			String productName = HttpServletRequestUtil.getString(request,
					"productName");
			Product productCondition = compactProductCondition4Search(
					currentRoom.getRoomId(), productCategoryId, productName);
			ProductExecution pe = productService.getProductList(
					productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or roomId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductById(@RequestParam Long productId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productId > -1) {
			Product product = productService.getProductById(productId);
			List<ProductCategory> productCategoryList = productCategoryService
					.getProductCategoryList(product.getRoom().getRoomId());
			modelMap.put("product", product);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or roomId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getproductcategorylistbyroomId", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductCategoryListByRoomId(
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Room currentRoom = (Room) request.getSession().getAttribute(
				"currentRoom");
		if ((currentRoom != null) && (currentRoom.getRoomId() != null)) {
			List<ProductCategory> productCategoryList = productCategoryService
					.getProductCategoryList(currentRoom.getRoomId());
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or roomId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		String productStr = HttpServletRequestUtil.getString(request,
				"productStr");
		MultipartHttpServletRequest multipartRequest = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			if (multipartResolver.isMultipart(request)) {
				multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
				thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream());
				for (int i = 0; i < IMAGEMAXCOUNT; i++) {
					CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest
							.getFile("productImg" + i);
					if (productImgFile != null) {
						ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
								productImgFile.getInputStream());
						productImgList.add(productImg);
					}
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片不能为空");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		if (product != null && thumbnail != null && productImgList.size() > 0) {
			try {
				Room currentRoom = (Room) request.getSession().getAttribute(
						"currentRoom");
				Room room = new Room();
				room.setRoomId(currentRoom.getRoomId());
				product.setRoom(room);
				ProductExecution pe = productService.addProduct(product,
						thumbnail, productImgList);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
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
			modelMap.put("errMsg", "请输入商品信息");
		}
		return modelMap;
	}

//	@RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
//	@ResponseBody
//	private Map<String, Object> modifyProduct(HttpServletRequest request) {
//		boolean statusChange = HttpServletRequestUtil.getBoolean(request,
//				"statusChange");
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "输入了错误的验证码");
//			return modelMap;
//		}
//		ObjectMapper mapper = new ObjectMapper();
//		Product product = null;
//		String productStr = HttpServletRequestUtil.getString(request,
//				"productStr");
//		MultipartHttpServletRequest multipartRequest = null;
//		CommonsMultipartFile thumbnail = null;
//		List<CommonsMultipartFile> productImgList = new ArrayList<CommonsMultipartFile>();
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//				request.getSession().getServletContext());
//		if (multipartResolver.isMultipart(request)) {
//			multipartRequest = (MultipartHttpServletRequest) request;
//			thumbnail = (CommonsMultipartFile) multipartRequest
//					.getFile("thumbnail");
//			for (int i = 0; i < IMAGEMAXCOUNT; i++) {
//				CommonsMultipartFile productImg = (CommonsMultipartFile) multipartRequest
//						.getFile("productImg" + i);
//				if (productImg != null) {
//					productImgList.add(productImg);
//				}
//			}
//		}
//		try {
//			product = mapper.readValue(productStr, Product.class);
//		} catch (Exception e) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", e.toString());
//			return modelMap;
//		}
//		if (product != null) {
//			try {
//				Room currentRoom = (Room) request.getSession().getAttribute(
//						"currentRoom");
//				Room room = new Room();
//				room.setRoomId(currentRoom.getRoomId());
//				product.setRoom(room);
//				ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
//				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
//					modelMap.put("success", true);
//				} else {
//					modelMap.put("success", false);
//					modelMap.put("errMsg", pe.getStateInfo());
//				}
//			} catch (RuntimeException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.toString());
//				return modelMap;
//			}
//
//		} else {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "请输入商品信息");
//		}
//		return modelMap;
//	}

	private Product compactProductCondition4Search(long roomId,
			long productCategoryId, String productName) {
		Product productCondition = new Product();
		Room room = new Room();
		room.setRoomId(roomId);
		productCondition.setRoom(room);
		if (productCategoryId != -1L) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		if (productName != null) {
			productCondition.setProductName(productName);
		}
		return productCondition;
	}
}
