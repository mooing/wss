package com.mooing.wss.cert.enums;

/**
 * 
 * 签发状态
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-19
 */
public enum IssueStatus {

	unissue(1, "待签发"),
	issued(2, "已签发"),
	change(3, "换发"),
	fill(4, "补发"),
	special(5, "特殊签发");

	private int status;
	private String name;

	private IssueStatus(int status, String name) {
		this.status = status;
		this.name = name;
	}

	public static String findNameByStatus(int status) {
		for (IssueStatus issueStatus : values()) {
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
