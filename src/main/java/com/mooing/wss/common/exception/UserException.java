package com.mooing.wss.common.exception;

/**
 * 用户信息异常
 * 
 * @author
 */
public class UserException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 默认异常
	 */
	public static final String USER_DEFAULT_EXCEPTION = "0";
	/**
	 * 用户未找到异常
	 */
	public static final String USER_IS_NOT_EXIST = "1";
	/**
	 * 角色名稱已存在
	 */
	public static final String ROLE_NAME_ISEXIST = "2";
	/**
	 * 用户名称已存在
	 */
	public static final String USER_NAME_ISEXIST = "3";
	/**
	 * 当前用户类型没有操作权限
	 */
	public static final String USER_TYPE_NOT_AUTHORITY = "4";

	public UserException(Throwable cause) {
		super(cause);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}

	public UserException() {
	}

}
