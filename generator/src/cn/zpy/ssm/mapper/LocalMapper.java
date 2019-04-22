package cn.zpy.ssm.mapper;

import cn.zpy.ssm.po.Local;
import cn.zpy.ssm.po.LocalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LocalMapper {
    int countByExample(LocalExample example);

    int deleteByExample(LocalExample example);

    int deleteByPrimaryKey(Integer localid);

    int insert(Local record);

    int insertSelective(Local record);

    List<Local> selectByExample(LocalExample example);

    Local selectByPrimaryKey(Integer localid);

    int updateByExampleSelective(@Param("record") Local record, @Param("example") LocalExample example);

    int updateByExample(@Param("record") Local record, @Param("example") LocalExample example);

    int updateByPrimaryKeySelective(Local record);

    int updateByPrimaryKey(Local record);
}