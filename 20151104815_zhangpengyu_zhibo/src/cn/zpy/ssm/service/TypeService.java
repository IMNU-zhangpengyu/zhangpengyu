package cn.zpy.ssm.service;

import java.util.List;

import cn.zpy.ssm.po.TypeCustom;
import cn.zpy.ssm.po.TypeQueryVo;

/**
 * ����service
 *
 */
public interface TypeService {

	//��ѯ�б�
	public List<TypeCustom> findTypeList(TypeQueryVo typeQueryVo)throws Exception;
	
	//����Id��ѯ��Ϣ
	public TypeCustom findTypeById(Integer id) throws Exception;
	
	//�޸���Ϣ
	public void updateType(Integer id,TypeCustom typeCustom) throws Exception;
	
}
