package com.mooing.wss.cert.enums;

/**
 * 
*出生证编号状态
*
* @author kaiming.chi
*
* @date 2013-12-19
 */
public enum CertCodeStatus {

	valid(1, "有效");

	private int status;
	private String name;

	private CertCodeStatus(int status, String name) {
		this.status = status;
		this.name = name;
	}

	public static String findNameByStatus(int status) {
		for (CertCodeStatus issueStatus : values()) {
			if (issueStatus.getStatus() == status) {
				return issueStatus.getName();
			}
		}
		return "";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
