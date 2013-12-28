package com.mooing.wss.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.TriggersRemove;
import com.mooing.wss.common.cache.base.SystemCache;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.system.model.Role;
import com.mooing.wss.system.model.User;

@Service
public class RoleService extends SystemBaseService {

	@Resource
	private SystemCache systemCache;

	public List<Role> findAllRole() {
		List<Role> roleList = new ArrayList<Role>();
		roleList = systemCache.findAllRole();
		return roleList;
	}

	public Pagination<Role> pageList(SearchBoxModel searchBox, Map<String, Object> search) {
		Integer count = wssBaseDao.executeForObject("Role.findAllRoleCount", search, Integer.class);
		Pagination<Role> page = null;
		List<Role> userList = new ArrayList<Role>();
		if (count != null && count != 0) {
			page = new Pagination<Role>(count, searchBox.getPageNum(), searchBox.getNumPerPage());
			search.put("startrecord", page.getStartPosition());
			search.put("recordsize", searchBox.getNumPerPage());
			userList = wssBaseDao.executeForObjectList("Role.findAllRolePage", search);
			page.bindData(userList);
		}
		return page;
	}

	/**
	 * 去新增角色
	 * 
	 * @param loginUser
	 * @throws UserException
	 */
	public void toAddRole(User loginUser) throws UserException {
		userAuthorityCheck(loginUser);
	}

	/**
	 * 保存角色基本信息
	 * 
	 * @param role
	 * @throws UserException
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@TriggersRemove(cacheName = "roleCache", removeAll = true)
	public void addRole(Role role) throws UserException {
		// 判断角色名是否存在
		Integer roleCount = wssBaseDao.executeForObject("Role.findRoleByName", role.getRolename(), Integer.class);
		if (roleCount != null && roleCount > 0) {
			throw new UserException(UserException.ROLE_NAME_ISEXIST);
		}
		wssBaseDao.execute("Role.saveRole", role);
	}

	/**
	 * 去修改角色
	 * 
	 * @param loginUser
	 * @param roleid
	 * @throws UserException
	 */
	public Role toUpdateRole(User loginUser, int roleid) throws UserException {
		userAuthorityCheck(loginUser);
		Role role = wssBaseDao.executeForObject("Role.findRoleById", roleid, Role.class);
		if (role == null) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		return role;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@TriggersRemove(cacheName = "roleCache", removeAll = true)
	public void updateRole(Role role) throws UserException {
		// 判断角色名是否存在
		Integer roleCount = wssBaseDao.executeForObject("Role.findRoleByName", role.getRolename(), Integer.class);
		if (roleCount != null && roleCount > 0) {
			throw new UserException(UserException.ROLE_NAME_ISEXIST);
		}
		wssBaseDao.execute("Role.updateRoleById", role);
	}

	/**
	 * 删除角色
	 * 
	 * @param loginUser
	 * @param roleid
	 * @throws UserException
	 */
	@TriggersRemove(cacheName = "roleCache", removeAll = true)
	public void delRole(User loginUser, int rid) throws UserException {
		userAuthorityCheck(loginUser);
		wssBaseDao.execute("Role.delRoleById", rid);
	}
}
