package cn.edu.hzvtc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hzvtc.dao.UserInfoDao;
import cn.edu.hzvtc.entity.UserInfo;
import cn.edu.hzvtc.util.DatabaseUtil;

public class UserInfoDaoImpl implements UserInfoDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	public UserInfoDaoImpl(Connection con) throws SQLException{
		this.conn=con;
	}
	//用户名重复
	@Override
	public UserInfo getUserInfoByUserName(String username) throws SQLException {
		UserInfo result =null;
		
		String sql="select * from userinfo where username=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		
		rs = pstmt.executeQuery();
		while(rs.next()){
			result = new UserInfo();
			result.setUserId(rs.getInt("userid"));
			result.setUserName(rs.getString("username"));
			result.setUserPassword(rs.getString("userpassword"));
			result.setUserEmail(rs.getString("useremail"));
		}
		
		DatabaseUtil.closeAll(null, pstmt, rs);
		return result;
	}
	//添加用户
	@Override
	public int saveUserInfo(UserInfo userinfo) throws SQLException {
		int result =0;
		String sql =null;
		sql="insert into userinfo(username,userpassword,useremail)values(?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,userinfo.getUserName());
		pstmt.setString(2,userinfo.getUserPassword());
		pstmt.setString(3,userinfo.getUserEmail());
		
		result=pstmt.executeUpdate();
		DatabaseUtil.closeAll(null, pstmt, null);
		
		return result;
	}
	//登录验证用户名密码
	@Override
	public UserInfo getUserInfo(UserInfo userinfo) throws SQLException {
		UserInfo result=null;
		
		String sql="select * from userinfo where username=? and userpassword=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,userinfo.getUserName());
		pstmt.setString(2,userinfo.getUserPassword());
		
		rs = pstmt.executeQuery();
		while(rs.next()){
			result = new UserInfo();
			result.setUserId(rs.getInt("userid"));
			result.setUserName(rs.getString("username"));
			result.setUserPassword(rs.getString("userpassword"));
			result.setUserEmail(rs.getString("useremail"));
		}
		
		DatabaseUtil.closeAll(null, pstmt, rs);
		return result;
	}
	//修改用户
	@Override
	public int updateUserInfo(UserInfo userinfo) throws SQLException {
		int result=0;
		String sql=null;
		
		sql="update userinfo set username=?,userpassword=?,useremail=? where userid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,userinfo.getUserName());
		pstmt.setString(2,userinfo.getUserPassword());
		pstmt.setString(3,userinfo.getUserEmail());
		pstmt.setInt(4,userinfo.getUserId());
		
		result=pstmt.executeUpdate();
		DatabaseUtil.closeAll(null, pstmt, null);
		
		return result;
	}
	//id查找用户
	@Override
	public UserInfo getUserInfoByUserId(Integer userid)
			throws SQLException {
		UserInfo result =null;
		
		String sql="select * from userinfo where userid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userid);
		
		rs = pstmt.executeQuery();
		while(rs.next()){
			result = new UserInfo();
			result.setUserId(rs.getInt("userid"));
			result.setUserName(rs.getString("username"));
			result.setUserPassword(rs.getString("userpassword"));
			result.setUserEmail(rs.getString("useremail"));
		}
		
		DatabaseUtil.closeAll(null, pstmt, rs);
		return result;
	}
	//查询id不符合的用户
	@Override
	public List<UserInfo> getAllUserInfo(Integer userid) throws SQLException {
		List<UserInfo> result =new ArrayList<UserInfo>();
		
		String sql="select * from userinfo where userid<>?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userid);
		
		rs=pstmt.executeQuery();
		while(rs.next()){
			UserInfo uf = new UserInfo();
			uf.setUserId(rs.getInt("userid"));
			uf.setUserName(rs.getString("username"));
			uf.setUserPassword(rs.getString("userpassword"));
			uf.setUserEmail(rs.getString("useremail"));
			result.add(uf);
		}
		
		DatabaseUtil.closeAll(null, pstmt, rs);
		return result;
	}

}
