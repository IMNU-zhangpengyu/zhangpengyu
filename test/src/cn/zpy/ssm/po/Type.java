package cn.zpy.ssm.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Type {
	
	 //�ǿ�У��
    @NotNull(message="{type.typeid.isNUll}")
    private Integer typeid;

    //У������
    //message����ʾУ�������ʾ����Ϣ
    //groups����У�������ĸ����飬groups���Զ���������
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