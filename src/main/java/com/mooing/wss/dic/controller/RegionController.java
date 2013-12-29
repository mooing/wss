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
import com.mooing.wss.system.controller.ModuleController;

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
	
	@RequestMapping("test")
	public  ModelAndView test(HttpSession session) {
		return new ModelAndView("cert/regiontest");
	}
	@RequestMapping("findByPid")
	public @ResponseBody String findRegionByPid(@RequestParam("pcode") String pcode, HttpSession session) {
		return regionService.findRegionByPid(pcode);
	}
	@RequestMapping("findProvince")
	public @ResponseBody String findProvince( HttpSession session) {
		return regionService.findProvince();
	}
}
