package com.zpy.o2o.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");
	
	//返回图片根路径
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/git_work/zhangpengyu/image1";
		} else {
			basePath = "/home/zhangpengyu/image1";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	//返回子路径
	public static String getRoomImagePath(long roomId) {
		String imagePath = "/upload/item/room/" + roomId +"/";
		return imagePath.replace("/", seperator);
	}
}
