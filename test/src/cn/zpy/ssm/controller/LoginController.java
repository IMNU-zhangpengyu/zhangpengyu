package cn.zpy.ssm.controller;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	// ��½
	@RequestMapping("/login")
	public String login(HttpSession session, String username, String password) throws Exception {

		// ����service�����û������֤
		// ...
	
		// ��session�б����û������Ϣ
		session.setAttribute("username", username);
		
		Cookie cookie = new Cookie("JID",session.getId());
		cookie.setMaxAge(60*60);
		
		// �ض�����Ʒ�б�ҳ��
		return "redirect:/type/queryType.action";
	}

	// �˳�
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// ���session
		session.invalidate();

		// �ض�����Ʒ�б�ҳ��
		return "redirect:/type/queryType.action";
	}
	
	@RequestMapping("/userCenter")
	public String userCenter(HttpSession session, String username, String password) throws Exception {

		// ����service�����û������֤
		// ...

		// ��session�б����û������Ϣ
		session.setAttribute("username", username);
		// �ض�����Ʒ�б�ҳ��
		return "userCenter";
	}
	
}
