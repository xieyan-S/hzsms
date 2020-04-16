package cn.edu.hzvtc.dao;

import java.sql.SQLException;
import java.util.List;

import cn.edu.hzvtc.entity.UserInfo;

public interface UserInfoDao {
	//�û����ظ�
	public UserInfo getUserInfoByUserName(String username) throws SQLException ;
	
	//����û�
	public int saveUserInfo(UserInfo userinfo) throws SQLException ;
	
	//��¼��֤�û�������
	public UserInfo getUserInfo(UserInfo userinfo) throws SQLException;
	
	//�޸��û�
	public int updateUserInfo(UserInfo userinfo) throws SQLException ;
	
	//id�����û�
	public UserInfo getUserInfoByUserId(Integer userid) throws SQLException;
	
	//��ѯid�����ϵ��û�
	public List<UserInfo> getAllUserInfo(Integer userid) throws SQLException;
}
