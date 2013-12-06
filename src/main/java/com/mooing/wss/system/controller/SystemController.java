package com.mooing.wss.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.common.controller.BaseController;
import com.mooing.wss.common.util.Sha1Util;
import com.mooing.wss.common.util.ValidCodeUtil;
import com.mooing.wss.system.model.User;
import com.mooing.wss.system.model.UserConstants;
import com.mooing.wss.system.model.UserSession;
import com.mooing.wss.system.service.SystemService;
import com.mooing.wss.system.service.UserService;

/**
 * 系统登录相关,不受拦截器控制
 * 
 * @author kaiming.chi
 * 
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(SystemController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SystemService systemService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		log.info("当前登录用户请求的的路径为：{}", requestUrl);
		return new ModelAndView("user/login");
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		return new ModelAndView("layout");
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView loginAcion(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request,
			@RequestParam("validCode") String validCode,HttpServletResponse response) {

		String errorMsg = "";

		ModelAndView mv = null;
		if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0) {
			mv = new ModelAndView("user/login");
			errorMsg = "登录失败, 用户名或密码不能为空!";
			mv.addObject("errorMsg", errorMsg);
			return mv;
		}
		
		//验证码
		boolean codeRes=systemService.checkValidCode(validCode, request.getSession());
		if(!codeRes){
			mv = new ModelAndView("user/login");
			errorMsg = "登录失败, 验证码不正确!";
			mv.addObject("errorMsg", errorMsg);
			return mv;
		}

		// 从db获取用户信息
		User user = userService.getByUserName(username);
		if (user == null) {
			mv = new ModelAndView("user/login");
			errorMsg = "登录失败, 用户名或密码错误!";
			mv.addObject("errorMsg", errorMsg);
			return mv;
		}

		String encrypted_password = userService.getSQLPassword(password.trim());
		String sha_password = Sha1Util.getSha1(user.getUsername(), encrypted_password, user.getRandom());
		if (!user.getPassword().equals(sha_password)) {
			mv = new ModelAndView("user/login");
			errorMsg = "登录失败, 用户名或密码错误!";
			mv.addObject("errorMsg", errorMsg);
			return mv;
		}
		if (user.getStatus() == User.STATUS_LOCKED) {
			mv = new ModelAndView("user/login");
			errorMsg = "登陆失败, 当前帐户已被锁定!";
			mv.addObject("errorMsg", errorMsg);
			return mv;
		}

		UserSession userSession = new UserSession(user);
		// 设置用户会话信息
		HttpSession session = request.getSession();
		session.setAttribute(UserConstants.USER_SESSION_KEY, userSession);

		log.info("login succ! username - {} , ip: {}", username, this.getIpAddr(request));
		mv = new ModelAndView("redirect:/user/list");

		return mv;
	}

	/**
	 * 退出
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping("logout")
	public String logoutAction(HttpServletRequest request, HttpServletResponse response) {
		// 注销时销毁Session
		HttpSession session = request.getSession();
		session.removeAttribute(UserConstants.USER_SESSION_KEY);
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("validcode")
	public void validCode(HttpServletRequest request, HttpServletResponse response) {
		ValidCodeUtil.generationCode(request, response);
	}

	/**
	 * 验证 验证码是否整
	 */
	@RequestMapping(value = "checkvalidcode", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkValidCode(@RequestParam("validCode") String validCode, HttpSession session) {
		return systemService.checkValidCode(validCode, session);
	}

	//
	// @RequestMapping(value = "updatepassword", method = RequestMethod.POST)
	// public ModelAndView updatepassword(@ModelAttribute("user") User user,
	// HttpSession session) {
	// User loginUser = getLoginUser(session);
	// String password = user.getPassword();
	// String oldPassword = user.getOldPassword();
	// String confirmPassword = user.getConfirmPassword();
	// String message = "";
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("user/editpassword");
	// mv.addObject("user", loginUser);
	// if (oldPassword == null || oldPassword.trim().length() == 0 || password
	// == null || password.trim().length() == 0 || confirmPassword == null
	// || confirmPassword.trim().length() == 0) {
	// message = "原密码，新密码和确认密码都不能为空!";
	// mv.addObject("message", message);
	// return mv;
	// }
	// if (!password.trim().equals(confirmPassword.trim())) {
	// message = "两次输入的密码不一样，请重新输入!";
	// mv.addObject("message", message);
	// return mv;
	// }
	// User existUser = userService.getById(user.getId());
	// if (existUser == null) {
	// message = "用户不存在!";
	// mv.addObject("message", message);
	// return mv;
	// } else {
	// String old_encrypted_password =
	// userService.getSQLPassword(oldPassword.trim());
	// String old_sha_password = Sha1Util.getSha1(existUser.getUsername(),
	// old_encrypted_password, existUser.getRandom());
	//
	// if (loginUser.getPassword().equals(old_sha_password)) {
	// String new_random = Sha1Util.getStr(20);
	// String new_encrypted_password =
	// userService.getSQLPassword(password.trim());
	// String new_sha_password = Sha1Util.getSha1(existUser.getUsername(),
	// new_encrypted_password, new_random);
	// loginUser.setPassword(new_sha_password);
	// loginUser.setUpdateUserId(loginUser.getId());
	// loginUser.setUpdateTime(new Date());
	// loginUser.setRandom(new_random);
	// loginUser.setHashPassword(new_encrypted_password);
	// loginUser.setIsPwdValid(User.PASSWORD_VALID_STATUS);
	// userService.updatePassword(loginUser);
	// message = "密码修改成功!";
	// mv.addObject("message", message);
	// return mv;
	// } else {
	// message = "旧密码输入错误!";
	// mv.addObject("message", message);
	// return mv;
	// }
	// }
	//
	// }

}
