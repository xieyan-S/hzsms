package cn.edu.hzvtc.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hzvtc.biz.MessageInfoBiz;
import cn.edu.hzvtc.biz.UserInfoBiz;
import cn.edu.hzvtc.biz.impl.MessageInfoBizImpl;
import cn.edu.hzvtc.biz.impl.UserInfoBizImpl;
import cn.edu.hzvtc.entity.MessageInfo;
import cn.edu.hzvtc.entity.UserInfo;
import cn.edu.hzvtc.util.PaginationUtil;

public class MsgServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MsgServlet() {
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
		String action="";
		MessageInfoBiz messageInfoBiz = null;
		UserInfo userinfo = null;
		
		try {
			if(request.getSession().getAttribute("loginuser")==null){
				request.setAttribute("msg", -2);
				request.getRequestDispatcher("login.jsp").forward(
						request, response);
				return;
			}
			
			action=request.getParameter("action");
			messageInfoBiz = new MessageInfoBizImpl();
			userinfo=(UserInfo)request.getSession()
					.getAttribute("loginuser");
			
			if("new".equals(action)){//������Ϣҳ��
				UserInfoBiz userInfoBiz = new UserInfoBizImpl();
				List<UserInfo> userinfos = userInfoBiz.getAllUserInfo(userinfo);
				request.setAttribute("userinfos",userinfos);
				request.getRequestDispatcher("msgnew.jsp").forward(
						request, response);
			}else if("send".equals(action)){//������Ϣ
				MessageInfo messageInfo = new MessageInfo();
				messageInfo.setMsgSender(userinfo);
				UserInfo receiver = new UserInfo();
				receiver.setUserId(Integer.parseInt(request
						.getParameter("receiveuser")));
				messageInfo.setMsgReceiver(receiver);
				messageInfo.setMsgTitle(request.getParameter("msgtitle"));
				messageInfo.setMsgContent(request.getParameter("msgcontent"));
				if("1".equals(request.getParameter("needreply"))){
					messageInfo.setMsgNeedReply(true);
				}else{
					messageInfo.setMsgNeedReply(false);
				}
				int result = messageInfoBiz.doInsertMessageInfo(messageInfo);
				if(result==0){
					request.setAttribute("error", "����Ϣ����ʧ��!");
					request.setAttribute("messageinfo", messageInfo);
					request.getRequestDispatcher("msg?action=new").forward(
					request, response);
					/*request.getRequestDispatcher("msgnew.jsp").forward(
					request, response);*/
				}else{
					request.setAttribute("msg", 2);
					request.getRequestDispatcher("msg?action=main").forward(
							request, response);
					/*request.getRequestDispatcher("main.jsp").forward(
							request, response);*/
				}
			}else if("main".equals(action)){//��ҳ
				int sendcount = messageInfoBiz.getMsgsCount(userinfo, 1, 1);//����δ��
				int recvcount = messageInfoBiz.getMsgsCount(userinfo, 1, -1);//����δ��
				
				request.setAttribute("sendcount", sendcount);
				request.setAttribute("recvcount", recvcount);
				request.getRequestDispatcher("main.jsp").forward(request, 
						response);
			}else if("list".equals(action)){//�鿴��Ϣ
				if(request.getParameter("arrow")==null
						||!request.getParameter("arrow").matches(
								"^[-\\+]?[\\d]*$")){
					request.setAttribute("msg", -1);
					request.getRequestDispatcher("login.jsp").forward(request, 
							response);
					return;
				}
				Integer arrow = Integer.parseInt(request.getParameter("arrow"));
				if(arrow!=1&&arrow!=-1){
					request.setAttribute("msg", -1);
					request.getRequestDispatcher("login.jsp").forward(request, 
							response);
					return;
				}
				Integer state = 0;
				if(request.getParameter("state")!=null
						&& request.getParameter("state").matches("[0-9]+")){
					state = Integer.parseInt(request.getParameter("state"));
				}
				Integer pageNo = 1;
				if(request.getParameter("pageNo")!=null
						&& request.getParameter("pageNo").matches("[0-9]+")){
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				}
				Integer pageSize = 5;
				if(request.getParameter("pageSize")!=null
						&& request.getParameter("pageSize").matches("[0-9]+")){
					pageSize = Integer.parseInt(request.getParameter("pageSize"));
				}
				
				PaginationUtil<MessageInfo> pageData = messageInfoBiz.getMsgs(
						userinfo, state, arrow, pageNo, pageSize);
				request.setAttribute("arrow", arrow);
				request.setAttribute("state", state);
				request.setAttribute("pageData", pageData);
				request.getRequestDispatcher("msglist.jsp").forward(request, 
						response);
				
			}else if("detail".equals(action)){//�鿴һ����Ϣ
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				
				if(request.getParameter("msgid") == null
						||!request.getParameter("msgid").matches("[0-9]+")){
					request.setAttribute("msg", -1);
					request.getRequestDispatcher("login.jsp").forward(request, 
							response);
					return;
				}
				Integer msgId = Integer.parseInt(request.getParameter("msgid"));
				
				PrintWriter out = response.getWriter();
				MessageInfo messageinfo = messageInfoBiz.getMsgById(msgId);
				
				if(messageinfo !=null){
					out.write("<div class='modal-dialog'><div class='modal-content'><div class='modal-header'>");
					if(messageinfo.getMsgReceiver().getUserId() == userinfo
							.getUserId()){
						out.write("<h4 class='modal-title' id='myModalLabel'>����"
								+ messageinfo.getMsgSender().getUserName()
								+ " ����Ϣ</h4></div>");
					}else{
						out.write("<h4 class='modal-title' id='myModalLabel'>����"
								+ messageinfo.getMsgReceiver().getUserName()
								+ " ����Ϣ</h4></div>");
					}
					out.write("<div class='modal-body'><div><p><span class='text-right col-sm-3'>����</span> <span>"
							+messageinfo.getMsgTitle()+"</span></p>");
					out.write("<p><span class='text-right col-sm-3'>����ʱ��</span><span>"
							+messageinfo.getMsgCreateDate()+"</span></p>");
					out.write("<p><span class='text-right col-sm-3'>״̬</span><span>");
					if(messageinfo.getMsgState()==1){
						out.write("δ��");
					}else if(messageinfo.getMsgState()==2){
						out.write("�Ѷ�");
						if(messageinfo.getMsgNeedReply()){
							out.write(" / δ�ظ�");
						}
					}else{
						out.write("�ѻظ�");
					}
					out.write("</span></p>");
					out.write("<p><span class='text-right col-sm-3'>����</span><span>"
							+messageinfo.getMsgContent()+"</span></p>");
					if(messageinfo.getMsgState()==3){
						out.write("<div><hr class='simple' color='#6f5499' /></div>");
						out.write("<p><span class='text-right col-sm-3'>�ظ�ʱ��</span><span>"
								+messageinfo.getMsgReplyDate()+"</span></p>");
						out.write("<p><span class='text-right col-sm-3'>�ظ�</span><span>"
								+messageinfo.getMsgReply()+"</span></p>");
					}
					out.write("</div></div><div class='modal-footer'>");
					
					if(messageinfo.getMsgReceiver().getUserId() == userinfo
							.getUserId()&&messageinfo.getMsgState()==1){
						messageInfoBiz.updateMessageState(msgId);
						String href = "msg?action=list";
						String queryString = request.getQueryString();
						String[] qs = queryString.split("&");
						for(String s:qs){
							if(!s.contains("action")){
								href = href + "&" +s;
							}
						}
						out.write("<a href='"
								+href
								+"'class='btn btn-default' onClick='document.location.reload()'><span class='glyphicon glyphicon-remove'></span> �ر�</a>");
						/*out.write("<a class='btn btn-default' onClick='document.location.reload()'><span class='glyphicon glyphicon-remove'></span> �ر�</a>");*/
					}else{
						out.write("<button type='button' class='btn btn-default' data-dismiss='modal'>");
						out.write("<span class='glyphicon glyphicon-remove'></span> �ر�</button>");
					}
					out.write("</div></div>");
				}else{
					out.write("<div class='modal-dialog'><div class='modal-content'><div class='modal-header'>");
					out.write("<h4 class='modal-title' id='myModalLabel'>�鿴����Ϣ</h4></div>");
					out.write("<div class='modal-body'><div><p>�޶���Ϣ��Ϣ!</p></div></div>");
					out.write("<div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>");
					out.write("<span class='glyphicon glyphicon-remove'></span> �ر�</button>");
					out.write("</div></div>");
				}
				out.close();
			}else if("reply".equals(action)){//�ظ���Ϣ
				if(request.getParameter("msgid") == null
						||!request.getParameter("msgid").matches("[0-9]+")){
					request.setAttribute("msg", -1);
					request.getRequestDispatcher("login.jsp").forward(request, 
							response);
					return;
				}
				Integer msgId = Integer.parseInt(request.getParameter("msgid"));
				
				MessageInfo messageinfo = messageInfoBiz.getMsgById(msgId);
				if(messageinfo !=null&&messageinfo.getMsgNeedReply()
						&&messageinfo.getMsgState()<3){
					request.setAttribute("messageinfo", messageinfo);
					if(messageinfo.getMsgReceiver().getUserId() == userinfo
							.getUserId()&&messageinfo.getMsgState()==1){
						messageInfoBiz.updateMessageState(msgId);
					}
					String href = "msg?action=list";
					String queryString = request.getQueryString();//msg?action=list&state=1&arrow=-1 ����
																  //msg?action=reply&msgid=20&arrow=-1&state=1&pageNo=1&pageSize=5
					String[] qs = queryString.split("&");
					for(String s:qs){
						if(!s.contains("action")&&!s.contains("msgid")){//����&&!s.contains("msgid")
							href += "&" +s;
						}
					}
					request.setAttribute("href", href);			 
																 //msg?action=list&msgid=20&arrow=-1&state=1&pageNo=1&pageSize=5
					request.getRequestDispatcher("msgreply.jsp").forward(
							request,response);
				}	
				else{
					request.setAttribute("msg", 3);
					request.getRequestDispatcher("msg?action=main").forward(request, 
							response);
				}
				
			}else if("update".equals(action)){//�ظ�����
				if(request.getParameter("msgid") == null
						||!request.getParameter("msgid").matches("[0-9]+")){
					request.setAttribute("msg", -1);
					request.getRequestDispatcher("login.jsp").forward(request, 
							response);
					return;
				}
				Integer msgId = Integer.parseInt(request.getParameter("msgid"));
				String href = request.getParameter("href");//msg?action=list&msgid=20&arrow=-1&state=1&pageNo=1&pageSize=5
				
				MessageInfo messageinfo = messageInfoBiz.getMsgById(msgId);
				messageinfo.setMsgReply(request.getParameter("reply"));
				messageinfo.setMsgState(3);
				
				int result = messageInfoBiz.updateMessageInfo(messageinfo);
				if(result==0){
					request.setAttribute("error", "����Ϣ�ظ�ʧ��!");
					request.setAttribute("messageinfo", messageinfo);
					request.getRequestDispatcher("msgreply.jsp").forward(
							request, response);
				}else{
					request.setAttribute("msg", 1);
					request.getRequestDispatcher(href).forward(
							request, response);
				}
			}else if("delete".equals(action)){//ɾ����Ϣ
				if(request.getParameter("msgid") == null
						||!request.getParameter("msgid").matches("[0-9]+")){
					request.setAttribute("msg", -1);
					request.getRequestDispatcher("login.jsp").forward(request, 
							response);
					return;
				}
				Integer msgId = Integer.parseInt(request.getParameter("msgid"));
				
				String href = "msg?action=list";
				String queryString = request.getQueryString();
				String[] qs = queryString.split("&");
				for(String s:qs){
					if(!s.contains("action")){
						href = href + "&" +s;
					}
				}
				
				int result=messageInfoBiz.deleteMessageInfo(msgId);
				if(result==0){
					request.setAttribute("error", "����Ϣɾ��ʧ��!");
					request.getRequestDispatcher(href).forward(
							request, response);
				}else{
					request.setAttribute("msg", 2);
					request.getRequestDispatcher(href).forward(
							request, response);
							/*request.getRequestDispatcher("msg?action=list&arrow=-1").forward(
							request, response);*/
				}
				
				
				
			}else{
				request.setAttribute("msg", -1);
				request.getRequestDispatcher("login.jsp").forward(
						request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("error.jsp").forward(request, 
					response);
		} catch (ParseException e) {
			request.setAttribute("error", e);
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
