package cn.zpy.ssm.mapper;

import cn.zpy.ssm.po.Roomtype;
import cn.zpy.ssm.po.RoomtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomtypeMapper {
    int countByExample(RoomtypeExample example);

    int deleteByExample(RoomtypeExample example);

    int deleteByPrimaryKey(Integer roomtypeid);

    int insert(Roomtype record);

    int insertSelective(Roomtype record);

    List<Roomtype> selectByExample(RoomtypeExample example);

    Roomtype selectByPrimaryKey(Integer roomtypeid);

    int updateByExampleSelective(@Param("record") Roomtype record, @Param("example") RoomtypeExample example);

    int updateByExample(@Param("record") Roomtype record, @Param("example") RoomtypeExample example);

    int updateByPrimaryKeySelective(Roomtype record);

    int updateByPrimaryKey(Roomtype record);
}