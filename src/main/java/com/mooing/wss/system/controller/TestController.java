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
@RequestMapping("/test")
public class TestController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SystemCache systemCache;

	@RequestMapping("list")
	public ModelAndView findAllUser(SearchBoxModel searchBox, HttpSession session) {
		ModelAndView mv = new ModelAndView("cert/query/track");
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
	@RequestMapping("ajax")
	public ModelAndView toAjax(HttpSession session) {
		ModelAndView mv = new ModelAndView("cert/handle/_record");
		return mv;
	}

}