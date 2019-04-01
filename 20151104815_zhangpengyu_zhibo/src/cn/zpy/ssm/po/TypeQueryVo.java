package cn.zpy.ssm.po;
/**
 * 包装对象
 *
 */
public class TypeQueryVo {

	//分类信息
	private Type type;
	
	//为了系统可扩展性，对原始生成的po进行扩展
	private TypeCustom typeCustom;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public TypeCustom getTypeCustom() {
		return typeCustom;
	}

	public void setTypeCustom(TypeCustom typeCustom) {
		this.typeCustom = typeCustom;
	}

	
	
}
