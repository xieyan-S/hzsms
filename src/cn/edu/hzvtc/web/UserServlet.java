package cn.edu.hzvtc.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hzvtc.biz.UserInfoBiz;
import cn.edu.hzvtc.biz.impl.UserInfoBizImpl;
import cn.edu.hzvtc.entity.UserInfo;

public class UserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserInfoBiz userInfoBiz = null;
		String action = "";
				
		try {
			action=request.getParameter("action");
			userInfoBiz = new UserInfoBizImpl();
			
			if("registe".equals(action)){//ע��
				//�˴�Ϊ����ɵ��û�ע�Ṧ��
				userInfoBiz = new UserInfoBizImpl();
				UserInfo userinfo = new UserInfo();
				userinfo.setUserName(request.getParameter("username"));
				userinfo.setUserPassword(request.getParameter("userpassword"));
				userinfo.setUserEmail(request.getParameter("useremail"));
				int result = userInfoBiz.doInsertUserInfo(userinfo);
				if(result==-1){
					request.setAttribute("error", "�û����Ѿ���ע��!");
					request.setAttribute("userinfo", userinfo);
					request.getRequestDispatcher("register.jsp").forward(
							request, response);
				}else if(result==0){
					request.setAttribute("error", "�û���ע��ʧ��!");
					request.setAttribute("userinfo", userinfo);
					request.getRequestDispatcher("register.jsp").forward(
							request, response);
				}else{
					request.setAttribute("msg", 1);
					request.getRequestDispatcher("login.jsp").forward(
							request, response);
				}
			}else if("login".equals(action)){//��¼
				UserInfo userinfo = new UserInfo();
				userinfo.setUserName(request.getParameter("username"));
				userinfo.setUserPassword(request.getParameter("userpassword"));
				String verifycode = request.getParameter("verifycode");
				
				if(!verifycode.equals(request.getSession().getAttribute(
						"verifycode"))){
					request.setAttribute("error", "��֤���������!");
					request.setAttribute("userinfo", userinfo);
					request.getRequestDispatcher("login.jsp").forward(
							request, response);
				}else {
					UserInfo loginUser = userInfoBiz.getUserInfo(userinfo);
					if(loginUser!=null){
						request.getSession().setAttribute("loginuser", 
								loginUser);
						response.sendRedirect("msg?action=main");
						/*response.sendRedirect("main.jsp");*/
					}else{
						request.setAttribute("error", "�û����������������!");
						request.setAttribute("userinfo", userinfo);
						request.getRequestDispatcher("login.jsp").forward(
								request, response);
					}
				}
				
			}else if("pwdupdate".equals(action)){//�����޸�
				String oldpassword=request.getParameter("oldpassword");
				String newpassword=request.getParameter("newpassword");
				if(request.getSession().getAttribute("loginuser")==null){
					request.setAttribute("msg", -2);
					request.getRequestDispatcher("login.jsp").forward(
							request, response);
				}else{
					UserInfo userinfo=(UserInfo)request.getSession().getAttribute("loginuser");
					if(!oldpassword.equals(userinfo.getUserPassword())){
						request.setAttribute("error", "�������������!");
						request.getRequestDispatcher("pwdupdate.jsp").forward(
								request, response);
					}else{
						userinfo.setUserPassword(newpassword);
						int result = userInfoBiz.doUpdateUserInfo(userinfo);
						if(result==-1){
							request.setAttribute("msg", -2);
							request.getRequestDispatcher("login.jsp").forward(
									request, response);
						}else if(result==0){
							request.setAttribute("error", "�����޸�ʧ��!");
							request.getRequestDispatcher("pwdupdate.jsp").forward(
									request, response);
						}else{
							request.getSession().setAttribute("loginuser", userinfo);
							request.setAttribute("msg", 1);
							request.getRequestDispatcher("msg?action=main").forward(
									request, response);
							/*request.getRequestDispatcher("main.jsp").forward(
									request, response);*/
						}
					}
				}
				
				
				
			}else if("logout".equals(action)){//�˳�
				request.getSession().removeAttribute("loginuser");
				response.sendRedirect("login.jsp");
			}else{
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("login.jsp").forward(
						request, response);
			}
		} catch (SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, 
					response);
		}
		
				
			


	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
