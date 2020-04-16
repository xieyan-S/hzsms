package cn.edu.hzvtc.dao;

import java.sql.SQLException;
import java.util.List;

import cn.edu.hzvtc.entity.UserInfo;

public interface UserInfoDao {
	//用户名重复
	public UserInfo getUserInfoByUserName(String username) throws SQLException ;
	
	//添加用户
	public int saveUserInfo(UserInfo userinfo) throws SQLException ;
	
	//登录验证用户名密码
	public UserInfo getUserInfo(UserInfo userinfo) throws SQLException;
	
	//修改用户
	public int updateUserInfo(UserInfo userinfo) throws SQLException ;
	
	//id查找用户
	public UserInfo getUserInfoByUserId(Integer userid) throws SQLException;
	
	//查询id不符合的用户
	public List<UserInfo> getAllUserInfo(Integer userid) throws SQLException;
}
