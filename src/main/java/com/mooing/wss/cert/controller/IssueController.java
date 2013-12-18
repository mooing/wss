package com.mooing.wss.cert.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.mooing.wss.cert.model.CertIssue;
import com.mooing.wss.cert.service.IssueService;
import com.mooing.wss.common.controller.BaseController;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.util.Pagination;

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
public class IssueController extends BaseController {
	@Autowired
	private IssueService issueService;

	/**
	 * 待签发列表
	 * 
	 * @param searchBox
	 * @param session
	 * @return
	 */
	public ModelAndView unIssuePageList(SearchBoxModel searchBox, HttpSession session) {
		ModelAndView mv = new ModelAndView("birth/manager/unIssueList");
		Map<String, Object> search = new HashMap<String, Object>();
		Pagination<CertIssue> page = issueService.pageList(searchBox, search);
		mv.addObject("page", page);
		return mv;
	}
}
