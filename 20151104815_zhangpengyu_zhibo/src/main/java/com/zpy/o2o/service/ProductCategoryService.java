package com.zpy.o2o.service;

import java.util.List;

import com.zpy.o2o.dto.ProductCategoryExecution;
import com.zpy.o2o.entity.ProductCategory;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * 
	 * @param long roomId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> getProductCategoryList(long roomId);

	/**
	 * 
	 * @param productCategory
	 * @return
	 * @throws RuntimeException
	 */
	ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategoryList) throws RuntimeException;

	/**
	 * 
	 * @param productCategoryId
	 * @param roomId
	 * @return
	 * @throws RuntimeException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,
			long roomId) throws RuntimeException;
}
