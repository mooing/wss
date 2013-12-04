package com.mooing.wss.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.system.model.User;

@Controller
@RequestMapping(value = "error")
public class ErrorController extends BaseController {

	@RequestMapping(value = "403")
	public ModelAndView page403(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		User loginUser = getLoginUser(session);
		return new ModelAndView("error/403", "user", loginUser);
	}

	@RequestMapping(value = "404")
	public ModelAndView page404(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		User loginUser = getLoginUser(session);
		return new ModelAndView("error/404", "user", loginUser);
	}

	@RequestMapping(value = "500")
	public ModelAndView page500(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		User loginUser = getLoginUser(session);
		return new ModelAndView("error/500", "user", loginUser);
	}

}
