package com.mooing.wss.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.common.cache.base.SystemCache;
import com.mooing.wss.common.controller.BaseController;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.system.enums.UserType;
import com.mooing.wss.system.model.Role;
import com.mooing.wss.system.model.User;
import com.mooing.wss.system.service.UserService;

/**
 * 
 * @author kaiming.chi
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SystemCache systemCache;

	@RequestMapping("list")
	public ModelAndView findAllUser(SearchBoxModel searchBox, HttpSession session) {
		ModelAndView mv = new ModelAndView("user/userList");
		Map<String, Object> search = new HashMap<String, Object>();
		Pagination<User> page = userService.pageList(searchBox, search);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 去添加用户
	 * 
	 * @return
	 */
	@RequestMapping("toadd")
	public ModelAndView toAdd(HttpSession session) {
		ModelAndView mv = new ModelAndView("user/userAdd");
		try {
			User loginUser = getLoginUser(session);
			List<Role> roleList = new ArrayList<Role>();
			roleList = userService.toAdd(loginUser);
			mv.addObject("roleList", roleList);
			mv.addObject("userTypeList", UserType.getAllUserType());// 获取所有用户类型
		} catch (UserException e) {
			// 用户没有权限
			if (e.getMessage().equals(UserException.USER_TYPE_NOT_AUTHORITY)) {
				mv.addObject("error", "1");
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return mv;
	}

	@RequestMapping("add")
	public ModelAndView add(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			User loginUser = getLoginUser(session);
			userService.add(loginUser, user);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (UserException e) {
			mv.addObject("user", new User());
			// 用户名已存在,安表示页面显示
			if (UserException.USER_NAME_ISEXIST.equals(e.getMessage())) {
				mv.addObject("error", "1");
			}
			return ajaxDoneError("用户名已存在！");
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
			return ajaxDoneError("error");
		}
	}

	/**
	 * 去修改用户信息
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping("toupdate")
	public ModelAndView toUpdate(@RequestParam(value = "userid") int userid, HttpSession session) {
		ModelAndView mv = new ModelAndView("user/userUpdate");
		try {
			User user = new User();
			User loginUser = getLoginUser(session);
			user = userService.toUpdate(loginUser, userid);
			mv.addObject("user", user);
			mv.addObject("roleList", systemCache.findAllRole());
			mv.addObject("userTypeList", UserType.getAllUserType());
		} catch (UserException e) {
			// 用户没有权限
			if (e.getMessage().equals(UserException.USER_TYPE_NOT_AUTHORITY)) {
				mv.addObject("error", "1");
			}
			// 用户不存在
			if (e.getMessage().equals(UserException.USER_IS_NOT_EXIST)) {
				mv.addObject("error", 2);
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return mv;
	}

	@RequestMapping("update")
	public ModelAndView update(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User loginUser = getLoginUser(session);
		String errormsg = "error";
		try {
			userService.update(loginUser, user);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (UserException e) {
			// 用户没有权限
			if (e.getMessage().equals(UserException.USER_TYPE_NOT_AUTHORITY)) {
				errormsg = "用户没有权限！";
			} else if (e.getMessage().equals(UserException.USER_ROLE_TYPE_EMPTY)) {
				errormsg = "请选择用户角色类型！";
			}
			// 用户名已存在,安表示页面显示
			else if (UserException.USER_NAME_ISEXIST.equals(e.getMessage())) {
				errormsg = "用户名已存在！";
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errormsg);
	}

	@RequestMapping("del/{userid}")
	public ModelAndView del(@PathVariable("userid") int userid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User loginUser = getLoginUser(session);
		try {
			userService.del(loginUser, userid);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (UserException e) {
			// 用户没有权限
			if (e.getMessage().equals(UserException.USER_TYPE_NOT_AUTHORITY)) {
				mv.addObject("error", "1");
			}
			return ajaxDoneError(e.getMessage());
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
			return ajaxDoneError("error");
		}
	}

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/login");
		return mv;
	}

	/**
	 * 用户列表，授予角色
	 * 
	 * @param userid
	 * @param session
	 * @return
	 */
	@RequestMapping("grantrole")
	public ModelAndView grantRole(@RequestParam(value = "userid") int userid, HttpSession session) {
		ModelAndView mv = new ModelAndView("user/userGrantRole");
		User loginUser = getLoginUser(session);
		try {
			User user = userService.grantRole(loginUser, userid);
			mv.addObject("user", user);
			mv.addObject("roleList", systemCache.findAllRole());
		} catch (UserException e) {
			// 用户没有权限
			if (e.getMessage().equals(UserException.USER_TYPE_NOT_AUTHORITY)) {
				mv.addObject("error", "1");
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return mv;
	}

	@RequestMapping("updaterole")
	public ModelAndView updateRole(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView mv = new ModelAndView("user/userGrantRole");
		User loginUser = getLoginUser(session);
		String errorMsg = "error";
		try {
			userService.updateRole(loginUser, user);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (UserException e) {
			// 用户没有权限
			if (e.getMessage().equals(UserException.USER_TYPE_NOT_AUTHORITY)) {
				errorMsg = "用户没有权限";
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errorMsg);
	}

	/**
	 * 用户列表，授予模块权限
	 * 
	 * @param userid
	 * @param session
	 * @return
	 */
	@RequestMapping("grantmodule")
	public ModelAndView grantModule(@RequestParam(value = "userid") int userid, HttpSession session) {
		ModelAndView mv = new ModelAndView("user/userGrantModule");
		User loginUser = getLoginUser(session);
		try {
			Map<String, Object> moduleMap = userService.grantModule(loginUser, userid);
			moduleMap.put("userid", userid);
			mv.addObject("moduleMap", moduleMap);
		} catch (UserException e) {
			// 用户没有权限
			if (e.getMessage().equals(UserException.USER_TYPE_NOT_AUTHORITY)) {
				mv.addObject("error", "1");
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return mv;
	}
	@RequestMapping("saveGrantModule")
	public ModelAndView saveGrantModule(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView mv = new ModelAndView("user/userGrantModule");
		User loginUser = getLoginUser(session);
		String errormsg = "error";
		try {
			userService.saveGrantModule(loginUser, user);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (UserException e) {
			// 用户没有权限
			if (e.getMessage().equals(UserException.USER_TYPE_NOT_AUTHORITY)) {
				errormsg = "用户没有权限！";
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errormsg);
	}

}