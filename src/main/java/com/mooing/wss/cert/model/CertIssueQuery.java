package com.mooing.wss.cert.model;

import java.util.List;

import com.mooing.wss.dic.model.DicSystem;
import com.mooing.wss.dic.model.Region;

/**
 * 
 * 儿童出生证签发信息实体
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-18
 */
public class CertIssueQuery {
	private CertIssueInfo certIssueInfo;
	private CertIsseDetail certIsseDetail;
	private List<Region> cityList;
	private List<DicSystem> nationList;
	private List<DicSystem> citizenList;

	public CertIssueInfo getCertIssueInfo() {
		return certIssueInfo;
	}

	public void setCertIssueInfo(CertIssueInfo certIssueInfo) {
		this.certIssueInfo = certIssueInfo;
	}

	public CertIsseDetail getCertIsseDetail() {
		return certIsseDetail;
	}

	public void setCertIsseDetail(CertIsseDetail certIsseDetail) {
		this.certIsseDetail = certIsseDetail;
	}

	public List<Region> getCityList() {
		return cityList;
	}

	public void setCityList(List<Region> cityList) {
		this.cityList = cityList;
	}

	public List<DicSystem> getNationList() {
		return nationList;
	}

	public void setNationList(List<DicSystem> nationList) {
		this.nationList = nationList;
	}

	public List<DicSystem> getCitizenList() {
		return citizenList;
	}

	public void setCitizenList(List<DicSystem> citizenList) {
		this.citizenList = citizenList;
	}
}
