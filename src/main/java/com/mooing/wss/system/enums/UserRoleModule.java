package com.mooing.wss.system.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 用户角色模块
*
*
*
* @author kaiming.chi
*
* @date 2014-1-4 上午8:45:13
 */
public enum UserRoleModule {
	role(1, "role"),
	user(2, "user");

	private int type;
	private String name;

	private UserRoleModule(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public static String getNameByType(int type) {
		String name = "";
		for (UserRoleModule userType : values()) {
			if (userType.getType() == type) {
				name = userType.getName();
			}
		}
		return name;
	}

	public static List<UserRoleModule> getAllUserType() {
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
