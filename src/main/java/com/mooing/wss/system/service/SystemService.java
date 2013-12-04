package com.mooing.wss.system.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mooing.wss.system.model.UserConstants;

/**
 * 系统service 包括用户登录 验证码 修改密码等
 * 
 * Title: SystemService Description:
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-1
 */
@Service
public class SystemService extends SystemBaseService {

	public boolean checkValidCode(String validCode, HttpSession session) {
		boolean res = false;
		Object validCodeSession = session.getAttribute(UserConstants.VALID_CODE);
		if (validCodeSession != null && validCode.equalsIgnoreCase(validCodeSession.toString())) {
			res = true;
		}
		return res;
	}
}
