package com.mooing.wss.common.controller;

import static com.mooing.wss.common.web.WebConstants.RESPONSE_STATUS_FAIL;
import static com.mooing.wss.common.web.WebConstants.RESPONSE_STATUS_SUCC;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.common.spring.AntiSamyEditor;
import com.mooing.wss.common.util.PolicyInstance;
import com.mooing.wss.system.model.User;
import com.mooing.wss.system.model.UserConstants;
import com.mooing.wss.system.model.UserSession;

/**
 * 
 * @author kaiming.chi
 * 
 */
public class BaseController {

	protected Logger log = LoggerFactory.getLogger(getClass());

	public User getLoginUser(HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute(UserConstants.USER_SESSION_KEY);
		if (userSession != null) {
			return userSession.getUser();
		} else {
			return null;
		}
	}

	/**
	 * return 404 view
	 */
	protected ModelAndView view404(String errorMsg) {
		ModelAndView mv = new ModelAndView("error/404");
		mv.addObject("errMsg", errorMsg);
		return mv;
	}

	public Map<String, Object> succ(String msg) {
		return succ(msg, null);
	}

	public Map<String, Object> succ(String msg, Object data) {
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", RESPONSE_STATUS_SUCC);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}

	public Map<String, Object> fail(String msg) {
		return fail(msg, null);
	}

	public Map<String, Object> fail(String msg, Object data) {
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", RESPONSE_STATUS_FAIL);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}

	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * trim all form string field
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(" \t\r\n\f", false));
		binder.registerCustomEditor(String.class, new AntiSamyEditor(PolicyInstance.getInstance()));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

}
