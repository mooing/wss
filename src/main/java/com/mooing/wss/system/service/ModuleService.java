package com.mooing.wss.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.mooing.wss.common.cache.base.SystemCache;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.system.model.Module;
import com.mooing.wss.system.model.User;

/**
 * 木框管理service
 * 
 * Title: ModuleService
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-2
 */
@Service
public class ModuleService extends SystemBaseService {
	@Resource
	private SystemCache systemCache;
	private static final Logger log = LoggerFactory.getLogger(ModuleService.class);

	/**
	 * 根据父节点id,获取模块数据
	 * 
	 * @return
	 */
	public String findModuleByPid(int pid) {
		List<Module> moduleList = systemCache.findModuleByPid(pid);
		String jsonString = JSON.toJSONString(moduleList);
		log.info("ModuleSerivce| findAllModule ,json:{}", jsonString);
		return jsonString;
	}

	public String findAllModule() {
		List<Module> moduleList = systemCache.findAllModule();
		String jsonString = JSON.toJSONString(moduleList);
		log.info("ModuleSerivce| findAllModule ,json:{}", jsonString);
		return jsonString;
	}

	/**
	 * 添加节点
	 * 
	 * @param loginUser
	 * @param pid
	 *            父id
	 * @param name
	 *            节点名称
	 * @throws UserException
	 */
	@TriggersRemove(cacheName = "moduleCache", removeAll = true)
	public void addNode(User loginUser, Module module) throws UserException {
		userAuthorityCheck(loginUser);
		if (module != null && !StringUtils.isBlank(module.getName())) {
			wssBaseDao.execute("Module.addModule", module);
		}
	}

	@TriggersRemove(cacheName = "moduleCache", removeAll = true)
	public void delNode(User loginUser, int id) throws UserException {
		userAuthorityCheck(loginUser);
		wssBaseDao.execute("Module.delModule", id);
	}

	@TriggersRemove(cacheName = "moduleCache", removeAll = true)
	public void updateNode(User loginUser, Module module) throws UserException {
		userAuthorityCheck(loginUser);
		if (module != null && !StringUtils.isBlank(module.getName())) {
			wssBaseDao.execute("Module.updateModule", module);
		}
	}

}
