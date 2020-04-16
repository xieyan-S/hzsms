package cn.edu.hzvtc.dao;

import java.sql.SQLException;
import java.text.ParseException;

import cn.edu.hzvtc.entity.MessageInfo;
import cn.edu.hzvtc.util.PaginationUtil;

public interface MessageInfoDao {
	//添加短消息
	public int saveMessageInfo(MessageInfo messageinfo) throws SQLException ;

	//获取短消息条数
	public int getMsgsCount(Integer userid,Integer msgstate,Integer arrow)
		throws SQLException;
	
	//获取短消息
	public PaginationUtil<MessageInfo> getMsgs(Integer userid,
			Integer msgstate,Integer arrow,int pageNo,int pageSize)
			throws SQLException;
	
	//获取一个短消息
	public MessageInfo getMsgById(Integer msgId) throws SQLException,
			ParseException;
	
	//更新短消息状态
	public int updateMessageState(Integer msgId) throws SQLException;
	
	//回复短消息
	public int updateMessageInfo(MessageInfo messageinfo) throws SQLException;
	
	//删除短消息
	public int deleteMessageInfo(Integer msgId) throws SQLException;
}
