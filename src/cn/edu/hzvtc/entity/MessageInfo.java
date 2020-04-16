package cn.edu.hzvtc.entity;

public class MessageInfo {
	private Integer msgId;// ID
	private UserInfo msgSender;// 发送方
	private UserInfo msgReceiver;// 接收方
	private String msgTitle;// 标题
	private String msgContent;// 内容
	private Integer msgState;// 状态
	private String msgCreateDate;// 发送时间
	private Boolean msgNeedReply;// 是否需要回复
	private String msgReply;// 回复内容
	private String msgReplyDate;// 回复时间
	private Boolean msgIsDelete;// 是否删除
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public UserInfo getMsgSender() {
		return msgSender;
	}
	public void setMsgSender(UserInfo msgSender) {
		this.msgSender = msgSender;
	}
	public UserInfo getMsgReceiver() {
		return msgReceiver;
	}
	public void setMsgReceiver(UserInfo msgReceiver) {
		this.msgReceiver = msgReceiver;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Integer getMsgState() {
		return msgState;
	}
	public void setMsgState(Integer msgState) {
		this.msgState = msgState;
	}
	public String getMsgCreateDate() {
		return msgCreateDate;
	}
	public void setMsgCreateDate(String msgCreateDate) {
		this.msgCreateDate = msgCreateDate;
	}
	public Boolean getMsgNeedReply() {
		return msgNeedReply;
	}
	public void setMsgNeedReply(Boolean msgNeedReply) {
		this.msgNeedReply = msgNeedReply;
	}
	public String getMsgReply() {
		return msgReply;
	}
	public void setMsgReply(String msgReply) {
		this.msgReply = msgReply;
	}
	public String getMsgReplyDate() {
		return msgReplyDate;
	}
	public void setMsgReplyDate(String msgReplyDate) {
		this.msgReplyDate = msgReplyDate;
	}
	public Boolean getMsgIsDelete() {
		return msgIsDelete;
	}
	public void setMsgIsDelete(Boolean msgIsDelete) {
		this.msgIsDelete = msgIsDelete;
	}
	
}
