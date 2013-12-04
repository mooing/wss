package com.mooing.wss.system.model;

import java.util.Date;
import java.util.List;

import com.mooing.wss.common.model.BaseModel;

/**
 * @version $id$
 * 
 */

public class User extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户锁定状态
	 */
	public static final int STATUS_LOCKED = 0;
	/**
	 * 登录名
	 */
	private String username;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 注册时间
	 */
	private Date regtime;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户类型 1：管理员 2：医生
	 */
	private int usertype;

	private String ip;
	/**
	 * 随机数,用户加密时使用
	 */
	private String random;

	/************** 以下供页面使用 **************/
	private List<Integer> roleIds;
	private List<Role> roleList;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}


	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}