package com.mooing.wss.common.exception;

/**
 * 框架通用异常类。
 * 
 * <p>
 * 当系统逻辑无法继续执行时，由框架本身抛出。<br>
 * 该异常类包含错误代码及错误消息替换字符串数组。通常情况下，由外部根据错误代码及替换字符串生成错误消息， 再执行
 * {@link #setMessage(String)} 设置错误消息。在未设置错误消息情况下，执行 {@link #getMessage()}
 * 将返回错误代码。
 * </p>
 */
public class SystemException extends RuntimeException {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 6737867643545079310L;

	/**
	 * 错误代码。
	 */
	private String errorCode = null;

	/**
	 * 错误消息替换字符串数组。
	 */
	private String[] options = null;

	/**
	 * 错误消息。
	 */
	private String message = null;

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因
	 */
	public SystemException(Throwable cause) {
		super(cause);
		this.errorCode = "";
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因
	 * @param errorCode
	 *            错误代码
	 */
	public SystemException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因
	 * @param errorCode
	 *            错误代码
	 * @param optionStrings
	 *            错误消息替换字符串数组
	 */
	public SystemException(String errorCode, Throwable cause, String[] optionStrings) {
		super(cause);
		this.errorCode = errorCode;
		this.options = optionStrings;
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因
	 * @param errorCode
	 *            错误代码
	 * @param s0
	 *            错误消息中{0}位置的替换字符串
	 */
	public SystemException(String errorCode, Throwable cause, String s0) {
		super(cause);
		this.errorCode = errorCode;
		this.options = new String[] { s0 };
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因
	 * @param errorCode
	 *            错误代码
	 * @param s0
	 *            错误消息中{0}位置的替换字符串
	 * @param s1
	 *            错误消息中{1}位置的替换字符串
	 */
	public SystemException(String errorCode, Throwable cause, String s0, String s1) {
		super(cause);
		this.errorCode = errorCode;
		this.options = new String[] { s0, s1 };
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因
	 * @param errorCode
	 *            错误代码
	 * @param s0
	 *            错误消息中{0}位置的替换字符串
	 * @param s1
	 *            错误消息中{1}位置的替换字符串
	 * @param s2
	 *            错误消息中{2}位置的替换字符串
	 */
	public SystemException(String errorCode, Throwable cause, String s0, String s1, String s2) {
		super(cause);
		this.errorCode = errorCode;
		this.options = new String[] { s0, s1, s2 };
	}

	/**
	 * 构造函数。
	 * 
	 * @param cause
	 *            异常原因
	 * @param errorCode
	 *            错误代码
	 * @param s0
	 *            错误消息中{0}位置的替换字符串
	 * @param s1
	 *            错误消息中{1}位置的替换字符串
	 * @param s2
	 *            错误消息中{2}位置的替换字符串
	 * @param s3
	 *            错误消息中{3}位置的替换字符串
	 */
	public SystemException(String errorCode, Throwable cause, String s0, String s1, String s2, String s3) {
		super(cause);
		this.errorCode = errorCode;
		this.options = new String[] { s0, s1, s2, s3 };
	}

	/**
	 * 取得错误代码。
	 * 
	 * @return 错误代码
	 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * 取得错误消息替换字符串数组。
	 * 
	 * @return 错误消息替换字符串数组
	 */
	public String[] getOptions() {
		return this.options;
	}

	/**
	 * 设置错误消息。
	 * 
	 * @param message
	 *            错误消息。
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 取得错误消息。 当错误消息不存在时，返回错误代码。
	 * 
	 * @return 错误消息或错误代码
	 */
	@Override
	public String getMessage() {
		if (this.message == null) {
			return this.errorCode;
		}
		return this.message;
	}
}
