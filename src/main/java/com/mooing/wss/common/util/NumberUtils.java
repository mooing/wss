package com.mooing.wss.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Pattern;


public class NumberUtils {

	/**
	 * float转换成可靠精度的 double
	 */
	public static double getDouble(float f) {
		BigDecimal bd = new BigDecimal(String.valueOf(f));
		return bd.doubleValue();
	}

	/**
	 * double 转换成float
	 */
	public static float getFloat(Double d) {
		BigDecimal bd = new BigDecimal(d);
		return bd.floatValue();
	}

	/**
	 * 获取两位float
	 */
	public static String getfloat2(Object f) {
		DecimalFormat myformat = new java.text.DecimalFormat("#0.00");
		return myformat.format(f);
	}

	/**
	 * 获取两位double
	 */
	public static String getDouble2(double f) {
		DecimalFormat myformat = new java.text.DecimalFormat("#0.00");
		return myformat.format(f);
	}

	/**
	 * 获取两位float
	 */
	public static String gettotalfloat2(float f, float num) {
		DecimalFormat myformat = new java.text.DecimalFormat("#0.00");
		return myformat.format(f * num);
	}

	/**
	 * 获取两位float
	 */
	public static float getf2(float f) {
		String str = getfloat2(f);
		return Float.valueOf(str);
	}

	/**
	 * 
	 * 截取字符串
	 * 
	 * @param source
	 *            源字符串
	 * @param length
	 *            需要截取的长度
	 * @param suffix
	 *            截取后添加的后缀
	 * @return
	 */
	public static String subString(String source, int length, String suffix) {
		if (source == null) {
			return "";
		} else {
			if (source.length() > length) {
				source = source.substring(0, length);
				if (suffix != null) {
					source += suffix;
				}
			}
		}
		return source;
	}


	/**
	 * 获取随机四位数.
	 */
	public static String getFourCode() {
		String desc = "0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			int idx = (int) (Math.random() * desc.length());
			sb.append(desc.charAt(idx));
		}
		return sb.toString();
	}



	/**
	 * 判断字符串是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	private static Random rnd = new Random();

	public static String getRandomNumber(int digCount) {
		StringBuilder sb = new StringBuilder(digCount);
		for (int i = 0; i < digCount; i++)
			sb.append((char) ('0' + rnd.nextInt(10)));
		return sb.toString();
	}
}
