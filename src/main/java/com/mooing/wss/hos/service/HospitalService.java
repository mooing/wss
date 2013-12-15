package com.mooing.wss.hos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.TriggersRemove;
import com.mooing.wss.common.cache.base.UnitCache;
import com.mooing.wss.common.dao.GenericBaseDAO;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.hos.model.Hospital;
import com.mooing.wss.system.model.User;

@Service
public class HospitalService {
	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;
	@Resource
	private UnitCache unitCache;

	public Pagination<Hospital> pageList(SearchBoxModel searchBox, Map<String, Object> search) {
		search.put("startrecord", searchBox.getPnum());
		search.put("recordsize", searchBox.getPsize());
		Integer count = wssBaseDao.executeForObject("Hospital.findAllHospitalCount", search, Integer.class);
		Pagination<Hospital> page = null;
		List<Hospital> list = new ArrayList<Hospital>();
		if (count != null && count != 0) {
			page = new Pagination<Hospital>(count, searchBox.getPnum(), searchBox.getPsize());
			list = wssBaseDao.executeForObjectList("Hospital.findAllHospitalPage", search);
			page.bindData(list);
		}
		return page;
	}

	/**
	 * 去新增Hospital
	 * 
	 * @param loginUser
	 */
	public void toAddHospital(User loginUser) {
	}

	/**
	 * 保存Hospital信息
	 * 
	 * @param role
	 * @throws UserException
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@TriggersRemove(cacheName = "hospitalAllCache", removeAll = true)
	public void addHospital(Hospital hospital) throws UserException {
		// 判断角色名是否存在
		// Integer roleCount =
		// wssBaseDao.executeForObject("Role.findRoleByName",
		// role.getRolename(), Integer.class);
		// if (roleCount != null && roleCount > 0) {
		// throw new UserException(UserException.ROLE_NAME_ISEXIST);
		// }
		wssBaseDao.execute("Hospital.saveHospital", hospital);
	}

	/**
	 * 去修改Hospital
	 * 
	 * @param loginUser
	 * @param hospitalid
	 * @throws UserException
	 */
	public Hospital toUpdateHospital(User loginUser, int hospitalId) throws UserException {
		// userAuthorityCheck(loginUser);
		Hospital hospital = wssBaseDao.executeForObject("Hospital.findHospitalById", hospitalId, Hospital.class);
		if (hospital == null) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		return hospital;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@TriggersRemove(cacheName = "hospitalAllCache", removeAll = true)
	public void updateHospital(Hospital hospital) throws UserException {
		// 判断角色名是否存在
		// Integer roleCount =
		// wssBaseDao.executeForObject("Hospital.findHospitalByName",
		// role.getRolename(), Integer.class);
		// if (roleCount != null && roleCount > 0) {
		// throw new UserException(UserException.ROLE_NAME_ISEXIST);
		// }
		wssBaseDao.execute("Hospital.updateHospitalById", hospital);
	}

	/**
	 * 删除Hospital
	 * 
	 * @param loginUser
	 * @param roleid
	 * @throws UserException
	 */
	@TriggersRemove(cacheName = "hospitalAllCache", removeAll = true)
	public void delHospital(User loginUser, int hospitalid) throws UserException {
		wssBaseDao.execute("Hospital.delHospitalById", hospitalid);
	}
}
