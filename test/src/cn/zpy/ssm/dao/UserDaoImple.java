package cn.zpy.ssm.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.zpy.ssm.util.JDBCUtils;
import cn.zpy.ssm.po.Local;

public class UserDaoImple implements UserDao {

	public Local login(Local local) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from local where username = ? and password = ?";
		Local existUser;
		try {
			existUser = queryRunner.query(sql, new BeanHandler<Local>(Local.class), local.getUsername(),local.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ÓÃ»§µÇÂ¼Ê§°Ü!");
		}
		return existUser;
	}

}
