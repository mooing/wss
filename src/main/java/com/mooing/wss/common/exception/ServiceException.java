package com.mooing.wss.common.exception;

/**
 * service层异常
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

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
