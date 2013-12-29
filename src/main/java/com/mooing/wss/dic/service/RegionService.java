package com.mooing.wss.dic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mooing.wss.common.cache.base.RegionCache;
import com.mooing.wss.dic.model.Region;

@Service
public class RegionService {
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

}
