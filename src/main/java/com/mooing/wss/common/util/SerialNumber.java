package com.mooing.wss.common.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成支付流水id
 * 
 * @author kaiming.chi
 * 
 */
public class SerialNumber {

	private static int index = 0; // 累加序数

	/**
	 * 
	 * @param datePattern
	 *            时间精确到哪一位形成数字，如精确到秒：yyMMddHHmmss
	 * @param numPattern
	 *            要几位累加数字，如，四位：0000
	 * @return 流水号
	 */
	public static String getSerialNumber(String tem, String datePattern, String numPattern) {
		Date date = new Date(System.currentTimeMillis());

		DecimalFormat df = new DecimalFormat(numPattern);
		int denominator = Integer.parseInt("1" + numPattern);
		int temIndex = index % denominator;
		String indexStr = df.format(temIndex);
		Random random = new Random();

		char[] b = { (char) (random.nextInt(100) % 26 + 65), (char) (random.nextInt(100) % 26 + 65) };
		String randomAlp = new String(b);
		// 时间和累加数字进行拼接
		String codeStr = tem + DateUtil.dateToString(date, datePattern) + indexStr + randomAlp;
		index++;
		return codeStr;
	}

	public static String getSerNumberById(String datePattern, String numPattern) {
		String indexStr = "0000";
		String codeStr = getSerialNumber(indexStr, datePattern, numPattern);
		return codeStr;
	}
}
