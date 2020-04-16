package cn.edu.hzvtc.biz.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.hzvtc.biz.MessageInfoBiz;
import cn.edu.hzvtc.dao.MessageInfoDao;
import cn.edu.hzvtc.dao.impl.MessageInfoDaoImpl;
import cn.edu.hzvtc.entity.MessageInfo;
import cn.edu.hzvtc.entity.UserInfo;
import cn.edu.hzvtc.util.DatabaseUtil;
import cn.edu.hzvtc.util.PaginationUtil;

public class MessageInfoBizImpl implements MessageInfoBiz {
	private Connection conn=null;
	private MessageInfoDao messageInfoDao=null;

	public MessageInfoBizImpl() throws SQLException{
		conn =DatabaseUtil.getConnection();
		messageInfoDao = new MessageInfoDaoImpl(conn);
	}
	//����û�
	@Override
	public int doInsertMessageInfo(MessageInfo messageinfo) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		messageinfo.setMsgState(1);
		messageinfo.setMsgCreateDate(sdf.format(new Date()));
		int result = messageInfoDao.saveMessageInfo(messageinfo);
		DatabaseUtil.closeAll(conn, null, null);
		return result;
	}
	//��ȡ����Ϣ����
	@Override
	public int getMsgsCount(UserInfo userinfo, Integer msgstate, Integer arrow)
			throws SQLException {
		
		return messageInfoDao.getMsgsCount(userinfo.getUserId(), msgstate,
				arrow);
	}
	//��ȡ����Ϣ
	@Override
	public PaginationUtil<MessageInfo> getMsgs(UserInfo userinfo,
			Integer msgstate, Integer arrow, int pageNo, int pageSize)
			throws SQLException {
		
		return messageInfoDao.getMsgs(userinfo.getUserId(), msgstate, arrow, 
				pageNo, pageSize);
	}
	//��ȡһ������Ϣ
	@Override
	public MessageInfo getMsgById(Integer msgId) throws SQLException,
			ParseException {
		
		return messageInfoDao.getMsgById(msgId);
	}
	//���¶���Ϣ״̬
	@Override
	public int updateMessageState(Integer msgId) throws SQLException {
		
		return messageInfoDao.updateMessageState(msgId);
	}
	//�ظ�����Ϣ
	@Override
	public int updateMessageInfo(MessageInfo messageinfo) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		messageinfo.setMsgReplyDate(sdf.format(new Date()));
		int result = messageInfoDao.updateMessageInfo(messageinfo);
		DatabaseUtil.closeAll(conn, null, null);
		return result;
	}
	//ɾ������Ϣ
	@Override
	public int deleteMessageInfo(Integer msgId) throws SQLException {
		
		return messageInfoDao.deleteMessageInfo(msgId);
	}

}
