package com.mooing.wss.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mooing.wss.common.cache.base.SystemCache;
import com.mooing.wss.common.cache.base.UnitCache;
import com.mooing.wss.common.exception.SystemException;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.common.util.Sha1Util;
import com.mooing.wss.hos.model.Doctor;
import com.mooing.wss.hos.model.Hospital;
import com.mooing.wss.system.enums.UserRoleModule;
import com.mooing.wss.system.enums.UserStatus;
import com.mooing.wss.system.enums.UserType;
import com.mooing.wss.system.model.Role;
import com.mooing.wss.system.model.User;

@Service
public class UserService extends SystemBaseService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private SystemCache systemCache;
	@Resource
	private UnitCache unitCache;

	/**
	 * 查询用户列表
	 * 
	 * @param searchBox
	 *            页面传入查询条件
	 * @param search
	 *            查询条件map
	 * @return
	 */
	public Pagination<User> pageList(SearchBoxModel searchBox, Map<String, Object> search) {
		Integer count = wssBaseDao.executeForObject("User.findUserCount", search, Integer.class);
		Pagination<User> page = null;
		List<User> userList = new ArrayList<User>();
		if (count != null && count != 0) {
			page = new Pagination<User>(count, searchBox.getPageNum(), searchBox.getNumPerPage());
			search.put("startrecord", page.getStartPosition());
			search.put("recordsize", searchBox.getNumPerPage());
			userList = wssBaseDao.executeForObjectList("User.findAllUser", search);
			if (!CollectionUtils.isEmpty(userList)) {
				// 设置医院名称和地址
				Map<Integer, Hospital> hospitalAllCache = unitCache.hospitalAndRegionCache();
				for (User user : userList) {
					Hospital hospital = hospitalAllCache.get(user.getHospitalId());
					if (hospital != null) {
						user.setHospitalName(hospital.getName());
						user.setHospitalAddress(hospital.getRegionName());
					}
					if (user.getUsertype() != null) {
						user.setUserTypeName(UserType.getNameByType(user.getUsertype()));
					}
					user.setUserStatus(UserStatus.getNameByStatus(user.getStatus()));
				}
			}
			page.bindData(userList);
		}
		return page;
	}

	/**
	 * 去添加用户
	 * 
	 * @return
	 * @throws UserException
	 */
	public List<Role> toAdd(User loginUser) throws UserException {
		userAuthorityCheck(loginUser);
		return systemCache.findAllRole();
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void add(User loginUser, User user) throws UserException {
		userAuthorityCheck(loginUser);
		// 如果基本信息为空，返回
		if (user == null || CollectionUtils.isEmpty(user.getRoleIds())) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		// 判断用户名是否存在
		Integer userCount = wssBaseDao.executeForObject("User.getUserCountByName", user.getUsername(), Integer.class);
		if (userCount != null && userCount > 0) {
			throw new UserException(UserException.USER_NAME_ISEXIST);
		}
		// 1.保存用户
		String sqlPassword = getSQLPassword(user.getPassword());
		String random = Sha1Util.getStr(20);
		String realPass = Sha1Util.getSha1(user.getUsername(), sqlPassword, random);
		user.setRandom(random);
		user.setPassword(realPass);
		wssBaseDao.execute("User.saveUser", user);
		// 2.如果是医生或领导类型，同步信息到医生,维护其单位信息和地区信息
		if (UserType.doctor.getType() == user.getUsertype() || UserType.lead.getType() == user.getUsertype()) {
			Doctor doc = new Doctor();
			doc.setUserid(user.getId());
			wssBaseDao.execute("Doctor.saveDoctor", doc);
		}
		// 3.保存用户角色
		saveUserRoles(user.getId(), user.getRoleIds());
	}

	/**
	 * 去修改用户信息
	 * 
	 * @param userid
	 * @return
	 * @throws UserException
	 */
	public User toUpdate(User loginUser, int userid) throws UserException {
		userAuthorityCheck(loginUser);
		User user = wssBaseDao.executeForObject("User.findUserById", userid, User.class);
		if (user == null) {
			log.error("UserService | toUpdate ,user is null;userid:{}", userid);
			throw new UserException(UserException.USER_IS_NOT_EXIST);
		}
		List<Integer> roleIds = wssBaseDao.executeForObjectList("Role.findRoleIdByUserId", userid);
		user.setRoleIds(roleIds);
		return user;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(User loginUser, User user) throws UserException {
		userAuthorityCheck(loginUser);
		if (user == null) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		// 如果基本信息为空，返回
		if (CollectionUtils.isEmpty(user.getRoleIds())) {
			throw new UserException(UserException.USER_ROLE_TYPE_EMPTY);
		}
		// 判断用户名是否存在
		Map<String, Object> map = Maps.newHashMap();
		map.put("username", user.getUsername());
		map.put("id", user.getId());
		Integer userCount = wssBaseDao.executeForObject("User.getUserCountByNameAndId", map, Integer.class);
		if (userCount != null && userCount > 0) {
			throw new UserException(UserException.USER_NAME_ISEXIST);
		}
		// 1.保存用户
		wssBaseDao.execute("User.updateUserById", user);
		// 删除用户角色
		delUserRole(user.getId());
		// 2.保存用户角色
		saveUserRoles(user.getId(), user.getRoleIds());
	}

	/**
	 * 通过用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User getByUserName(String username) {
		return wssBaseDao.executeForObject("User.getUserByName", username, User.class);
	}

	public String getSQLPassword(String password) {
		try {
			return wssBaseDao.executeForObject("User.getSQLPassword", password, String.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SystemException(e.getMessage(), e);
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param loginUser
	 *            当前登录用户
	 * @param userid
	 *            要删除的用户id
	 * @throws UserException
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void del(User loginUser, int userid) throws UserException {
		// 是否有其他控制
		userAuthorityCheck(loginUser);
		wssBaseDao.execute("User.delUserByIds", Lists.newArrayList(userid));
		// 设置医生为无效
		wssBaseDao.execute("Doctor.delDoctorByUserIds", Lists.newArrayList(userid));
		// 删除对应角色
		delUserRole(userid);
	}

	/**
	 * 去修改用户角色信息
	 * 
	 * @param userid
	 * @return
	 * @throws UserException
	 */
	public User grantRole(User loginUser, int userid) throws UserException {
		userAuthorityCheck(loginUser);
		User user = wssBaseDao.executeForObject("User.findUserById", userid, User.class);
		if (user == null) {
			log.error("UserService | grantRole ,user is null;userid:{}", userid);
			throw new UserException(UserException.USER_IS_NOT_EXIST);
		}
		List<Integer> roleIds = wssBaseDao.executeForObjectList("Role.findRoleIdByUserId", userid);
		user.setRoleIds(roleIds);
		return user;
	}

	/**
	 * 
	 * @param loginUser
	 *            当前登录用户
	 * @param user
	 *            修改权限用户信息
	 * @throws UserException
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateRole(User loginUser, User user) throws UserException {
		userAuthorityCheck(loginUser);
		if (user == null) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		// 如果基本信息为空，返回
		if (CollectionUtils.isEmpty(user.getRoleIds())) {
			throw new UserException(UserException.USER_ROLE_TYPE_EMPTY);
		}
		// 删除用户角色
		delUserRole(user.getId());
		// 2.保存用户角色
		saveUserRoles(user.getId(), user.getRoleIds());
	}

	/**
	 * 授予用户模块权限
	 * 
	 * @param loginUser
	 * @param userid
	 * @return
	 * @throws UserException
	 */
	public Map<String, Object> grantModule(User loginUser, int userid) throws UserException {
		// userAuthorityCheck(loginUser, userid);
		Map<String, Object> moduleMap = Maps.newHashMap();
		moduleMap.put("objId", userid);
		moduleMap.put("type", UserRoleModule.user.getType());
		List<Integer> moduleIds = wssBaseDao.executeForObjectList("Module.findModulesByObjId", moduleMap);
		Map<String, Object> map = Maps.newHashMap();
		map.put("moduleList", systemCache.findAllModule());
		if (!CollectionUtils.isEmpty(moduleIds)) {
			String mids = moduleIds.toString();
			map.put("moduleIds", mids.substring(1, mids.length() - 1));
		}
		return map;
	}

	/**
	 * 保存用户模块 权限
	 * 
	 * @param loginUser
	 * @param user
	 * @throws UserException
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveGrantModule(User loginUser, User user) throws UserException {
		if (user == null || user.getId() == 0 || CollectionUtils.isEmpty(user.getModuleIds())) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		Map<String, Object> moduleMap = Maps.newHashMap();
		moduleMap.put("objId", user.getId());
		moduleMap.put("moduleIds", user.getModuleIds());
		moduleMap.put("type", UserRoleModule.user.getType());
		// 删除原用户模块权限
		wssBaseDao.execute("Module.delSysModulesByObjId", moduleMap);
		// 新增用户模块权限
		wssBaseDao.execute("Module.addSysModulesByObjId", moduleMap);
	}

	/**
	 * 获取模块权限
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> findModulesByUser(int userId) {
		try {
			Map<String,Object> moduleMap = new HashMap<String,Object>();
			moduleMap.put("objId", userId);
			moduleMap.put("type", UserRoleModule.user.getType());
			
			List<String> moduleNames=Lists.newArrayList();
			//查找用户的模块权限
			List<Integer> moduleIds = wssBaseDao.executeForObjectList("Module.findModulesByObjId", moduleMap);
			if(CollectionUtils.isNotEmpty(moduleIds)){
				for (Integer moduleId : moduleIds) {
//					moduleNames.add(e);
				}
			}
//			// 查找用户角色具有的模块权限
//			String byRole = "";
//			List _roleAcl = null;
//			List<SysUserRole> roles = sysUserRoleDao.findList("from SysUserRole ur where ur.user.id = ?", userId);
//			for (SysUserRole commUserRole : roles) {
//				SysRole role = commUserRole.getRole();
//				byRole = "select acl.id, acl.sysModuleId from SysACL acl where acl.principalType = ? and acl.principalId = ?";
//				_roleAcl = sysAclDao.findList(byRole, "role", role.getId());
//				for (Object object : _roleAcl) {
//					Object[] arr = (Object[]) object;
//					result.put(arr[1], arr[0]);
//				}
//			}
//			Set coll = result.keySet();
//			List<String> temp = sysModuleDao.findSnListByIds(coll);
//			List<String> acls = new ArrayList();
//			for (String str : temp) {
//				acls.add("'" + str + "'");
//			}
			// for (Object object : coll) {
			// int moduleId = (Integer)object;
			// SysModule m = sysModuleDao.get(SysModule.class, moduleId);
			// }
//			return acls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
