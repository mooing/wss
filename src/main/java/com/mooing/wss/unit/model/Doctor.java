package com.mooing.wss.unit.model;

import com.mooing.wss.common.model.BaseModel;

/**
 * 医生实体
 * 
 * @author kaiming.chi
 * 
 */
public class Doctor extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4182283425835533653L;
	/**
	 * 用户id 对应用户表
	 */
	private int userid;
	/**
	 * 医生姓名
	 */
	private String name;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
