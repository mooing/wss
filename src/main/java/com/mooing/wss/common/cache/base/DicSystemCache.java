package com.mooing.wss.common.cache.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.googlecode.ehcache.annotations.Cacheable;
import com.mooing.wss.common.dao.GenericBaseDAO;
import com.mooing.wss.dic.enums.DicSystemBigType;
import com.mooing.wss.dic.enums.DicSystemChildType;
import com.mooing.wss.dic.model.DicSystem;

/**
 * 
 * 系统共用字典缓存
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-19 下午10:57:44
 */
@Component
public class DicSystemCache {
	@Autowired
	@Qualifier("baseDao")
	public GenericBaseDAO wssBaseDao;

	/**
	 * 获取民族数据
	 * 
	 * @param code
	 * @return
	 */
	@Cacheable(cacheName = "dic.getNation")
	public List<DicSystem> getNation() {
		Map<String, Object> param = Maps.newHashMap();
		param.put("bigTypeId", DicSystemBigType.common.getType());
		param.put("childTypeId", DicSystemChildType.nation.getType());
		List<DicSystem> list = wssBaseDao.executeForObjectList("DicSystem.getDicByType", param);
		return list;
	}
	/**
	 * 获取国籍数据
	 * 
	 * @param code
	 * @return
	 */
	@Cacheable(cacheName = "dic.getCitizen")
	public List<DicSystem> getCitizen() {
		Map<String, Object> param = Maps.newHashMap();
		param.put("bigTypeId", DicSystemBigType.common.getType());
		param.put("childTypeId", DicSystemChildType.citizen.getType());
		List<DicSystem> list = wssBaseDao.executeForObjectList("DicSystem.getDicByType", param);
		return list;
	}

}
