package cn.edu.hzvtc.biz.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.edu.hzvtc.biz.UserInfoBiz;
import cn.edu.hzvtc.dao.UserInfoDao;
import cn.edu.hzvtc.dao.impl.UserInfoDaoImpl;
import cn.edu.hzvtc.entity.UserInfo;
import cn.edu.hzvtc.util.DatabaseUtil;

public class UserInfoBizImpl implements UserInfoBiz {
	private Connection conn=null;
	private UserInfoDao userInfoDao=null;

	public UserInfoBizImpl() throws SQLException{
		conn =DatabaseUtil.getConnection();
		userInfoDao = new UserInfoDaoImpl(conn);
	}
	//添加用户
	@Override
	public int doInsertUserInfo(UserInfo userinfo) throws SQLException {
		UserInfo user=userInfoDao.getUserInfoByUserName(userinfo
				.getUserName());
		if(user!=null){
			//用户名已经使用
			DatabaseUtil.closeAll(conn, null, null);
			return -1;
		}
		int result = userInfoDao.saveUserInfo(userinfo);
		DatabaseUtil.closeAll(conn, null, null);
		return result;
	}
	//查看用户
	@Override
	public UserInfo getUserInfo(UserInfo userinfo) throws SQLException {
		UserInfo user = userInfoDao.getUserInfo(userinfo);
		DatabaseUtil.closeAll(conn, null, null);
		return user;
	}
	//修改用户
	@Override
	public int doUpdateUserInfo(UserInfo userinfo) throws SQLException {
		UserInfo user=userInfoDao.getUserInfoByUserId(userinfo
				.getUserId());
		if(user == null){
			//用户名不存在
			DatabaseUtil.closeAll(conn, null, null);
			return -1;
		}
		int result = userInfoDao.updateUserInfo(userinfo);
		DatabaseUtil.closeAll(conn, null, null);
		return result;
	}
	//查询除自己以外的全体用户
	@Override
	public List<UserInfo> getAllUserInfo(UserInfo userinfo) throws SQLException {
		return userInfoDao.getAllUserInfo(userinfo.getUserId());
	}

}
