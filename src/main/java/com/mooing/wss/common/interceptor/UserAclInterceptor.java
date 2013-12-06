package com.mooing.wss.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.common.web.RequestUtil;
import com.mooing.wss.system.model.UserConstants;
import com.mooing.wss.system.model.UserSession;

/**
 * 
* Title: UserAclInterceptor
* Description: 
*
* @author kaiming.chi
*
* @date 2013-11-29
 */
public class UserAclInterceptor implements HandlerInterceptor {

	// private static final Logger log =
	// LoggerFactory.getLogger(UserAclInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = ((HttpServletRequest) req).getSession();
		UserSession userSession = (UserSession) session.getAttribute(UserConstants.USER_SESSION_KEY);

		String pathInfo = RequestUtil.getPathInfo(req);

		// 处理未登录和session失效重定向到登陆页面
		if (userSession == null && !pathInfo.endsWith("/system/login")&& !pathInfo.endsWith("/system/validcode")&& !pathInfo.endsWith("/system/checkvalidcode")) {
			res.sendRedirect(req.getContextPath() + "/system/login");
			return false;
		}
		return true;
	}
}
