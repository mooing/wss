package com.mooing.wss.cert.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.cert.model.CertIssueInfo;
import com.mooing.wss.cert.model.CertIssueQuery;
import com.mooing.wss.cert.service.IssueService;
import com.mooing.wss.common.controller.BaseController;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.system.model.User;

/**
 * 
 * 签发controller
 * 
 * Title: IssueController
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-18
 */
@Controller
@RequestMapping("/cert")
public class CertIssueController extends BaseController {
	@Autowired
	private IssueService issueService;

	/**
	 * 待签发列表
	 * 
	 * @param searchBox
	 * @param session
	 * @return
	 */
	@RequestMapping("unissue")
	public ModelAndView unIssuePageList(SearchBoxModel searchBox, HttpSession session) {
		ModelAndView mv = new ModelAndView("cert/manage/unIssueList");
		Map<String, Object> search = new HashMap<String, Object>();
		Pagination<CertIssueInfo> page = issueService.unIssuePageList(searchBox, search);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 去首次签发
	 * 
	 * @return
	 */
	@RequestMapping("toadd")
	public ModelAndView toAdd(HttpSession session) {
		ModelAndView mv = new ModelAndView("cert/manage/firstIssue");
		try {
			User loginUser = getLoginUser(session);
			CertIssueQuery issueQuery = issueService.toAdd(loginUser);
			mv.addObject("issueQuery", issueQuery);
		} catch (Exception e) {
			mv.addObject("error", 0);
			log.error(e.getMessage(), e);
		}
		return mv;
	}

	/**
	 * 首次签发保存
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("add")
	public @ResponseBody
	String add(@ModelAttribute("issueQuery") CertIssueQuery issueQuery, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		return "";
	}
}
