package com.mooing.wss.hos.controller;

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
import com.mooing.wss.hos.model.Doctor;
import com.mooing.wss.hos.service.DoctorService;
import com.mooing.wss.system.model.User;

/**
 * 医生管理
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-29 下午11:18:10
 */
@Controller
@RequestMapping("doctor")
public class DoctorController extends BaseController {
	@Autowired
	private DoctorService doctorService;
	public static String navTabId = "hospitalUnit";

	@RequestMapping("list")
	public ModelAndView findAllDoctor(SearchBoxModel searchBox, HttpSession session) {
		ModelAndView mv = new ModelAndView("doctor/doctorList");
		Map<String, Object> search = new HashMap<String, Object>();
		Pagination<Doctor> page = doctorService.pageList(searchBox, search);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 去新增
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("toadd")
	public ModelAndView toAddRole(HttpSession session) {
		ModelAndView mv = new ModelAndView("doctor/doctorAdd");
		User loginUser = getLoginUser(session);
		try {
			Map<String ,Object> map=doctorService.toAddDoctor(loginUser);
			mv.addObject("doctorMap", map);
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
	@RequestMapping("add")
	public ModelAndView addDoctor(@ModelAttribute("doctor") Doctor doctor, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			doctorService.addDoctor(doctor);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"),navTabId);
		} catch (UserException e) {
			if (UserException.USER_NAME_ISEXIST == e.getMessage()) {
				errormsg="输入的用户名:"+doctor.getUsername()+",已经存在,请重新输入！";
			}
		} catch (Exception e) {
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
	@RequestMapping("toupdate")
	public ModelAndView toUpdateDoctor(@RequestParam(value = "doctorId") int doctorId, HttpSession session) {
		ModelAndView mv = new ModelAndView("doctor/doctorUpdate");
		User loginUser = getLoginUser(session);
		try {
			Doctor doctor = doctorService.toUpdateDoctor(loginUser, doctorId);
			mv.addObject("doctor", doctor);
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
	@RequestMapping("update")
	public ModelAndView updateDoctor(@ModelAttribute("doctor") Doctor doctor, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			doctorService.updateDoctor(doctor);
			return ajaxDialogDoneSuccess(getMessage("msg.operation.success"),navTabId);
		} catch (UserException e) {
			if (UserException.USER_NAME_ISEXIST == e.getMessage()) {
				errormsg="用户名已存在，请重新输入！";
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
	@RequestMapping("del/{doctorId}")
	public ModelAndView delDoctor(@PathVariable(value = "doctorId") int doctorId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String errormsg = "error";
		try {
			User loginUser = getLoginUser(session);
			doctorService.delDoctor(loginUser, doctorId);
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
}
