package cn.zpy.ssm.action;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.zpy.ssm.service.UserService;
import cn.zpy.ssm.util.BaseServlet;
import cn.zpy.ssm.po.Local;
import cn.zpy.ssm.po.User;

public class UserServlet extends BaseServlet {
	/**
	 * ���session�Ƿ����
	 * @throws IOException 
	 */
	public String check(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// ��session�л���û�����Ϣ
		User existUser = (User) req.getSession().getAttribute("existUser");
		// �ж�session�е��û��Ƿ����
		if(existUser == null){
			// ��¼����Ϣ�Ѿ�������!
			resp.getWriter().println("1");
		}else{
			// ��¼����Ϣû�й���
			resp.getWriter().println("2");
		}
		return null;
	}
	
	/**
	 *  �˳�������
	 * @throws IOException 
	 */
	public String exit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// ���session����
		HttpSession session = request.getSession();
		// ��session����.
		session.invalidate();
		// ҳ��ת��.
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return null;
	}
	
	/**
	 * ������������
	 * @throws IOException 
	 */
	public String sendMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 1.�������� ��
		System.out.println("sendMessage invoke....");
		String from = req.getParameter("from"); // ������
		String face = req.getParameter("face"); // ����
		String to = req.getParameter("to"); // ������
		String color = req.getParameter("color"); // ������ɫ
		String content = req.getParameter("content"); // ��������
		// ����ʱ�� ���������ʹ��SimpleDateFormat
		String sendTime = new Date().toLocaleString(); // ����ʱ��
		// ���ServletContext����.
		ServletContext application = getServletContext();
		//  ��ServletContext�л�ȡ��Ϣ
		String sourceMessage = (String) application.getAttribute("message");
		// ƴ�ӷ��Ե�����:xx �� yy ˵ xxx
		sourceMessage += "<font color='blue'><strong>" + from
				+ "</strong></font><font color='#CC0000'>" + face
				+ "</font>��<font color='green'>[" + to + "]</font>˵��"
				+ "<font color='" + color + "'>" + content + "</font>��"
				+ sendTime + "��<br>";
		// ����Ϣ���뵽application�ķ�Χ
		application.setAttribute("message", sourceMessage);
		return getMessage(req, resp);
	}
	
	/**
	 * ��ȡ��Ϣ�ķ���
	 * @throws IOException 
	 */
	public String getMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String message = (String) getServletContext().getAttribute("message");
		if(message != null){
			resp.getWriter().println(message);
		}
		return null;
	}
	/**
	 * ���˵Ĺ���
	 * @throws IOException 
	 */
/*	public String kick(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 1.���ղ���
		int id = Integer.parseInt(req.getParameter("id"));
		// 2.����:��userMap�н��û���Ӧ��session����.
		// ���userMap����(�����б�)
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) getServletContext()
				.getAttribute("userMap");
		// �������û���Ӧ��session.���֪�����ĸ��û���? id�Ѿ����ݹ���.ȥ���ݿ��в�ѯ.
		// ��дuser��equals �� hashCode ���� ��ôֻҪ�û���id��ͬ����Ϊ��ͬһ���û�.
		User user = new User();
		user.setId(id);
		// ��map�����л���û��Ķ�Ӧ��session 
		HttpSession session = userMap.get(user);
		// ����session
		session.invalidate();
		// �ض���ҳ��
		resp.sendRedirect(req.getContextPath()+"/main.jsp");
		return null;
	}*/
	
	/**
	 * ��¼�Ĺ���
	 */
	public String login(HttpServletRequest req,HttpServletResponse resp){
		// ��������
		Map<String, String[]> map = req.getParameterMap();
		Local local = new Local();
		// ��װ����
		try {
			BeanUtils.populate(local, map);
			// ����Service�㴦������ 
			UserService us = new UserService();
			Local existLocal = us.login(local);
			if (existLocal == null) {
				// �û���¼ʧ��
				req.setAttribute("msg", "�û������������!");
				return "../jsp/index3.jsp";
			} else {
				// �û���¼�ɹ�
				// ��һ��BUG�Ľ��:�ڶ����û���¼��֮ǰ��session����!
				req.getSession().invalidate();
				
				// �ڶ���BUG�Ľ��:�ж��û��Ƿ��Ѿ���Map������,���ڣ��Ѿ����б���.������session.
				// ��õ�ServletCOntext�д��Map����.
				Map<Local, HttpSession> localMap = (Map<Local, HttpSession>) getServletContext()
						.getAttribute("localMap");
				// �ж��û��Ƿ��Ѿ���map������'
				if(localMap.containsKey(existLocal)){
					// ˵��map��������û�.
					HttpSession session = localMap.get(existLocal);
					// �����session����.
					session.invalidate();
				}
				
				// ʹ�ü�����:HttpSessionBandingListener������JavaBean�ϵļ�����.
				req.getSession().setAttribute("existLocal", existLocal);
				ServletContext application = getServletContext();

				String sourceMessage = "";

				if (null != application.getAttribute("message")) {
					sourceMessage = application.getAttribute("message")
							.toString();
				}

				sourceMessage += "ϵͳ���棺<font color='gray'>"
						+ existLocal.getUsername() + "�߽��������ң�</font><br>";
				application.setAttribute("message", sourceMessage);

				resp.sendRedirect(req.getContextPath() + "/main.jsp");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
