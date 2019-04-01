package cn.zpy.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zpy.ssm.mapper.TypeMapper;
import cn.zpy.ssm.mapper.TypeMapperCustom;
import cn.zpy.ssm.po.Type;
import cn.zpy.ssm.po.TypeCustom;
import cn.zpy.ssm.po.TypeQueryVo;
import cn.zpy.ssm.service.TypeService;
//接口
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeMapperCustom typeMapperCustom; 
	
	@Autowired
	private TypeMapper typeMapper;
	
	@Override
	public List<TypeCustom> findTypeList(TypeQueryVo typeQueryVo) throws Exception {
		// 通过TypeMapperCustom查询数据库
		return typeMapperCustom.findTypeList(typeQueryVo) ;
	}

	@Override
	public TypeCustom findTypeById(Integer typeid) throws Exception {
		Type type = typeMapper.selectByPrimaryKey(typeid);
		//中间业务处理
		//***
		//返回
		TypeCustom typeCustom = new TypeCustom();
		//拷贝 属性值
		BeanUtils.copyProperties(type, typeCustom);
		return typeCustom;
	}

	@Override
	public void updateType(Integer typeid, TypeCustom typeCustom) throws Exception {
		//添加校验
		
		//更新信息
		typeCustom.setTypeid(typeid);
		typeMapper.updateByPrimaryKeyWithBLOBs(typeCustom);
	}

}
