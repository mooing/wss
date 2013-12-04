package com.mooing.wss.common.cache.base;

import java.util.Date;

public class CachedClient {
//	// 创建cachedClient
//	protected static MemCachedClient memCachedClient = new MemCachedClient();
//
//	static {
//		String serverIp = "127.0.0.1:11211";
//		String[] servers = null;
//		if (serverIp != null && serverIp.trim().length() > 0) {
//			servers = serverIp.split(",");
//		} else {
//			servers = new String[] { "127.0.0.1:11211" };
//		}
//
//		Integer[] weights = { 3 };
//		// 创建连接池
//		SockIOPool pool = SockIOPool.getInstance();
//		// 设置属性
//		pool.setServers(servers);
//		pool.setWeights(weights);
//		// 初始连接数
//		pool.setInitConn(5);
//		pool.setMinConn(5);
//		pool.setMaxConn(250);
//		pool.setMaxIdle(1000 * 60 * 60 * 6);
//		pool.setMaintSleep(30);
//		pool.setNagle(false);
//		pool.setSocketTO(3000);
//		pool.setSocketConnectTO(0);
//		// 初始化
//		pool.initialize();
//		// 是否压缩
//		memCachedClient.setCompressEnable(true);
//		// 设置压缩大小
//		memCachedClient.setCompressThreshold(64 * 1024);
//		System.out.println("CachedClient started ,cache server Ip is: ");
//		for (String s : servers) {
//			System.out.println(s);
//		}
//
//	}
//
//	/**
//	 * 设置缓存键值，缓存时间
//	 * 
//	 * @param key
//	 *            缓存键
//	 * @param object
//	 *            缓存的值
//	 * @param idleTime
//	 *            缓存时间
//	 */
//	public static void put(String key, Object object, long idleTime) {
//		if (object != null) {
//			memCachedClient.set(key, object, new Date(System.currentTimeMillis() + idleTime));
//		}
//	}
//
//	/**
//	 * 通过键获取值
//	 * 
//	 * @param key
//	 *            键
//	 * @return
//	 */
//	public static Object get(String key) {
//		return memCachedClient.get(key);
//	}
//
//	/**
//	 * 根据键删除对应的值
//	 * 
//	 * @param key
//	 */
//	public static void delete(String key) {
//		memCachedClient.delete(key);
//	}
}
