package cn.zpy.ssm.service;

import cn.zpy.ssm.dao.UserDao;
import cn.zpy.ssm.dao.UserDaoImple;
import cn.zpy.ssm.po.Local;

public class UserService {

	public Local login(Local local) {
		UserDao dao = new UserDaoImple();
		return dao.login(local);
	}
	
	
}
