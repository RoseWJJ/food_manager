package com.et.tool;

import java.util.List;

public class PageTool {

	public PageTool(Integer CurPage,Integer totalCount,Integer pageCount){
		this.CurPage=CurPage;//��ǰҳ
		this.totalCount=totalCount;//�ܼ�¼��(���ݿ�����)
		this.pageCount=pageCount==null?this.pageCount:pageCount;//ÿҳ��ʾ����
		this.previesPage=(CurPage==1?1:CurPage-1);//��һҳ
		//������һҳ֮ǰ�������ҳ��
		this.totalPage=totalCount%this.pageCount==0?totalCount/this.pageCount:totalCount/this.pageCount+1;
		this.nextPage=(CurPage==totalPage)?totalPage:(CurPage+1);
		this.startIndex=(CurPage-1)*this.pageCount+1;
		this.endIndex=CurPage*this.pageCount;
	}
	private Integer startIndex;//��������
	private Integer endIndex;//��ʼ����
	private Integer CurPage;//��ǰҳ
	private Integer totalCount;//�ܼ�¼��
	private Integer pageCount=10;//ÿҳ��ʾ����
	private Integer previesPage;//ǰһҳ
	private Integer nextPage;//��һҳ
	private Integer totalPage;//��ҳ��
	private List data;//�洢���ղ�ѯ������
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	public Integer getCurPage() {
		return CurPage;
	}
	public void setCurPage(Integer curPage) {
		CurPage = curPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPreviesPage() {
		return previesPage;
	}
	public void setPreviesPage(Integer previesPage) {
		this.previesPage = previesPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	public static void main(String[] args) {
		int curPage=2;
		int totalCount=37;
		int pageCount=5;
		PageTool pt = new PageTool(curPage, totalCount, pageCount);
		System.out.println("��һҳ"+pt.getNextPage());
		System.out.println("��һҳ"+pt.getPreviesPage());
		System.out.println("��ǰҳ��"+curPage);
		System.out.println("��ҳҳ"+pt.getTotalPage());
		System.out.println("��ʼ����"+pt.getStartIndex());
		System.out.println("��������"+pt.getEndIndex());
	}
}
