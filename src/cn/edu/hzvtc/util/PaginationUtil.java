package cn.edu.hzvtc.util;

import java.util.LinkedList;
import java.util.List;

public class PaginationUtil<T> {
	
	//ҳ���С����ÿҳ��ʾ��¼��
	private int pageSize = 5;
	//��ǰҳ��
	private int pageNo = 1;
	
	//��ҳ��
	private int totalPageCount = 1;
	
	//��¼����
	private int totalCount = 0;
	
	//���ؽ����
	private List<T> items = new LinkedList<T>();
	
	private int pageStart = 0;
	private int pageEnd = 0;
	
	public int getPageStart(){
		for(int i = 0;i < 5;i++){
			if(pageNo - i>0)
				pageStart=pageNo-i;
		}
		return pageStart;
	}
	
	public int getPageEnd(){
		for(int i = 0;i < 5;i++){
			if(pageNo + i<=totalPageCount)
				pageEnd=pageNo+i;
		}
		return pageEnd;
	}
	
	@SuppressWarnings("unused")
	private PaginationUtil(){
		
	}
	
	public PaginationUtil(int pageNo,int pageSize){
		setPageSize(pageSize);
		setPageNo(pageNo);
	}
	
	public PaginationUtil(List<T> items,int pageNo,int totalCount){
		setTotalCount(totalCount);
		refreshPageNo(pageNo);
		setItems(items);
	}
	
	private int refreshPageNo(int pageNo){
		int tempCurrPage = pageNo;
		if(pageNo<1){
			tempCurrPage=1;
		}
		if(pageNo>this.totalPageCount){
			tempCurrPage=this.totalPageCount;
		}
		return tempCurrPage;
	}
	
	/**
	 * �õ���ʼ��¼��
	 * 
	 * @return
	 */
	public int getStartRow(){
		return (pageNo-1)*pageSize;
	}
	
	/**
	 * �õ�������¼��
	 * 
	 * @return
	 */
	public int getEndRow(){
		return pageNo*pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(totalCount>=0){
			int count = totalCount/pageSize;
			if(totalCount%pageSize>0){
				count++;
			}
			this.totalPageCount=count;
		}
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	

}
