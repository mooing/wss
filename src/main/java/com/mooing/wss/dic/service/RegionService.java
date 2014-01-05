package com.mooing.wss.dic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mooing.wss.common.cache.base.RegionCache;
import com.mooing.wss.common.dao.GenericBaseDAO;
import com.mooing.wss.dic.model.Region;
import com.mooing.wss.system.model.User;

@Service
public class RegionService {

	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;
	@Autowired
	private RegionCache regionCache;

	public String findRegionByPid(String pcode) {
		List<Region> regionList = regionCache.findByPcode(pcode);
		String jsonString = JSON.toJSONString(regionList);
		return jsonString;
	}

	public String findRegionByLevel(int level) {
		List<Region> regionList = regionCache.findRegionByLevel(level);
		String jsonString = JSON.toJSONString(regionList);
		return jsonString;
	}

	public String findProvince() {
		List<Region> regionList = regionCache.findRegionByLevel(1);
		String jsonString = JSON.toJSONString(regionList);
		return jsonString;
	}

	/**
	 * 获取当前登录用户下所有地区
	 * 
	 * @param regionCode
	 *            当前登录用户所属地区编码
	 * @param loginUser
	 *            登录用户
	 * @return
	 */
	public String findRegionByCode(String regionCode, User loginUser) {
		List<Region> regionList = Lists.newArrayList();
		if (loginUser.getUsername().equals("admin")) {
			regionList = regionCache.findAllRegion();
		} else {
			regionList = wssBaseDao.executeForObjectList("Region.findRegionByLikeCode", regionCode);
		}
		return JSON.toJSONString(regionList);
	}

}
