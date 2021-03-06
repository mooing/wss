package com.mooing.wss.system.model;

import com.mooing.wss.common.model.BaseModel;

/**
 * 用户角色实体
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-2
 */

public class UserRole extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2776836831310112618L;

	private int userid;
	private int roleid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

}
