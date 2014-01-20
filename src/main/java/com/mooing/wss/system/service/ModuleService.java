package com.mooing.wss.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.mooing.wss.common.cache.base.SystemCache;
import com.mooing.wss.common.exception.ServiceException;
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
	@TriggersRemove(cacheName = { "moduleCache", "system.firstModuleCache" }, removeAll = true)
	public void addNode(User loginUser, Module module) throws UserException, ServiceException {
		userAuthorityCheck(loginUser);
		if (module != null && !StringUtils.isBlank(module.getModname())) {
			// 验证权限名是否重复
			Integer moduleCount = wssBaseDao.executeForObject("Module.getModuleCountByAuthorityRel", module.getAuthorityRel(), Integer.class);
			if (moduleCount != null && moduleCount > 0) {
				log.error(" ModuleService | addNode ,moduleCount gt 0,AuthorityRel is exits,AuthorityRel:{}", module.getAuthorityRel());
				throw new ServiceException(ServiceException.MODULE_AUTHORITY_ISEXIST_EXCEPTION);
			}
			// 获取父模块，权限名
			String parentAuthorityRel = wssBaseDao.executeForObject("Module.getAuthorityRelByPid", module.getPid(), String.class);
			if (StringUtils.isBlank(parentAuthorityRel)) {
				log.error(" ModuleService | addNode ,parentAuthorityRel is null,module.getPid():{}", module.getPid());
				throw new ServiceException(ServiceException.DEFAULT_EXCEPTION);
			}
			module.setpAuthorityRel(parentAuthorityRel);
			wssBaseDao.execute("Module.addModule", module);
		}
	}

	@TriggersRemove(cacheName = { "moduleCache", "system.firstModuleCache" }, removeAll = true)
	public void delNode(User loginUser, int id) throws UserException, ServiceException {
		userAuthorityCheck(loginUser);
		if (id == 1) {
			// 不能删除根节点
			throw new ServiceException(ServiceException.ROOT_NOT_DEL);
		}
		wssBaseDao.execute("Module.delModule", id);
	}

	@TriggersRemove(cacheName = { "moduleCache", "system.firstModuleCache" }, removeAll = true)
	public void updateNode(User loginUser, Module module) throws UserException, ServiceException {
		userAuthorityCheck(loginUser);
		if (module != null && !StringUtils.isBlank(module.getModname())) {
			if (module.getId() == 1) {
				// 不能删除根节点
				throw new ServiceException(ServiceException.ROOT_NOT_DEL);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("authorityRel", module.getAuthorityRel());
			map.put("id", module.getId());
			// 验证权限名是否重复
			Integer moduleCount = wssBaseDao.executeForObject("Module.getUpdateModuleCountByAuthorityRel", map, Integer.class);
			if (moduleCount != null && moduleCount > 0) {
				log.error(" ModuleService | updateNode ,moduleCount gt 0,AuthorityRel is exits,AuthorityRel:{}", module.getAuthorityRel());
				throw new ServiceException(ServiceException.MODULE_AUTHORITY_ISEXIST_EXCEPTION);
			}
			wssBaseDao.execute("Module.updateModule", module);
		}
	}

	public Module toUpdateNode(User loginUser, int moduleid) throws UserException, ServiceException {
		userAuthorityCheck(loginUser);
		if (moduleid == 1) {
			// 不能删除根节点
			throw new ServiceException(ServiceException.ROOT_NOT_DEL);
		}
		if (moduleid != 0) {
			return wssBaseDao.executeForObject("Module.findById", moduleid, Module.class);
		}
		return null;
	}

	/**
	 * 获取一级菜单，供layout使用
	 * 
	 * @param loginUser
	 */
	public String getFirstModule(User loginUser) {
		List<Module> firstModule = systemCache.getFirstModule();
		getLoginUserModuleAuth(loginUser, firstModule);
		String jsonString = JSON.toJSONString(firstModule);
		log.info("ModuleSerivce| getFirstModule ,json:{}", jsonString);
		return jsonString;
	}

	/**
	 * 获取一级菜单，供layout使用
	 * 
	 * @param loginUser
	 */
	public List<Module> getFirstModuleList(User loginUser) {
		List<Module> firstModule = systemCache.getFirstModule();
		getLoginUserModuleAuth(loginUser, firstModule);
		return firstModule;
	}

	/**
	 * 去除无权限模块
	 * 
	 * @param loginUser
	 * @param firstModule
	 */
	private void getLoginUserModuleAuth(User loginUser, List<Module> firstModule) {
		if (CollectionUtils.isNotEmpty(firstModule)) {
			// admin 拥有最高权限
			if (!loginUser.getUsername().equals("admin")) {
				List<String> moduleAuthList = loginUser.getModuleAuthList();
				List<Module> removeList = Lists.newArrayList();
				if (CollectionUtils.isNotEmpty(moduleAuthList)) {
					for (Module module : firstModule) {
						for (String auth : moduleAuthList) {
							if (module.getAuthorityRel().equals(auth)) {
								removeList.add(module);
							}
						}
					}
					firstModule.removeAll(removeList);
				}
			}
		}
	}

	/**
	 * 获取一级菜单下所有菜单，供layout使用
	 */
	public String findUnFirstModule(User loginUser) {
		List<Module> moduleList = systemCache.findUnFirstModule();
		getLoginUserModuleAuth(loginUser, moduleList);
		String jsonString = JSON.toJSONString(moduleList);
		log.info("ModuleSerivce| findUnFirstModule ,json:{}", jsonString);
		return jsonString;
	}

}
