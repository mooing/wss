package com.mooing.wss.system.model;

import com.mooing.wss.common.model.BaseModel;

/**
 * 模块实体 Title: Module
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-2
 */
public class Module extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4946939315451760982L;

	/**
	 * 模块名称
	 */
	private String name;

	/**
	 * 父id
	 */
	private Integer pId;

	/**
	 * 英文名
	 */
	private String sen;

	/**
	 * 是否叶子节点 1:是；0：不是
	 */
	private boolean leaf;
	/**
	 * 序号
	 */
	private int sort;

	public String getSen() {
		return sen;
	}

	public void setSen(String sen) {
		this.sen = sen;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

}
