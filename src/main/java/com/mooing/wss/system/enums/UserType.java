package com.mooing.wss.system.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 用户类型枚举
 * 
 * @author kaiming.chi
 * 
 */
public enum UserType {
	admin(1, "管理员"), doctor(2, "医生"), lead(3, "领导");

	private int type;
	private String name;

	private UserType(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public static String getNameByType(int type) {
		String name = "";
		for (UserType userType : values()) {
			if (userType.getType() == type) {
				name = userType.getName();
			}
		}
		return name;
	}

	public static List<UserType> getAllUserType() {
		return Arrays.asList(values());
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
