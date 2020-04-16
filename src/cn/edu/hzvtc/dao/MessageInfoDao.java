package cn.edu.hzvtc.dao;

import java.sql.SQLException;
import java.text.ParseException;

import cn.edu.hzvtc.entity.MessageInfo;
import cn.edu.hzvtc.util.PaginationUtil;

public interface MessageInfoDao {
	//��Ӷ���Ϣ
	public int saveMessageInfo(MessageInfo messageinfo) throws SQLException ;

	//��ȡ����Ϣ����
	public int getMsgsCount(Integer userid,Integer msgstate,Integer arrow)
		throws SQLException;
	
	//��ȡ����Ϣ
	public PaginationUtil<MessageInfo> getMsgs(Integer userid,
			Integer msgstate,Integer arrow,int pageNo,int pageSize)
			throws SQLException;
	
	//��ȡһ������Ϣ
	public MessageInfo getMsgById(Integer msgId) throws SQLException,
			ParseException;
	
	//���¶���Ϣ״̬
	public int updateMessageState(Integer msgId) throws SQLException;
	
	//�ظ�����Ϣ
	public int updateMessageInfo(MessageInfo messageinfo) throws SQLException;
	
	//ɾ������Ϣ
	public int deleteMessageInfo(Integer msgId) throws SQLException;
}
