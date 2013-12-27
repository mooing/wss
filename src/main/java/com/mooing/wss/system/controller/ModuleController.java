package com.mooing.wss.system.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.mooing.wss.common.controller.BaseController;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.common.util.CommonJson;
import com.mooing.wss.common.util.Constants;
import com.mooing.wss.system.model.Module;
import com.mooing.wss.system.model.Role;
import com.mooing.wss.system.model.User;
import com.mooing.wss.system.service.ModuleService;

/**
 * 模块管理
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-2
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {
	@Autowired
	private ModuleService moduleService;

	/**
	 * 跳转到模块list 树
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("totree")
	public ModelAndView moduleList(HttpSession session) {
		ModelAndView mv = new ModelAndView("module/moduleList");
		return mv;
	}

	@RequestMapping("bypid")
	@ResponseBody
	public String moduleTree(HttpServletRequest request, HttpSession session) {
		int pid = 0;
		String pidParam = request.getParameter("pid");
		if (!StringUtils.isBlank(pidParam)) {
			pid = Integer.parseInt(pidParam);
		}
		String jsonString = moduleService.findModuleByPid(pid);
		return jsonString;
	}

	/**
	 * 获取所有树节点
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("alltree")
	public @ResponseBody String findAllModule(HttpServletResponse response,HttpSession session) {
		String jsonString = moduleService.findAllModule();
		URLEncoder.encode(jsonString);
		return jsonString;
//		try {
//			response.getWriter().print(jsonString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 去新增角色
	 * 
	 * 系统管理员\地市\县区\乡镇
	 * 
	 * @return
	 */
	@RequestMapping("toaddmodule")
	public ModelAndView toAddRole(@RequestParam int moduleid,HttpSession session) {
		ModelAndView mv = new ModelAndView("module/moduleAdd");
		User loginUser = getLoginUser(session);
		mv.addObject("moduleid", moduleid);
		return mv;
	}

	/**
	 * 添加数节点
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("addnode")
	@ResponseBody
	public String addNode(@ModelAttribute("module") @Valid Module module, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			User loginUser = getLoginUser(session);
			moduleService.addNode(loginUser, module);
			return CommonJson.success(Constants.navTabId_MODULE);
		} catch (UserException e) {
			mv.addObject("user", new User());
			// not authority
			if (UserException.USER_TYPE_NOT_AUTHORITY.equals(e.getMessage())) {
				mv.addObject("error", "1");
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
		}
		return JSON.toJSONString("yes");
	}
	/**
	 * delete节点
	 * 
	 * @param id 节点id
	 * @param session
	 * @return
	 */
	@RequestMapping("delnode/{id}")
	@ResponseBody
	public String delNode(@PathVariable("id") int id, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			User loginUser = getLoginUser(session);
			moduleService.delNode(loginUser, id);
		} catch (UserException e) {
			mv.addObject("user", new User());
			// not authority
			if (UserException.USER_TYPE_NOT_AUTHORITY.equals(e.getMessage())) {
				mv.addObject("error", "1");
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
		}
		return JSON.toJSONString("yes");
	}
	/**
	 * delete节点
	 * 
	 * @param id 节点id
	 * @param session
	 * @return
	 */
	@RequestMapping("updatenode")
	@ResponseBody
	public String updateNode(@ModelAttribute("module") @Valid Module module, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			User loginUser = getLoginUser(session);
			moduleService.updateNode(loginUser, module);
		} catch (UserException e) {
			mv.addObject("user", new User());
			// not authority
			if (UserException.USER_TYPE_NOT_AUTHORITY.equals(e.getMessage())) {
				mv.addObject("error", "1");
			}
		} catch (Exception e) {
			mv.addObject("error", 0);
		}
		return JSON.toJSONString("yes");
	}
}
