package cn.edu.hzvtc.biz;

import java.sql.SQLException;
import java.util.List;

import cn.edu.hzvtc.entity.UserInfo;

public interface UserInfoBiz {
	//����û�
	public int doInsertUserInfo(UserInfo userinfo) throws SQLException;
	
	//�鿴�û�
	public UserInfo getUserInfo(UserInfo userinfo) throws SQLException;
	
	//�޸��û�
	public int doUpdateUserInfo(UserInfo userinfo) throws SQLException ;
	
	//��ѯ���Լ������ȫ���û�
	public List<UserInfo> getAllUserInfo(UserInfo userinfo) throws SQLException;
}
