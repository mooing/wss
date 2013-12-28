package com.mooing.wss.common.controller;

import static com.mooing.wss.common.web.WebConstants.RESPONSE_STATUS_FAIL;
import static com.mooing.wss.common.web.WebConstants.RESPONSE_STATUS_SUCC;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mooing.wss.common.spring.AntiSamyEditor;
import com.mooing.wss.common.util.PolicyInstance;
import com.mooing.wss.common.util.ServerInfo;
import com.mooing.wss.system.model.User;
import com.mooing.wss.system.model.UserConstants;
import com.mooing.wss.system.model.UserSession;

/**
 * 
 * @author kaiming.chi
 * 
 */
public abstract class BaseController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ReloadableResourceBundleMessageSource _res;

	protected Logger log = LoggerFactory.getLogger(getClass());

	public User getLoginUser(HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute(UserConstants.USER_SESSION_KEY);
		if (userSession != null) {
			return userSession.getUser();
		} else {
			return null;
		}
	}

	protected ModelAndView ajaxDone(int statusCode, String message, String forwardUrl, String navTabId, String rel, String callbackType) {
		ModelAndView mav = new ModelAndView("ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		mav.addObject("forwardUrl", forwardUrl);
		mav.addObject("navTabId", navTabId);
		mav.addObject("rel", rel);
		mav.addObject("callbackType", callbackType);
		return mav;
	}

	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message, "", "", "", "");
	}
	protected ModelAndView ajaxDialogDoneSuccess(String message,String navTabId) {
		return ajaxDone(200, message, "", navTabId, "", "");
	}
	protected ModelAndView ajaxDialogDoneSuccess(String message) {
		return ajaxDone(200, message, "", "", "", "");
	}
	protected ModelAndView ajaxDoneSuccess(String message,String callbackType) {
		return ajaxDone(200, message, "", "", "", callbackType);
	}
	protected ModelAndView ajaxDoneSuccess(String message,String forwardUrl,String navTabId,String rel,String callbackType) {
		return ajaxDone(200, message, forwardUrl, navTabId, rel,callbackType);
	}

	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(300, message, "", "", "", "");
	}

	protected String getMessage(String code) {
		return this.getMessage(code, new Object[] {});
	}

	protected String getMessage(String code, Object arg0) {
		return getMessage(code, new Object[] { arg0 });
	}

	protected String getMessage(String code, Object arg0, Object arg1) {
		return getMessage(code, new Object[] { arg0, arg1 });
	}

	protected String getMessage(String code, Object arg0, Object arg1, Object arg2) {
		return getMessage(code, new Object[] { arg0, arg1, arg2 });
	}

	protected String getMessage(String code, Object arg0, Object arg1, Object arg2, Object arg3) {
		return getMessage(code, new Object[] { arg0, arg1, arg2, arg3 });
	}

	protected String getMessage(String code, Object[] args) {
		// HttpServletRequest request =
		// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale = localeResolver.resolveLocale(request);

		return _res.getMessage(code, args, locale);
		// return "";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();

		request.setAttribute("exception", e);

		if (ServerInfo.isAjax(request) || request.getParameter("ajax") != null) {
			return ajaxDoneError(e.getMessage());
		}

		ModelAndView mav = new ModelAndView("error");
		mav.addObject("statusCode", 300);
		mav.addObject("message", e.getMessage());

		return mav;
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
	/**
	 * 成功返回结果
	 * 
	 * @return
	 */
	protected String success(String navTabId) {
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "success");
		msg.put("navTabId", navTabId);
//		msg.put("rel", "systemUser");
		msg.put("callbackType", "closeCurrent");
		msg.put("forwardUrl", "");
//		msg.put("confirmMsg", "");
		msg.put("statusCode", "200");
		msg.put("status", "1");
		return JSON.toJSONString(msg);
	}

	/**
	 * 失败提示信息
	 * 
	 * @param msg
	 *            提示信息
	 * @return
	 */
	protected String failJSON(String msg) {
		Map<String, Object> msgMap = Maps.newHashMap();
		msgMap.put("statusCode", "300");
		msgMap.put("message", msg);
		msgMap.put("navTabId", "");
		msgMap.put("rel", "");
		msgMap.put("callbackType", "");
		msgMap.put("forwardUrl", "");
		msgMap.put("confirmMsg", "");
		return JSON.toJSONString(msgMap);
	}
}
