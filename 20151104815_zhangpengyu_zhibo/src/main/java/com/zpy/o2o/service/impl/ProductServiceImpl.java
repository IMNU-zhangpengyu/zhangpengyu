package com.zpy.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zpy.o2o.dao.ProductDao;
import com.zpy.o2o.dao.ProductImgDao;
import com.zpy.o2o.dto.ImageHolder;
import com.zpy.o2o.dto.ProductExecution;
import com.zpy.o2o.entity.Product;
import com.zpy.o2o.entity.ProductImg;
import com.zpy.o2o.enums.ProductStateEnum;
import com.zpy.o2o.service.ProductService;
import com.zpy.o2o.util.FileUtil;
import com.zpy.o2o.util.ImageUtil;
import com.zpy.o2o.util.PageCalculator;
import com.zpy.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}

	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductByProductId(productId);
	}

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws RuntimeException {
		if (product != null && product.getRoom() != null && product.getRoom().getRoomId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品失败:" + e.toString());
			}
			if (productImgList != null && productImgList.size() > 0) {
				addProductImgList(product, productImgList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws RuntimeException {
		if (product != null && product.getRoom() != null && product.getRoom().getRoomId() != null) {
			product.setLastEditTime(new Date());
			if (thumbnail != null) {
				Product tempProduct = productDao.queryProductByProductId(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					FileUtil.deleteFile(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			if (productImgList != null && productImgList.size() > 0) {
				deleteProductImgs(product.getProductId());
				addProductImgList(product, productImgList);
			}
			try {
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new RuntimeException("更新商品信息失败:" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		String dest = PathUtil.getRoomImagePath(product.getRoom().getRoomId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		for(ImageHolder productImgHolder : productImgHolderList) {
			//generateThumbnail数据改变（8-3中）
			String imgAddr = ImageUtil.generateThumbnail(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		if(productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if(effectedNum <= 0) {
					throw new RuntimeException("创建图片失败");
				}
			}catch (Exception e) {
				throw new RuntimeException("创建图片失败" + e.toString());
			}
		}
	}

	private void deleteProductImgs(long productId) {
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		for (ProductImg productImg : productImgList) {
			FileUtil.deleteFile(productImg.getImgAddr());
		}
		productImgDao.deleteProductImgByProductId(productId);
	}

	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getRoomImagePath(product.getRoom().getRoomId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}
}
