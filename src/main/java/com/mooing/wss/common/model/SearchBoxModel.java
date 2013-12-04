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

	int pnum = 0;
	int psize = 2;

	public SearchBoxModel() {
		super();
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getPsize() {
		return psize;
	}

	public void setPsize(int psize) {
		this.psize = psize;
	}

}
