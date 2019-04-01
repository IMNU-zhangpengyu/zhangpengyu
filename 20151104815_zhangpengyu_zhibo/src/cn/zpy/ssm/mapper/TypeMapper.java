package cn.zpy.ssm.mapper;

import cn.zpy.ssm.po.Type;
import cn.zpy.ssm.po.TypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeMapper {
    int countByExample(TypeExample example);

    int deleteByExample(TypeExample example);

    int insert(Type record);

    int insertSelective(Type record);
    
   // List<Type> selectByExampleWithBLOBs(TypeExample example);

    List<Type> selectByExample(TypeExample example);
    
    Type selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExampleWithBLOBs(@Param("record") Type record, @Param("example") TypeExample example);
    
    int updateByPrimaryKeyWithBLOBs(Type record);
    
    int updateByExample(@Param("record") Type record, @Param("example") TypeExample example);
}