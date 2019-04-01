package cn.zpy.ssm.mapper;

import cn.zpy.ssm.po.Type;
import cn.zpy.ssm.po.TypeCustom;
import cn.zpy.ssm.po.TypeExample;
import cn.zpy.ssm.po.TypeQueryVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeMapperCustom {
	
	//≤È—Ø¡–±Ì
	public List<TypeCustom> findTypeList(TypeQueryVo typeQueryVo)throws Exception;
	


}