package com.mooing.wss.common.cache.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.googlecode.ehcache.annotations.Cacheable;
import com.mooing.wss.common.dao.GenericBaseDAO;
import com.mooing.wss.hos.model.Hospital;

/**
 * 单位模块缓存
 * 
 * 
 * Title: UnitCache
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-15
 */
@Component
public class UnitCache {
	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;

	/**
	 * 查询所有Hospital
	 * 
	 * @return
	 */
	@Cacheable(cacheName = "hospitalAllCache")
	public Map<Integer, Hospital> hospitalAllCache() {
		List<Hospital> list = new ArrayList<Hospital>();
		Map<Integer, Hospital> hosMap = Maps.newHashMap();
		list = wssBaseDao.executeForObjectList("Hospital.findHospitalAndRegion", null);
		if (!CollectionUtils.isEmpty(list)) {
			for (Hospital hospital : list) {
				hosMap.put(hospital.getId(), hospital);
			}

		}
		return hosMap;
	}

}
