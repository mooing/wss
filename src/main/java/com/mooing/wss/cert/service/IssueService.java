package com.mooing.wss.cert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mooing.wss.cert.model.CertIssueInfo;
import com.mooing.wss.cert.model.CertIssueQuery;
import com.mooing.wss.common.cache.base.DicSystemCache;
import com.mooing.wss.common.cache.base.RegionCache;
import com.mooing.wss.common.model.SearchBoxModel;
import com.mooing.wss.common.service.BaseService;
import com.mooing.wss.common.util.CommonJson;
import com.mooing.wss.common.util.Constants;
import com.mooing.wss.common.util.Pagination;
import com.mooing.wss.dic.model.DicSystem;
import com.mooing.wss.dic.model.Region;
import com.mooing.wss.system.model.User;

@Service
public class IssueService extends BaseService {
	@Autowired
	private DicSystemCache dicSystemCache;
	@Autowired
	private RegionCache regionCache;

	/**
	 * 待签发列表
	 * 
	 * @param searchBox
	 * @param search
	 * @return
	 */
	public Pagination<CertIssueInfo> unIssuePageList(SearchBoxModel searchBox, Map<String, Object> search) {
		Integer count = wssBaseDao.executeForObject("CertIssueInfo.unIssueCount", search, Integer.class);
		Pagination<CertIssueInfo> page = null;
		List<CertIssueInfo> userList = new ArrayList<CertIssueInfo>();
		if (count != null && count != 0) {
			page = new Pagination<CertIssueInfo>(count, searchBox.getPageNum(), searchBox.getNumPerPage());
			search.put("startrecord", page.getStartPosition());
			search.put("recordsize", searchBox.getNumPerPage());
			userList = wssBaseDao.executeForObjectList("CertIssueInfo.unIssueList", search);
			if (!CollectionUtils.isEmpty(userList)) {
			}
			page.bindData(userList);
		}
		return page;
	}

	/**
	 * 去首次签发
	 * 
	 * @param loginUser
	 */
	public CertIssueQuery toAdd(User loginUser) {
		// FIXME 验证权限
		// 根据当前登录用户省code，查询下级所有市地区
		List<Region> cityList = regionCache.findByPcode(loginUser.getRegionCode());
		// 获取民族
		List<DicSystem> nationList = dicSystemCache.getNation();
		List<DicSystem> citizenList = dicSystemCache.getCitizen();
		CertIssueQuery infoQuery = new CertIssueQuery();
		infoQuery.setCityList(cityList);
		infoQuery.setNationList(nationList);
		infoQuery.setCitizenList(citizenList);
		return infoQuery;
	}

	public String add(CertIssueQuery issueQuery) {
		//check cert status
		CertIssueInfo certIssueInfo = issueQuery.getCertIssueInfo();
		if(certIssueInfo==null){
			return CommonJson.fail("error");
		}
		Integer certCount=wssBaseDao.executeForObject("CertIssueInfo.checkCertCodeStatus", certIssueInfo.getIssueStatus(), Integer.class);
		if(certCount==null||certCount==0){
			return CommonJson.fail("code invalid");
		}
		//save certinfo
		wssBaseDao.execute("CertIssueInfo.saveIssueInfo", certIssueInfo);
		//save  certdetail
		wssBaseDao.execute("CertIssueInfo.saveIssueInfo", issueQuery.getCertIsseDetail());
		return CommonJson.success(Constants.navTabId_USER);
	}

}
