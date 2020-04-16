package cn.edu.hzvtc.biz;

import java.sql.SQLException;
import java.util.List;

import cn.edu.hzvtc.entity.UserInfo;

public interface UserInfoBiz {
	//添加用户
	public int doInsertUserInfo(UserInfo userinfo) throws SQLException;
	
	//查看用户
	public UserInfo getUserInfo(UserInfo userinfo) throws SQLException;
	
	//修改用户
	public int doUpdateUserInfo(UserInfo userinfo) throws SQLException ;
	
	//查询除自己以外的全体用户
	public List<UserInfo> getAllUserInfo(UserInfo userinfo) throws SQLException;
}
