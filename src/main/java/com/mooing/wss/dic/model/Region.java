package com.mooing.wss.dic.model;

import com.mooing.wss.common.model.BaseModel;

/**
 * 
 * 
 * 地区字典
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-19 下午10:12:24
 */
public class Region extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8477917667896291609L;
	/**
	 * 地区编号
	 */
	private String code;
	/**
	 * 层级
	 */
	private int level;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父编号
	 */
	private String pcode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

}
