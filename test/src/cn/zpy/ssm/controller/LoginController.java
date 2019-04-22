package cn.zpy.ssm.controller;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	// 登陆
	@RequestMapping("/login")
	public String login(HttpSession session, String username, String password) throws Exception {

		// 调用service进行用户身份验证
		// ...
	
		// 在session中保存用户身份信息
		session.setAttribute("username", username);
		
		Cookie cookie = new Cookie("JID",session.getId());
		cookie.setMaxAge(60*60);
		
		// 重定向到商品列表页面
		return "redirect:/type/queryType.action";
	}

	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// 清除session
		session.invalidate();

		// 重定向到商品列表页面
		return "redirect:/type/queryType.action";
	}
	
	@RequestMapping("/userCenter")
	public String userCenter(HttpSession session, String username, String password) throws Exception {

		// 调用service进行用户身份验证
		// ...

		// 在session中保存用户身份信息
		session.setAttribute("username", username);
		// 重定向到商品列表页面
		return "userCenter";
	}
	
}
