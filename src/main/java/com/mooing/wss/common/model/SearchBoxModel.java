package com.mooing.wss.common.model;

/**
 * 供所有查询使用 添加查询条件,如有新增条件可继承
 * 
 * 
 * Title: SearchBoxModel Description:
 * 
 * @author kaiming.chi
 * 
 * @date 2013-11-30
 */
public class SearchBoxModel {

	int pageNum = 1;
	int numPerPage = 10;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

}
