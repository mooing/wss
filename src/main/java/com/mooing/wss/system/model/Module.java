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
	private String modname;

	/**
	 * 父id
	 */
	private Integer pid;

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
	/**
	 * 链接
	 */
	private String href;
	/**
	 * 权限标识
	 */
	private String authorityRel;
	/**
	 * 父节点权限标识
	 */
	private String pAuthorityRel;

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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getAuthorityRel() {
		return authorityRel;
	}

	public void setAuthorityRel(String authorityRel) {
		this.authorityRel = authorityRel;
	}

	public String getpAuthorityRel() {
		return pAuthorityRel;
	}

	public void setpAuthorityRel(String pAuthorityRel) {
		this.pAuthorityRel = pAuthorityRel;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getModname() {
		return modname;
	}

	public void setModname(String modname) {
		this.modname = modname;
	}

}
