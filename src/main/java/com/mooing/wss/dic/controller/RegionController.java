package com.mooing.wss.dic.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.common.controller.BaseController;
import com.mooing.wss.dic.service.RegionService;
import com.mooing.wss.system.model.User;

/**
 * 地区管理
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-29 下午6:27:28
 */
@Controller
@RequestMapping("region")
public class RegionController extends BaseController {

	@Autowired
	private RegionService regionService;

	@RequestMapping("findByPid")
	public @ResponseBody
	String findRegionByPid(@RequestParam("pcode") String pcode, HttpSession session) {
		return regionService.findRegionByPid(pcode);
	}

	@RequestMapping("findProvince")
	public @ResponseBody
	String findProvince(HttpSession session) {
		return regionService.findProvince();
	}

	/**
	 * 添加单位时,调用地区,跳转到选择地区页面
	 * 
	 * @return
	 */
	@RequestMapping("goregionlookup")
	public ModelAndView goRegionLookup() {
		ModelAndView mv = new ModelAndView("unit/regionLookup");
		return mv;
	}

	/**
	 * 获取当前登录用户下所有地区
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("curregion")
	public @ResponseBody
	String findLoginUserAllRegion(HttpSession session) {
		User loginUser = getLoginUser(session);
		// 系统admin,特殊处理
		return regionService.findRegionByCode(loginUser.getRegionCode(), loginUser);
	}

}
