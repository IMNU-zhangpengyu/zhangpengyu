package cn.zpy.ssm.mapper;

import cn.zpy.ssm.po.Wechat;
import cn.zpy.ssm.po.WechatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WechatMapper {
    int countByExample(WechatExample example);

    int deleteByExample(WechatExample example);

    int deleteByPrimaryKey(Integer wid);

    int insert(Wechat record);

    int insertSelective(Wechat record);

    List<Wechat> selectByExample(WechatExample example);

    Wechat selectByPrimaryKey(Integer wid);

    int updateByExampleSelective(@Param("record") Wechat record, @Param("example") WechatExample example);

    int updateByExample(@Param("record") Wechat record, @Param("example") WechatExample example);

    int updateByPrimaryKeySelective(Wechat record);

    int updateByPrimaryKey(Wechat record);
}