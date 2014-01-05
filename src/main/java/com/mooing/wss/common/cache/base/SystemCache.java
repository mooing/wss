package com.mooing.wss.common.cache.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mooing.wss.common.dao.GenericBaseDAO;
import com.mooing.wss.system.model.Module;
import com.mooing.wss.system.model.Role;

/**
 * 系统模块相关缓存
 * 
 * @author kaiming.chi
 * 
 */
@Service
public class SystemCache {
	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;

	/**
	 * 查询所有角色缓存
	 * 
	 * @return
	 */
	@Cacheable(cacheName = "roleCache")
	public List<Role> findAllRole() {
		List<Role> roleList = new ArrayList<Role>();
		roleList = wssBaseDao.executeForObjectList("Role.findAllRole", null);
		return roleList;
	}

	/**
	 * 查询所有模块缓存
	 * 
	 * @return
	 */
	public List<Module> findModuleByPid(int pid) {
		List<Module> moduleList = new ArrayList<Module>();
		moduleList = wssBaseDao.executeForObjectList("Module.findModuleByPid", pid);
		return moduleList;
	}

	@Cacheable(cacheName = "moduleCache")
	public List<Module> findAllModule() {
		List<Module> moduleList = new ArrayList<Module>();
		moduleList = wssBaseDao.executeForObjectList("Module.findAllModule", null);
		return moduleList;
	}


	/**
	 * 获取一级菜单 
	 */
	@Cacheable(cacheName = "system.firstModuleCache")
	public List<Module> getFirstModule() {
		List<Module> moduleList = new ArrayList<Module>();
		moduleList = wssBaseDao.executeForObjectList("Module.findByFirstModule", null);
		return moduleList;
	}
	/**
	 * 获取一级菜单下所有菜单 
	 */
	@Cacheable(cacheName = "system.unFirstModule")
	public List<Module> findUnFirstModule() {
		List<Module> moduleList = new ArrayList<Module>();
		moduleList = wssBaseDao.executeForObjectList("Module.findUnFirstModule", null);
		return moduleList;
	}
}
