package com.mooing.wss.hos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.mooing.wss.common.cache.base.UnitCache;
import com.mooing.wss.common.dao.GenericBaseDAO;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.hos.model.Hospital;
import com.mooing.wss.system.model.User;

/**
 * 单位管理
 * 
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-29 下午11:14:31
 */
@Service
public class UnitService {
	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;
	@Resource
	private UnitCache unitCache;

	private static final Logger log = LoggerFactory.getLogger(UnitService.class);

	public Pagination<Hospital> pageList(SearchBoxModel searchBox, Map<String, Object> search) {
		Integer count = wssBaseDao.executeForObject("Hospital.findAllHospitalCount", search, Integer.class);
		Pagination<Hospital> page = null;
		List<Hospital> list = new ArrayList<Hospital>();
		if (count != null && count != 0) {
			page = new Pagination<Hospital>(count, searchBox.getPageNum(), searchBox.getNumPerPage());
			search.put("startrecord", page.getStartPosition());
			search.put("recordsize", searchBox.getNumPerPage());
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
	public void toAddUnit(User loginUser) {
		// TODO Auto-generated method stub

	}

	/**
	 * 保存Hospital信息
	 * 
	 * @param role
	 * @throws UserException
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@TriggersRemove(cacheName = "hospitalAllCache", removeAll = true)
	public void addUnit(Hospital hospital) throws UserException {
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
	public Hospital toUpdateUnit(User loginUser, int hospitalId) throws UserException {
		// userAuthorityCheck(loginUser);
		Hospital hospital = wssBaseDao.executeForObject("Hospital.findHospitalById", hospitalId, Hospital.class);
		if (hospital == null) {
			throw new UserException(UserException.USER_DEFAULT_EXCEPTION);
		}
		return hospital;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@TriggersRemove(cacheName = "hospitalAllCache", removeAll = true)
	public void updateUnit(Hospital hospital) throws UserException {
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
	public void delUnit(User loginUser, int hospitalid) throws UserException {
		wssBaseDao.execute("Hospital.delHospitalById", hospitalid);
	}

	/**
	 * 获取所有树数据
	 * 
	 * @return
	 */
	public String findAllUnitTree() {
		String jsonString = JSON.toJSONString(unitCache.hospitalAllCache());
		log.info("UnitSerivce| findAllUnitTree ,json:{}", jsonString);
		return jsonString;
	}

	/**
	 * 获取当前登录用户下所有单位
	 * 
	 * @param regionCode
	 * @param loginUser
	 * @return
	 */
	public String findUnitByCode(String regionCode, User loginUser) {
		List<Hospital> unitList = Lists.newArrayList();
		int pUnitId=0;
			Map<String, String> map = Maps.newHashMap();
			map.put("regionCode", "41");
			unitList = wssBaseDao.executeForObjectList("Hospital.findUnitByLikeRegionCode", map);
			if(CollectionUtils.isNotEmpty(unitList)){
				pUnitId=unitList.get(0).getPid();
			}
		Map<String, Object> jsonMap=new HashMap<String, Object>();
		jsonMap.put("pUnitId", pUnitId);
		jsonMap.put("unitList", unitList);
		return JSON.toJSONString(jsonMap);
	}

}
