package cn.zpy.ssm.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.zpy.ssm.po.TypeCustom;
import cn.zpy.ssm.po.TypeQueryVo;
import cn.zpy.ssm.service.TypeService;

/**
 * �����controller
 */
@Controller
//Ϊ�˶�url���з������ �����������ﶨ���·�������շ���url�Ǹ�·��+��·��
//���磺�����б�/Type/queryType.action
@RequestMapping("/type")
public class TypeController {

	@Autowired
	private TypeService typeService;

	// �����ѯ
	@RequestMapping("/queryType")
	public ModelAndView queryType(HttpServletRequest request,TypeQueryVo typeQueryVo) throws Exception {
		//����forward��request�Ƿ���Թ���
		
		//System.out.println(request.getParameter("id"));

		// ����service���� ���ݿ⣬��ѯ�����б�
		List<TypeCustom> typeList = typeService.findTypeList(typeQueryVo);
		
		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// �൱ ��request��setAttribut����jspҳ����ͨ��typeListȡ����
		modelAndView.addObject("typeList", typeList);

		// ָ����ͼ
		// �±ߵ�·�����������ͼ������������jsp·����ǰ׺��jsp·���ĺ�׺���޸�Ϊ
		// modelAndView.setViewName("/WEB-INF/jsp/type/typeList.jsp");
		// �ϱߵ�·�����ÿ��Բ��ڳ�����ָ��jsp·����ǰ׺��jsp·���ĺ�׺
		modelAndView.setViewName("type/typeList");

		return modelAndView;

	}

	//������Ϣ�޸�ҳ����ʾ
	//@RequestMapping("/editType")
	//����http���󷽷�������post��get
//	@RequestMapping(value="/editType",method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView editType()throws Exception {
//		
//		//����service���ݷ���id��ѯ������Ϣ
//		TypeCustom typeCustom = typeService.findTypeById(1);
//		
//		// ����ModelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		
//		//��������Ϣ�ŵ�model
//		modelAndView.addObject("typeCustom", typeCustom);
//		
//		//�����޸�ҳ��
//		modelAndView.setViewName("type/editType");
//		
//		return modelAndView;
//	}
	
	@RequestMapping(value="/editType",method={RequestMethod.POST,RequestMethod.GET})
	//@RequestParam���ָ��request����������ƺ��βν��а󶨡�
	//ͨ��required����ָ�������Ƿ����Ҫ����
	//ͨ��defaultValue��������Ĭ��ֵ�����id����û�д��룬��Ĭ��ֵ���βΰ󶨡�
	public String editType(Model model,@RequestParam(value="id",required=true) Integer type_id)throws Exception {
		
		//����service���ݷ���id��ѯ������Ϣ
		TypeCustom typeCustom = typeService.findTypeById(type_id);
		
		//ͨ���β��е�model��model���ݴ���ҳ��
		//�൱��modelAndView.addObject����
		model.addAttribute("typeCustom", typeCustom);
		
		return "type/editType";
	}
	
	//������Ϣ�޸��ύ
	@RequestMapping("/editTypeSubmit")
	public String editTypeSubmit(
			Model model,
			HttpServletRequest request,Integer typeid,
			 @Validated TypeCustom typeCustom,BindingResult bindingResult,
			MultipartFile pic 
			)throws Exception {
		
		// ��ȡУ�������Ϣ
		if (bindingResult.hasErrors()) {
			// ���������Ϣ
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// ���������Ϣ
				System.out.println(objectError.getDefaultMessage());

			}
			// ��������Ϣ����ҳ��
			model.addAttribute("allErrors", allErrors);
			
			
			//����ֱ��ʹ��model���ύpojo���Ե�ҳ��
			//model.addAttribute("type", typeCustom);
			
			// �������µ���Ʒ�޸�ҳ��
			return "type/editType";
		}
		//ԭʼ����
				String originalFilename = pic.getOriginalFilename();
		//�ϴ�ͼƬ
		if(pic!=null && originalFilename!=null && originalFilename.length()>0){
			
			//�洢ͼƬ������·��
			String pic_path = "D:\\git_work\\zhangpengyu\\temp\\";
			
			
			//�µ�ͼƬ����
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//��ͼƬ
			File newFile = new File(pic_path+newFileName);
			
			//���ڴ��е�����д�����
			pic.transferTo(newFile);
			
			//����ͼƬ����д��typeCustom��
			//typeCustom.setPic(newFileName);
			
		}
		
		//����service���·�����Ϣ��ҳ����Ҫ��������Ϣ�����˷���
		typeService.updateType(typeid, typeCustom);
		
		//�ض��򵽷����ѯ�б�
//		return "redirect:queryType.action";
		//ҳ��ת��
		//return "forward:queryType.action";
		return "success";
	}

	@RequestMapping(value="/editType",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView regist(Integer typeid,TypeCustom typeCustom)throws Exception {
		
		typeService.regist(typeid, typeCustom);
		// ����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	

}
