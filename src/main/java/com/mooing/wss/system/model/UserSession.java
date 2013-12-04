package com.mooing.wss.system.model;

import java.io.Serializable;

/**
 * 用户会话信息。
 * 
 */
public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户信息
	 */
	private User user = null;

	/**
	 * 构造函数。
	 * 
	 * @param user
	 *            用户信息
	 * @param validPaths
	 *            可访问路径列表
	 */
	public UserSession(User user) {
		super();
		this.user = user;
	}

	/**
	 * 获取用户信息。
	 * 
	 * @return 用户信息
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 设置用户信息。
	 * 
	 * @param user
	 *            用户信息
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
