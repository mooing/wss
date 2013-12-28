package com.mooing.wss.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.service.BaseService;
import com.mooing.wss.system.enums.UserType;
import com.mooing.wss.system.model.User;
import com.mooing.wss.system.model.UserRole;

/**
 * 系统模块公用service，包括基本验证 Title: BaseService Description:
 * 
 * @author kaiming.chi
 * 
 * @date 2013-11-30
 */
@Service
public class SystemBaseService extends BaseService {
	/**
	 * 增删改 用户信息时，权限验证
	 * 
	 * @param loginUser
	 *            当前登录用户
	 * @throws UserException
	 */
	// FIXME 是否有其他验证
	void userAuthorityCheck(User loginUser) throws UserException {
		if (loginUser.getUsertype() != UserType.admin.getType()) {
			throw new UserException(UserException.USER_TYPE_NOT_AUTHORITY);
		}
	}

	/**
	 * 验证是否有操作权限,并且是否当前用户操作
	 * 
	 * @param loginUser
	 * @param userid
	 * @throws UserException
	 */
	void userAuthorityCheck(User loginUser, int userid) throws UserException {
		if (loginUser.getUsertype() != UserType.admin.getType() || userid != loginUser.getId()) {
			throw new UserException(UserException.USER_TYPE_NOT_AUTHORITY);
		}
	}

	/**
	 * 批量保存用户对应角色
	 * 
	 * @param userid
	 *            用户id
	 * @param roleIds
	 *            对应的角色表的角色id list
	 */
	void saveUserRoles(int userid, List<Integer> roleIds) {
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		for (Integer roleid : roleIds) {
			UserRole ur = new UserRole();
			ur.setUserid(userid);
			ur.setRoleid(roleid);
			userRoleList.add(ur);
		}
		wssBaseDao.execute("Role.saveBatchUserRole", userRoleList);
	}

	/**
	 * 根据用户id删除用户对应角色
	 * 
	 * @param userid
	 */
	void delUserRole(int id) {
		wssBaseDao.execute("Role.delRoleByUserId", id);
	}

	/**
	 * 根据用户id删除用户对应角色
	 * 
	 * @param userid
	 */
	void delUserRole(String ids) {
		wssBaseDao.execute("Role.delRoleByUserIds", Lists.newArrayList(Splitter.on(",").split(ids)));
	}
}
