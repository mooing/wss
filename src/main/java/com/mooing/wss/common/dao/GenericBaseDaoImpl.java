package com.mooing.wss.common.dao;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericBaseDaoImpl extends SqlSessionDaoSupport implements GenericBaseDAO {
	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(GenericBaseDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForObject(java.lang.String,
	 * java.lang.Object, java.lang.Class)
	 */
	@Override
	public <E> E executeForObject(String sqlID, Object bindParams, Class<E> clazz) {

		if (log.isDebugEnabled()) {
			log.debug("executeForObject Start.");
		}

		E rObj = null;
		try {
			// 执行SQL：取得记录
			Object obj = getSqlSession().selectOne(sqlID, bindParams);
			if (log.isDebugEnabled() && obj != null) {
				log.debug("Return type:" + obj.getClass().getName());
			}
			if (clazz != null && obj != null) {
				rObj = (E) clazz.cast(obj);
			}
		} catch (ClassCastException e) {
			throw new ClassCastException();
		}

		if (log.isDebugEnabled()) {
			log.debug("executeForObject End.");
		}

		return rObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qunar.base.meerkat.dao.QueryDAO#executeForMap(java.lang.String,
	 * java.lang.Object)
	 */
	public Map<String, Object> executeForMap(String sqlID, Object bindParams) {

		if (log.isDebugEnabled()) {
			log.debug("executeForMap Start.");
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> rObj = this.executeForObject(sqlID, bindParams, Map.class);

		if (log.isDebugEnabled()) {
			log.debug("executeForMap End.");
		}

		return rObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForObjectArray(java.lang.String
	 * , java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<E> clazz) {

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectArray Start.");
		}

		if (clazz == null) {
			throw new ClassCastException();
		}

		E[] retArray;
		try {
			// 执行SQL：取得记录
			List<E> list = getSqlSession().selectList(sqlID, bindParams);
			retArray = (E[]) Array.newInstance(clazz, list.size());
			list.toArray(retArray);
		} catch (ArrayStoreException e) {
			throw new ArrayStoreException();
		}

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectArray End.");
		}

		return retArray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForMapArray(java.lang.String,
	 * java.lang.Object)
	 */
	public Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams) {

		if (log.isDebugEnabled()) {
			log.debug("executeForMapArray Start.");
		}

		@SuppressWarnings("unchecked")
		Map<String, Object>[] map = executeForObjectArray(sqlID, bindParams, Map.class);

		if (log.isDebugEnabled()) {
			log.debug("executeForMapArray End.");
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForObjectArray(java.lang.String
	 * , java.lang.Object, java.lang.Class, int, int)
	 */
	@SuppressWarnings("unchecked")
	public <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<E> clazz, int beginIndex, int maxCount) {

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectArray Start.");
		}

		if (clazz == null) {
			throw new ClassCastException();
		}

		E[] retArray;
		try {
			// 执行SQL：取得记录
			List<E> list = getSqlSession().selectList(sqlID, bindParams, new RowBounds(beginIndex, maxCount));

			retArray = (E[]) Array.newInstance(clazz, list.size());
			list.toArray(retArray);
		} catch (ArrayStoreException e) {
			throw new ClassCastException();
		}

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectArray End.");
		}

		return retArray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForMapArray(java.lang.String,
	 * java.lang.Object, int, int)
	 */
	public Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams, int beginIndex, int maxCount) {

		if (log.isDebugEnabled()) {
			log.debug("executeForMapArray Start.");
		}

		@SuppressWarnings("unchecked")
		Map<String, Object>[] map = executeForObjectArray(sqlID, bindParams, Map.class, beginIndex, maxCount);

		if (log.isDebugEnabled()) {
			log.debug("executeForMapArray End.");
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForObjectList(java.lang.String
	 * , java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> executeForObjectList(String sqlID, Object bindParams) {

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectList Start.");
		}

		// 执行SQL：取得记录
		List<E> list = getSqlSession().selectList(sqlID, bindParams);

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectList End.");
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForMapList(java.lang.String,
	 * java.lang.Object)
	 */
	public List<Map<String, Object>> executeForMapList(String sqlID, Object bindParams) {

		if (log.isDebugEnabled()) {
			log.debug("executeForMapList Start.");
		}

		List<Map<String, Object>> mapList = executeForObjectList(sqlID, bindParams);

		if (log.isDebugEnabled()) {
			log.debug("executeForMapList End.");
		}

		return mapList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForObjectList(java.lang.String
	 * , java.lang.Object, int, int)
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> executeForObjectList(String sqlID, Object bindParams, int beginIndex, int maxCount) {

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectList Start.");
		}

		// 执行SQL：取得记录
		List<E> list = getSqlSession().selectList(sqlID, bindParams, new RowBounds(beginIndex, maxCount));

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectList End.");
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qunar.base.meerkat.dao.QueryDAO#executeForMapList(java.lang.String,
	 * java.lang.Object, int, int)
	 */
	public List<Map<String, Object>> executeForMapList(String sqlID, Object bindParams, int beginIndex, int maxCount) {

		if (log.isDebugEnabled()) {
			log.debug("executeForMapList Start.");
		}

		List<Map<String, Object>> mapList = executeForObjectList(sqlID, bindParams, beginIndex, maxCount);

		if (log.isDebugEnabled()) {
			log.debug("executeForMapList End.");
		}

		return mapList;
	}

	@Override
	public int execute(String sqlID, Object bindParams) {

		if (log.isDebugEnabled()) {
			log.debug("execute Start.");
		}

		// 执行SQL: 更新数据
		int row = getSqlSession().update(sqlID, bindParams);

		if (log.isDebugEnabled()) {
			log.debug("execute End. success count:" + row);
		}

		return row;
	}
}
