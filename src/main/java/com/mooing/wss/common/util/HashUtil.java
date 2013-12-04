/*
 * Copyright (c) 2011 Qunar.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mooing.wss.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hash计算处理工具类。
 * 
 * <p>
 * 使用java.security.MessageDigest获取字符串的Hash值。<br>
 * 用于MD5、SHA1或其他Hash算法。 <strong>使用方法</strong><br>
 * <code><pre>
 *   ...
 *    // 计算用户密码的Hash值
 *    byte[] hash = HashUtil.hashMD5(userPassword);
 *   ...
 *  </pre></code>
 * </p>
 */
public class HashUtil {

	/**
	 * 日志类。
	 */
	private static final Logger log = LoggerFactory.getLogger(HashUtil.class);

	/**
	 * 使用指定算法进行字符串Hash计算。
	 * 
	 * @param algorithm
	 *            算法名称
	 * @param str
	 *            字符串
	 * @return 计算结果
	 * @throws NoSuchAlgorithmException
	 *             算法不存在异常
	 * 
	 */
	public static byte[] hash(String algorithm, String str) throws NoSuchAlgorithmException {
		if (algorithm == null || str == null) {
			return null;
		}
		MessageDigest md = MessageDigest.getInstance(algorithm.toUpperCase());
		return md.digest(str.getBytes());
	}

	/**
	 * 使用MD5进行字符串Hash计算。
	 * 
	 * @param str
	 *            字符串
	 * @return Hash值
	 */
	public static byte[] hashMD5(String str) {
		try {
			return hash("MD5", str);
		} catch (NoSuchAlgorithmException e) {
			log.error("The algorithm is not available" + " in the caller's environment.", e);
			return null; // can't happen
		}
	}

	/**
	 * 使用SHA1进行字符串Hash计算。
	 * 
	 * @param str
	 *            字符串
	 * @return Hash值
	 */
	public static byte[] hashSHA1(String str) {
		try {
			return hash("SHA1", str);
		} catch (NoSuchAlgorithmException e) {
			log.error("The algorithm is not available" + " in the caller's environment.", e);
			return null; // can't happen
		}
	}
}
