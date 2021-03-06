package com.mooing.wss.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.common.controller.BaseController;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.system.model.Role;
import com.mooing.wss.system.model.User;
import com.mooing.wss.system.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;

	@RequestMapping("list")
	public ModelAndView findAllRole(SearchBoxModel searchBox, HttpSession session) {
		ModelAndView mv = new ModelAndView("role/roleList");
		Map<String, Object> search = new HashMap<String, Object>();
		Pagination<Role> page = roleService.pageList(searchBox, search);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 去新增角色
	 * 
	 * 系统管理员\地市\县区\乡镇
	 * 
	 * @return
	 */
	@RequestMapping("toaddrole")
	public ModelAndView toAddRole(HttpSession session) {
		ModelAndView mv = new ModelAndView("role/roleAdd");
		User loginUser = getLoginUser(session);
		try {
			mv.addObject("role", new Role());
			roleService.toAddRole(loginUser);
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

	/**
	 * 新增角色
	 * 
	 * 系统管理员\地市\县区\乡镇
	 * 
	 * @return
	 */
	@RequestMapping("addrole")
	public ModelAndView addRole(@ModelAttribute("role") Role role, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			roleService.addRole(role);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (UserException e) {
			// 角色名已存在
			if (UserException.ROLE_NAME_ISEXIST == e.getMessage()) {
				mv.addObject("error", "1");
				errormsg = "角色名已存在！";
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errormsg);
	}

	/**
	 * 去修改角色
	 * 
	 * 系统管理员\地市\县区\乡镇
	 * 
	 * @return
	 */
	@RequestMapping("toupdaterole")
	public ModelAndView toUpdateRole(@RequestParam(value = "roleid") int roleid, HttpSession session) {
		ModelAndView mv = new ModelAndView("role/roleUpdate");
		User loginUser = getLoginUser(session);
		try {
			Role role = roleService.toUpdateRole(loginUser, roleid);
			mv.addObject("role", role);
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

	/**
	 * 保存修改角色
	 * 
	 * 系统管理员\地市\县区\乡镇
	 * 
	 * @return
	 */
	@RequestMapping("updaterole")
	public ModelAndView updateRole(@ModelAttribute("role") Role role, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			roleService.updateRole(role);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (UserException e) {
			// 角色名已存在
			if (UserException.ROLE_NAME_ISEXIST == e.getMessage()) {
				mv.addObject("error", "1");
				errormsg = "角色名已存在！";
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errormsg);
	}

	/**
	 * 删除角色
	 * 
	 * @return
	 */
	@RequestMapping("delrole/{rid}")
	public ModelAndView delRole(@PathVariable(value = "rid") int rid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			User loginUser = getLoginUser(session);
			roleService.delRole(loginUser, rid);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (UserException e) {
			// 没有权限
			if (UserException.USER_TYPE_NOT_AUTHORITY == e.getMessage()) {
				mv.addObject("error", "1");
				errormsg = "当前用户类型没有操作权限！";
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errormsg);
	}

	/************ 以下是用户列表配置权限 ***************************************************/

	/**
	 * 授予模块权限
	 * 
	 * @param roleid
	 * @param session
	 * @return
	 */
	@RequestMapping("grantmodule")
	public ModelAndView grantModule(@RequestParam(value = "roleid") int roleid, HttpSession session) {
		ModelAndView mv = new ModelAndView("role/roleGrantModule");
		User loginUser = getLoginUser(session);
		try {
			Map<String, Object> moduleMap = roleService.grantModule(loginUser, roleid);
			moduleMap.put("roleid", roleid);
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
	public ModelAndView saveGrantModule(@ModelAttribute("role") Role role, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User loginUser = getLoginUser(session);
		String errormsg = "error";
		try {
			roleService.saveGrantModule(loginUser, role);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"));
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errormsg);
	}
}
