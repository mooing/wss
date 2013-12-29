package com.mooing.wss.common.cache.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mooing.wss.common.dao.GenericBaseDAO;
import com.mooing.wss.dic.model.Region;

/**
 * 
 * 地区缓存类
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-19
 */
@Component
public class RegionCache {
	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;

	/**
	 * 按父地区编号，查询下级所有信息
	 * 
	 * @return
	 */
	@Cacheable(cacheName = "region.findByPcode")
	public List<Region> findByPcode(String pcode) {
		List<Region> list = new ArrayList<Region>();
		list = wssBaseDao.executeForObjectList("Region.findByPcode", pcode);
		return list;
	}

	/**
	 * 查询 所有信息
	 * 
	 * @return
	 */
	@Cacheable(cacheName = "region.findAllRegion")
	public List<Region> findAllRegion() {
		List<Region> list = new ArrayList<Region>();
		list = wssBaseDao.executeForObjectList("Region.findAllRegion", null);
		return list;
	}

}
