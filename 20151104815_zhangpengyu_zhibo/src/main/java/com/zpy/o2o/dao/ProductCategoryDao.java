package com.zpy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zpy.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 通过employee id 查询店铺
	 * 
	 * @param long roomId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> queryProductCategoryList(long roomId);
//	List<ProductCategory> queryByRoomId(long roomId);

	/**
	 * 新增商品类别
	 * 
	 * @param ProductCategory
	 *            productCategory
	 * @return effectedNum
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/**
	 * 删除商品类别（初版，即只支持删除尚且没有发布商品的商品类别）
	 * 
	 * @param productCategoryId
	 * @param roomId
	 * @return effectedNum
	 */
	int deleteProductCategory(
			@Param("productCategoryId") long productCategoryId,
			@Param("roomId") long roomId);
}
