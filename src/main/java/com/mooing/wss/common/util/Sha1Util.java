package com.mooing.wss.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sha1Util {

	private static final Logger log = LoggerFactory.getLogger(Sha1Util.class);

	// 生成随机字符串
	public static String getStr(int n) { // 定义需要生成字符串的位数
		String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random ran = new Random();
		char[] ca = new char[n]; // 定义一个字符数组，用于String创建的构造子
		for (int i = 0; i < ca.length; i++) {
			ca[i] = s.charAt(ran.nextInt(62));
		}
		return new String(ca);
	}
	
	/**
	 * 
	 * @param username 用户名
	 * @param encrypted_password 加密密码
	 * @param random 随机数
	 * @return
	 */
	public static String getSha1(String username, String encrypted_password, String random) {

		String longPwd = encrypted_password + username + random;

		return SHA1(longPwd);

	}

	public static String SHA1(String inStr) {
        MessageDigest md = null;
        String outStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");     //选择SHA-1，也可以选择MD5
            byte[] digest = md.digest(inStr.getBytes());       //返回的是byet[]，要转化为String存储比较方便
            outStr = bytetoString(digest);
        }catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            log.error("SHA1密码加密出错:" + nsae.getMessage(), nsae);
        }
        return outStr;
    }

	public static String bytetoString(byte[] digest) {
		StringBuilder sb = new StringBuilder();
		String tempStr = "";

		for (int i = 1; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				sb.append("0").append(tempStr);
			} else {
				sb.append(tempStr);
			}
		}
		return sb.toString().toLowerCase();
	}
	
	public static void main(String[] args) {
//		System.out.println(getSha1("package","02c6c6a407f5b5e9","VIxaETynmn1bUQGjZcdL"));
		System.out.println(getStr(20));
	}
	
	
}
