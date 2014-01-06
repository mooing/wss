package com.mooing.wss.hos.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.common.controller.BaseController;
import com.mooing.wss.common.exception.UserException;
import com.mooing.wss.hos.model.Hospital;
import com.mooing.wss.hos.service.UnitService;
import com.mooing.wss.system.model.User;

/**
 * 单位管理
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-28 下午5:23:44
 */
@Controller
@RequestMapping("unit")
public class UnitController extends BaseController {
	@Autowired
	private UnitService unitService;
	public static String navTabId = "hospitalUnit";

	/**
	 * 跳转到模块list 树
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("totree")
	public ModelAndView unitList(HttpSession session) {
		ModelAndView mv = new ModelAndView("unit/unitList");
		return mv;
	}

	/**
	 * 获取所有树节点
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("alltree")
	public @ResponseBody
	String findAllUnit(HttpServletResponse response, HttpSession session) {
		return unitService.findAllUnitTree();
	}

	/**
	 * 去新增 
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("toaddunit")
	public ModelAndView toAddUnit(@RequestParam("pid") int pid,HttpSession session) {
		ModelAndView mv = new ModelAndView("unit/unitAdd");
		User loginUser = getLoginUser(session);
		mv.addObject("pid", pid);
		try {
			unitService.toAddUnit(loginUser);
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return mv;
	}

	/**
	 * 新增单位
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("addunit")
	public ModelAndView addUnit(@ModelAttribute("hospital") Hospital hospital, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			unitService.addUnit(hospital);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"),navTabId);
		}  catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errormsg);
	}

	/**
	 * 去修改
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("toupdateunit")
	public ModelAndView toUpdateUnit(@RequestParam(value = "unitid") int unitid, HttpSession session) {
		ModelAndView mv = new ModelAndView("unit/unitUpdate");
		User loginUser = getLoginUser(session);
		try {
			Hospital hospital = unitService.toUpdateUnit(loginUser, unitid);
			mv.addObject("hospital", hospital);
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
	 * 保存修改
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("updateunit")
	public ModelAndView updateUnit(@ModelAttribute("hospital") Hospital hospital, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			unitService.updateUnit(hospital);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"),navTabId);
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return ajaxDoneError(errormsg);
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping("delunit/{unitid}")
	public ModelAndView delUnit(@PathVariable(value = "unitid") int unitid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			User loginUser = getLoginUser(session);
			unitService.delUnit(loginUser, unitid);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"),navTabId);
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
	/**
	 * 添加医生时,调用单位,跳转到选择单位页面
	 * 
	 * @return
	 */
	@RequestMapping("gounitlookup")
	public ModelAndView goUnitLookup() {
		ModelAndView mv = new ModelAndView("doctor/unitLookup");
		return mv;
	}

	/**
	 * 获取当前登录用户下所有单位
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("curunit")
	public @ResponseBody
	String findLoginUserAllUnit(HttpSession session) {
		User loginUser = getLoginUser(session);
		// 系统admin,特殊处理
		return unitService.findUnitByCode(loginUser.getRegionCode(), loginUser);
	}
	
	
}
