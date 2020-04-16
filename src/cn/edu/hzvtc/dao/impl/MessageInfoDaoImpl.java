package cn.edu.hzvtc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hzvtc.dao.MessageInfoDao;
import cn.edu.hzvtc.dao.UserInfoDao;
import cn.edu.hzvtc.entity.MessageInfo;
import cn.edu.hzvtc.util.DatabaseUtil;
import cn.edu.hzvtc.util.PaginationUtil;

public class MessageInfoDaoImpl implements MessageInfoDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	public MessageInfoDaoImpl(Connection con) throws SQLException{
		this.conn=con;
	}
	//添加短消息
	@Override
	public int saveMessageInfo(MessageInfo messageinfo) throws SQLException {
		int result =0;
		String sql =null;
		
		sql="insert into msginfo(msgsenderid,msgreceiverid,msgtitle,"
				+"msgcontent,msgstate,msgcreatedate,msgneedreply,msgisdelete)values(?,?,?,?,?,?,?,false)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,messageinfo.getMsgSender().getUserId());
		pstmt.setInt(2,messageinfo.getMsgReceiver().getUserId());
		pstmt.setString(3,messageinfo.getMsgTitle());
		pstmt.setString(4,messageinfo.getMsgContent());
		pstmt.setInt(5,messageinfo.getMsgState());
		pstmt.setString(6,messageinfo.getMsgCreateDate());
		pstmt.setBoolean(7,messageinfo.getMsgNeedReply());
		
		result=pstmt.executeUpdate();
		DatabaseUtil.closeAll(null, pstmt, null);
		
		return result;
	}
	//获取短消息条数
	@Override
	public int getMsgsCount(Integer userid, Integer msgstate, Integer arrow)
			throws SQLException {
		int result=0;
		
		String sql="select count(*) from msginfo ";
		if(arrow==1){//发送方,arrow判定
			sql=sql+"where msgsenderid=? ";
		}else if(arrow==-1){//接收方
			sql=sql+"where msgreceiverid=? ";
		}
		if(msgstate!=0){
			sql=sql+"and msgstate=? ";
		}
		sql=sql+"and msgisdelete=false ";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, userid);
		if(msgstate!=0){
			pstmt.setInt(2, msgstate);//未读是1
		}
		
		rs=pstmt.executeQuery();
		while(rs.next())
			result=rs.getInt(1);
		
		DatabaseUtil.closeAll(null, pstmt, rs);
		return result;
	}
	//获取短消息
	@Override
	public PaginationUtil<MessageInfo> getMsgs(Integer userid, Integer msgstate,
			Integer arrow, int pageNo, int pageSize) 
			throws SQLException {
		PaginationUtil<MessageInfo> result = new PaginationUtil<MessageInfo>(pageNo,pageSize);
		result.setTotalCount(getMsgsCount(userid,msgstate,arrow));
		
		UserInfoDao userInfoDao = new UserInfoDaoImpl(conn);
		String sql = "select * from msginfo ";
		if(arrow==1){//发送方,arrow判定
			sql=sql+"where msgsenderid=? ";
		}else if(arrow==-1){//接收方
			sql=sql+"where msgreceiverid=? ";
		}
		if(msgstate!=0){
			sql=sql+"and msgstate=? ";
		}
		sql=sql+"and msgisdelete=false ";
		sql=sql+"order by msgcreatedate desc ";
		sql=sql+"limit ?,? ";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, userid);
		if(msgstate!=0){
			pstmt.setInt(2, msgstate);//未读是1
			pstmt.setInt(3, (pageNo-1)*pageSize);
			pstmt.setInt(4, pageSize);
		}else{
			pstmt.setInt(2, (pageNo-1)*pageSize);
			pstmt.setInt(3, pageSize);
		}
		List<MessageInfo> messageinfos=new ArrayList<MessageInfo>();
		rs = pstmt.executeQuery();
		while(rs.next()){
			MessageInfo messageinfo = new MessageInfo();
			messageinfo.setMsgId(rs.getInt("msgid"));
			messageinfo.setMsgSender(userInfoDao.getUserInfoByUserId(rs
					.getInt("msgsenderid")));
			messageinfo.setMsgReceiver(userInfoDao.getUserInfoByUserId(rs
					.getInt("msgReceiverid")));
			messageinfo.setMsgTitle(rs.getString("msgtitle"));
			messageinfo.setMsgContent(rs.getString("msgcontent"));
			messageinfo.setMsgState(rs.getInt("msgstate"));
			messageinfo.setMsgCreateDate(rs.getString("msgcreatedate"));
			messageinfo.setMsgNeedReply(rs.getBoolean("msgneedreply"));
			messageinfo.setMsgReply(rs.getString("msgreply"));
			messageinfo.setMsgReplyDate(rs.getString("msgreplydate"));
			messageinfos.add(messageinfo);
		}
		DatabaseUtil.closeAll(null, pstmt, rs);
		
		result.setItems(messageinfos);
		return result;
	}
	//获取一个短消息
	@Override
	public MessageInfo getMsgById(Integer msgId) throws SQLException,
			ParseException {
		MessageInfo result = null;
		UserInfoDao userInfoDao = new UserInfoDaoImpl(conn);
		
		String sql = "select * from msginfo where msgid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, msgId);
		
		rs = pstmt.executeQuery();
		while(rs.next()){
			result = new MessageInfo();
			result.setMsgId(rs.getInt("msgid"));
			result.setMsgSender(userInfoDao.getUserInfoByUserId(rs
					.getInt("msgsenderid")));
			result.setMsgReceiver(userInfoDao.getUserInfoByUserId(rs
					.getInt("msgReceiverid")));
			result.setMsgTitle(rs.getString("msgtitle"));
			result.setMsgContent(rs.getString("msgcontent"));
			result.setMsgState(rs.getInt("msgstate"));
			result.setMsgCreateDate(rs.getString("msgcreatedate"));
			result.setMsgNeedReply(rs.getBoolean("msgneedreply"));
			result.setMsgReply(rs.getString("msgreply"));
			result.setMsgReplyDate(rs.getString("msgreplydate"));
		}
		
		DatabaseUtil.closeAll(null, pstmt, rs);
		return result;
	}
	//更新短消息状态
	@Override
	public int updateMessageState(Integer msgId) throws SQLException {
		int result = 0;
		String sql = null;
		
		sql="update msginfo set msgstate=? where msgid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, 2);
		pstmt.setInt(2, msgId);
		
		result = pstmt.executeUpdate();
		DatabaseUtil.closeAll(null, pstmt, rs);
		
		return result;
	}
	//回复短消息
	@Override
	public int updateMessageInfo(MessageInfo messageinfo) throws SQLException {
		int result = 0;
		String sql = null;
		
		sql="update msginfo set msgstate=?,msgReply=?,msgReplyDate=? where msgid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, messageinfo.getMsgState());
		pstmt.setString(2, messageinfo.getMsgReply());
		pstmt.setString(3, messageinfo.getMsgReplyDate());
		pstmt.setInt(4, messageinfo.getMsgId());
		
		result = pstmt.executeUpdate();
		DatabaseUtil.closeAll(null, pstmt, rs);
		
		return result;
	}
	//删除短消息
	@Override
	public int deleteMessageInfo(Integer msgId) throws SQLException {
		int result = 0;
		String sql = null;
		
		sql="update msginfo set msgisdelete=? where msgid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setBoolean(1, true);
		pstmt.setInt(2, msgId);
		
		result = pstmt.executeUpdate();
		DatabaseUtil.closeAll(null, pstmt, rs);
		
		return result;
	}

}
