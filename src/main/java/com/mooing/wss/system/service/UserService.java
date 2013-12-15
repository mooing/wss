package com.mooing.wss.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.mooing.wss.common.cache.base.SystemCache;
import com.mooing.wss.common.cache.base.UnitCache;
import com.mooing.wss.common.exception.SystemException;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.common.util.Sha1Util;
import com.mooing.wss.hos.model.Doctor;
import com.mooing.wss.hos.model.Hospital;
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
		search.put("startrecord", searchBox.getPnum());
		search.put("recordsize", searchBox.getPsize());
		Integer count = wssBaseDao.executeForObject("User.findUserCount", search, Integer.class);
		Pagination<User> page = null;
		List<User> userList = new ArrayList<User>();
		if (count != null && count != 0) {
			page = new Pagination<User>(count, searchBox.getPnum(), searchBox.getPsize());
			userList = wssBaseDao.executeForObjectList("User.findAllUser", search);
			if (!CollectionUtils.isEmpty(userList)) {
				// 设置医院名称和地址
				Map<Integer, Hospital> hospitalAllCache = unitCache.hospitalAllCache();
				for (User user : userList) {
					Hospital hospital = hospitalAllCache.get(user.getHospitalId());
					if (hospital != null) {
						user.setHospitalName(hospital.getName());
						user.setHospitalAddress(hospital.getRegionName());
					}
					user.setUserTypeName(UserType.getNameByType(user.getUsertype()));
					user.setUserStatus(UserStatus.getNameByStatus(user.getStatus()));
				}
			}
			page.bindData(userList);
		}
		return page;
	}

	/**
	 * 配置用户角色
	 * 
	 * 根据用户id，获取用户角色，全部角色
	 * 
	 * @param userid
	 * @return
	 */
	public User findUserRole(int userid) {
		User user = new User();
		user.setId(userid);
		List<Integer> roleIds = wssBaseDao.executeForObjectList("Role.findRoleIdByUserId", userid);
		user.setRoleList(systemCache.findAllRole());
		user.setRoleIds(roleIds);
		return user;
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
		// 2.如果是医生类型，同步信息到医生
		if (UserType.doctor.getType() == user.getUsertype()) {
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
		user.setRoleList(systemCache.findAllRole());
		user.setRoleIds(roleIds);
		return user;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(User loginUser, User user) throws UserException {
		userAuthorityCheck(loginUser);
		// 如果基本信息为空，返回
		if (user == null || CollectionUtils.isEmpty(user.getRoleIds())) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
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
		wssBaseDao.execute("User.delUserById", userid);
		// 设置医生为无效
		wssBaseDao.execute("Doctor.delDoctorByUserId", userid);
		// 删除对应角色
		delUserRole(userid);
	}

	/**
	 * 用户列表，配置用户角色
	 * 
	 * @param loginUser
	 *            当前登录用户
	 * @param userid
	 *            要配置的的用户id
	 * @param roleIds
	 *            对应角色表的角色id
	 * @throws UserException
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void configUserRole(User loginUser, int userid, List<Integer> roleIds) throws UserException {
		// 是否有其他控制
		userAuthorityCheck(loginUser);
		if (userid < 0 || CollectionUtils.isEmpty(roleIds)) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		// 删除原用户角色
		delUserRole(userid);
		// 2.保存用户角色
		saveUserRoles(userid, roleIds);
	}
}
