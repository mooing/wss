package com.mooing.wss.common.exception;

/**
 * service层异常
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	

	/**
	 * 删除根节点异常
	 */
	public static final String ROOT_NOT_DEL = "0";
	
	
	/**
	 * 默认异常
	 */
	public static final String DEFAULT_EXCEPTION = "1";
	

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException() {
	}

}
