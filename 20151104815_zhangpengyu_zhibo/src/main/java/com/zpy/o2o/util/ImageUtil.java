package com.zpy.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 
 * 处理图片
 *
 */
public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	// 日志相关
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	public static String generateThumbnail(InputStream thumbnailInputStream,String fileName, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is:" + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
		// 创建缩略图并加水映
		try {
			Thumbnails.of(thumbnailInputStream).size(788, 1080)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/budingzhibobaise.png")), 0.8f)
					.outputQuality(1f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}

	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * 
	 * 创建目录
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

	}

	/**
	 * 
	 * 获取输入文件流名
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 
	 * 生成随机文件名
	 */
	public static String getRandomFileName() {
		// 随机5位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}

	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("D:/git_work/zhangpengyu/temp/2.jpg")).size(788, 1080)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/budingzhibobaise.png")), 0.8f)
				.outputQuality(1f).toFile("D:/git_work/zhangpengyu/temp/2new.jpg");
	}
	
	/**
	 * storePath是文件的还是目录路径
	 * 文件就删除文件
	 * 目录则删除目录
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File file[] = fileOrPath.listFiles();
				for(int i = 0;i < file.length; i++) {
					file[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
	
	
	
}
