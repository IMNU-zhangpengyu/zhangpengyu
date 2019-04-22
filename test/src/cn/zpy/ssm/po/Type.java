package cn.zpy.ssm.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Type {
	
	 //非空校验
    @NotNull(message="{type.typeid.isNUll}")
    private Integer typeid;

    //校验名称
    //message是提示校验出错显示的信息
    //groups：此校验属于哪个分组，groups可以定义多个分组
    @Size(min=1,max=4,message="{type.type.length.error}")
    private String type;

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}