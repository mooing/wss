package com.mooing.wss.common.email;

import java.util.regex.Pattern;

public class MailUtil {
	public static boolean checkEmail(String email) {
		String pattern = "(\\w|\\.|\\-)+@(\\w|\\.|\\-)+\\.[a-zA-Z]{2,4}";
		boolean isEmail = false;
		if (email != null && !email.trim().equals("")) {
			isEmail = Pattern.compile(pattern).matcher(email.trim()).matches();
		}
		return isEmail;
	}
	
	/**
	 * 多个邮箱
	 * @param email
	 * @return
	 */
	public static boolean checkEmails(String email) {
		String pattern = "((\\w|\\.|\\-)+@(\\w|\\.|\\-)+\\.[a-zA-Z]{2,4}|,)+";
		boolean isEmail = false;
		if (email != null && !email.trim().equals("")) {
			isEmail = Pattern.compile(pattern).matcher(email.trim()).matches();
		}
		return isEmail;
	}
	
	public static void main(String[] args) {
		String email = "xx@xx.com.dafa";
		System.out.println(checkEmail(email));
	}
}
