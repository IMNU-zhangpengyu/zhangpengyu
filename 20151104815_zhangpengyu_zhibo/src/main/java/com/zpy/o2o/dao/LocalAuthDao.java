package com.zpy.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.zpy.o2o.entity.LocalAuth;

public interface LocalAuthDao {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username,
			@Param("password") String password);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") long userId);

	/**
	 * 
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);
	int insertLocal(LocalAuth localAuth);
	/**
	 * 
	 * @param localAuth
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId,
			@Param("username") String username,
			@Param("password") String password,
			@Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}
