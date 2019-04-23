package com.zpy.o2o.service;

import java.util.List;

import com.zpy.o2o.dto.ImageHolder;
import com.zpy.o2o.dto.ProductExecution;
import com.zpy.o2o.entity.Product;

public interface ProductService {
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

	Product getProductById(long productId);

	ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList);

	ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws RuntimeException;
}
