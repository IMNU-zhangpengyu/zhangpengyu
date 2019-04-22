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
 * 分类的controller
 */
@Controller
//为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
//比如：分类列表：/Type/queryType.action
@RequestMapping("/type")
public class TypeController {

	@Autowired
	private TypeService typeService;

	// 分类查询
	@RequestMapping("/queryType")
	public ModelAndView queryType(HttpServletRequest request,TypeQueryVo typeQueryVo) throws Exception {
		//测试forward后request是否可以共享
		
		//System.out.println(request.getParameter("id"));

		// 调用service查找 数据库，查询分类列表
		List<TypeCustom> typeList = typeService.findTypeList(typeQueryVo);
		
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过typeList取数据
		modelAndView.addObject("typeList", typeList);

		// 指定视图
		// 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
		// modelAndView.setViewName("/WEB-INF/jsp/type/typeList.jsp");
		// 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
		modelAndView.setViewName("type/typeList");

		return modelAndView;

	}

	//分类信息修改页面显示
	//@RequestMapping("/editType")
	//限制http请求方法，可以post和get
//	@RequestMapping(value="/editType",method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView editType()throws Exception {
//		
//		//调用service根据分类id查询分类信息
//		TypeCustom typeCustom = typeService.findTypeById(1);
//		
//		// 返回ModelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		
//		//将分类信息放到model
//		modelAndView.addObject("typeCustom", typeCustom);
//		
//		//分类修改页面
//		modelAndView.setViewName("type/editType");
//		
//		return modelAndView;
//	}
	
	@RequestMapping(value="/editType",method={RequestMethod.POST,RequestMethod.GET})
	//@RequestParam里边指定request传入参数名称和形参进行绑定。
	//通过required属性指定参数是否必须要传入
	//通过defaultValue可以设置默认值，如果id参数没有传入，将默认值和形参绑定。
	public String editType(Model model,@RequestParam(value="id",required=true) Integer type_id)throws Exception {
		
		//调用service根据分类id查询分类信息
		TypeCustom typeCustom = typeService.findTypeById(type_id);
		
		//通过形参中的model将model数据传到页面
		//相当于modelAndView.addObject方法
		model.addAttribute("typeCustom", typeCustom);
		
		return "type/editType";
	}
	
	//分类信息修改提交
	@RequestMapping("/editTypeSubmit")
	public String editTypeSubmit(
			Model model,
			HttpServletRequest request,Integer typeid,
			 @Validated TypeCustom typeCustom,BindingResult bindingResult,
			MultipartFile pic 
			)throws Exception {
		
		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				System.out.println(objectError.getDefaultMessage());

			}
			// 将错误信息传到页面
			model.addAttribute("allErrors", allErrors);
			
			
			//可以直接使用model将提交pojo回显到页面
			//model.addAttribute("type", typeCustom);
			
			// 出错重新到商品修改页面
			return "type/editType";
		}
		//原始名称
				String originalFilename = pic.getOriginalFilename();
		//上传图片
		if(pic!=null && originalFilename!=null && originalFilename.length()>0){
			
			//存储图片的物理路径
			String pic_path = "D:\\git_work\\zhangpengyu\\temp\\";
			
			
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(pic_path+newFileName);
			
			//将内存中的数据写入磁盘
			pic.transferTo(newFile);
			
			//将新图片名称写到typeCustom中
			//typeCustom.setPic(newFileName);
			
		}
		
		//调用service更新分类信息，页面需要将分类信息传到此方法
		typeService.updateType(typeid, typeCustom);
		
		//重定向到分类查询列表
//		return "redirect:queryType.action";
		//页面转发
		//return "forward:queryType.action";
		return "success";
	}

	@RequestMapping(value="/editType",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView regist(Integer typeid,TypeCustom typeCustom)throws Exception {
		
		typeService.regist(typeid, typeCustom);
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	

}
