package cn.zpy.ssm.service;

import java.util.List;

import cn.zpy.ssm.po.TypeCustom;
import cn.zpy.ssm.po.TypeQueryVo;

/**
 * 管理service
 *
 */
public interface TypeService {

	//查询列表
	public List<TypeCustom> findTypeList(TypeQueryVo typeQueryVo)throws Exception;
	
	//根据Id查询信息
	public TypeCustom findTypeById(Integer id) throws Exception;
	
	//修改信息
	public void updateType(Integer id,TypeCustom typeCustom) throws Exception;
	
}
