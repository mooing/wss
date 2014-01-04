package com.mooing.wss.system.model;

import java.util.List;

public class Role {
	private Integer id;
	private String rolename;

	// 当前用户模块id list
	private List<Integer> moduleIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<Integer> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}
}
