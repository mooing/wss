package com.mooing.wss.hos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.mooing.wss.common.cache.base.UnitCache;
import com.mooing.wss.common.dao.GenericBaseDAO;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.common.util.Sha1Util;
import com.mooing.wss.hos.model.Doctor;
import com.mooing.wss.system.model.User;

/**
 * 
 * 医生管理
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-29 下午11:14:54
 */
@Service
public class DoctorService {
	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;
	@Resource
	private UnitCache unitCache;

	private static final Logger log = LoggerFactory.getLogger(DoctorService.class);

	public Pagination<Doctor> pageList(SearchBoxModel searchBox, Map<String, Object> search) {
		Integer count = wssBaseDao.executeForObject("Doctor.findDoctorCount", search, Integer.class);
		Pagination<Doctor> page = null;
		List<Doctor> list = new ArrayList<Doctor>();
		if (count != null && count != 0) {
			page = new Pagination<Doctor>(count, searchBox.getPageNum(), searchBox.getNumPerPage());
			search.put("startrecord", page.getStartPosition());
			search.put("recordsize", searchBox.getNumPerPage());
			list = wssBaseDao.executeForObjectList("Doctor.findAllDoctor", search);
			page.bindData(list);
		}
		return page;
	}

	/**
	 * 去新增Doctor
	 * 
	 * @param loginUser
	 */
	public Map<String, Object> toAddDoctor(User loginUser) {
		Map<String, Object> map = Maps.newHashMap();
		// 查询所有单位信息
		map.put("hospitalList", unitCache.hospitalAllCache());
		return map;
	}

	/**
	 * 医生管理中，保存Doctor信息
	 * 
	 * @param role
	 * @throws UserException
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addDoctor(Doctor doctor) throws UserException {
		// 保存用户信息
		// 判断用户名是否存在
		Integer userCount = wssBaseDao.executeForObject("User.getUserCountByName", doctor.getUsername(), Integer.class);
		if (userCount != null && userCount > 0) {
			throw new UserException(UserException.USER_NAME_ISEXIST);
		}
		User user = new User();
		user.setUsername(doctor.getUsername());
		// 1.保存用户
		String sqlPassword = wssBaseDao.executeForObject("User.getSQLPassword", doctor.getPassword(), String.class);
		String random = Sha1Util.getStr(20);
		String realPass = Sha1Util.getSha1(user.getUsername(), sqlPassword, random);
		user.setRandom(random);
		user.setPassword(realPass);
		wssBaseDao.execute("User.saveUser", user);
		doctor.setUserid(user.getId());
		// 保存医生
		wssBaseDao.execute("Doctor.saveDoctorAtDoc", doctor);
	}

	/**
	 * 去修改Doctor
	 * 
	 * @param loginUser
	 * @param id
	 * @throws UserException
	 */
	public Doctor toUpdateDoctor(User loginUser, int doctorId) throws UserException {
		// userAuthorityCheck(loginUser);
		Doctor doctor = wssBaseDao.executeForObject("Doctor.findDoctorById", doctorId, Doctor.class);
		if (doctor == null) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		return doctor;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateDoctor(Doctor doctor) throws UserException {
		// 判断角色名是否存在
		// Integer roleCount =
		// wssBaseDao.executeForObject("Hospital.findHospitalByName",
		// role.getRolename(), Integer.class);
		// if (roleCount != null && roleCount > 0) {
		// throw new UserException(UserException.ROLE_NAME_ISEXIST);
		// }
		wssBaseDao.execute("Doctor.updateDoctorById", doctor);
	}

	/**
	 * 删除Doctor
	 * 
	 * @param loginUser
	 * @param id
	 * @throws UserException
	 */
	public void delDoctor(User loginUser, int doctorId) throws UserException {
		wssBaseDao.execute("Doctor.delDoctorById", doctorId);
	}

}
