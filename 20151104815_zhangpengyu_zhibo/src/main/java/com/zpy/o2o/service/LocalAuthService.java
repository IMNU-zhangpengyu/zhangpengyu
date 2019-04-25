package com.zpy.o2o.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zpy.o2o.dto.LocalAuthExecution;
import com.zpy.o2o.entity.LocalAuth;

public interface LocalAuthService {
	/**
	 * 
	 * @param username
	 * @return
	 */
	LocalAuth getLocalAuthByUserNameAndPwd(String username, String password);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(long userId);

	/**
	 * 
	 * @param localAuth
	 * @param profileImg
	 * @return
	 * @throws RuntimeException
	 */
//	LocalAuthExecution register(LocalAuth localAuth,
//			CommonsMultipartFile profileImg) throws RuntimeException;

	/**
	 * 
	 * @param localAuth
	 * @return
	 * @throws RuntimeException
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth)
			throws RuntimeException;

	/**
	 * 
	 * @param localAuthId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String username,
			String password, String newPassword);
}
