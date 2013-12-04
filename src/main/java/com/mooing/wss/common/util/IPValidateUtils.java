package com.mooing.wss.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IPValidateUtils {

	/**
	 * 防止反向代理导致request.getRemoteAddr()不可靠
	 * 
	 * @param request
	 *            the request
	 * @return ip 请求地址真实IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 验证requestIp是否匹配bindIp，匹配返回true，否则返回false.
	 * 
	 * @param bindIp
	 *            绑定的IP
	 * @param requestIp
	 *            待验证的IP，即登录IP
	 * @return true, if valid bind ip
	 * @throws Exception
	 *             the exception
	 */
	public static boolean validBindIp(String bindIp, String requestIp) throws Exception {
		// 无绑定IP，则所有IP都是正确的。
		if (StringUtils.isEmpty(bindIp)) {
			return true;
		}

		if (StringUtils.isEmpty(requestIp)) {
			return false;
		}
		// 是否绑定的标志，true为2个IP匹配，false为不匹配。
		boolean validBindIp = true;
		// split 函数需要的参数是正则表达式，【.】在正则表达式有特殊含义，所以要转义。
		String[] bindIpSplits = bindIp.split("\\.");
		String[] requestIpSplits = requestIp.split("\\.");
		int bindIpSplitsLength = bindIpSplits.length;
		int requestIpSplitsLength = requestIpSplits.length;
		int minLength = bindIpSplitsLength < requestIpSplitsLength ? bindIpSplitsLength : requestIpSplitsLength;

		for (int i = 0; i < minLength; i++) {
			// 标志为false，则跳出循环。
			if (!validBindIp) {
				break;
			}
			// "*"匹配所有
			if (bindIpSplits[i].indexOf("*") != -1) {
				continue;
			}
			// 对应的值是否相等。如果不等，则绑定标志置为false。
			if (!requestIpSplits[i].equals(bindIpSplits[i])) {
				validBindIp = false;
			}
		}

		return validBindIp;
	}

	/**
	 * Valid bind ip.
	 * 
	 * 如果成功返回 true
	 * 
	 * @param bindIps
	 *            the bind ips
	 * @param requestIp
	 *            the request ip
	 * @return true, if valid bind ip
	 * @throws Exception
	 *             the exception
	 */
	public boolean validBindIp(String[] bindIps, String requestIp) throws Exception {
		if (bindIps == null || bindIps.length == 0) {
			return true;
		}
		if (StringUtils.isEmpty(requestIp)) {
			return false;
		}
		boolean validBindIp = false;
		for (String bindIp : bindIps) {
			// 如果有一个符合，则比较完成，退出循环，返回true。
			if (validBindIp) {
				break;
			}
			validBindIp = this.validBindIp(bindIp, requestIp);
		}
		return validBindIp;
	}

	/**
	 * Valid bind ip.
	 * 
	 * 如果成功返回 true
	 * 
	 * @param bindIpsWithSeparator
	 *            the bind ips with separator
	 * @param requestIp
	 *            the request ip
	 * @param separator
	 *            the separator
	 * @return true, if valid bind ip
	 * @throws Exception
	 *             the exception
	 */
	public boolean validBindIp(String bindIpsWithSeparator, String requestIp, String separator) throws Exception {
		if (StringUtils.isEmpty(bindIpsWithSeparator)) {
			return true;
		}
		if (StringUtils.isEmpty(requestIp)) {
			return false;
		}
		if (StringUtils.isEmpty(separator)) {
			separator = ",";
		}
		String[] bindIps = bindIpsWithSeparator.split(separator);
		return this.validBindIp(bindIps, requestIp);
	}
}
