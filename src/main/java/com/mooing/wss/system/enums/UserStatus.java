package com.mooing.wss.system.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 状态
 * 
 * 
 * Title: UserStatus
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-15
 */
public enum UserStatus {
	valid(1, "有效"), invalid(0, "无效");

	private int status;
	private String name;

	private UserStatus(int status, String name) {
		this.status = status;
		this.name = name;
	}

	public static String getNameByStatus(int status) {
		String name = "";
		for (UserStatus userStatus : values()) {
			if (userStatus.getStatus() == status) {
				name = userStatus.getName();
			}
		}
		return name;
	}

	public static List<UserStatus> getAllUserType() {
		return Arrays.asList(values());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
