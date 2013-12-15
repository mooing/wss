package com.mooing.wss.hos.model;

import com.mooing.wss.common.model.NamedModel;

/**
 * 医院实体
 * 
 * 
 * Title: Hospital
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-6
 */
public class Hospital extends NamedModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6504945795507511526L;
	/**
	 * 单位编码 暂无用
	 */
	private String hosCode;
	/**
	 * 机构编码 暂无用
	 */
	private String orgCode;
	/**
	 * 所在地区编码,对应region表的code
	 */
	private String regionCode;
	/**
	 * 补充地址
	 */
	private String extAddress;
	/**
	 * 序号
	 */
	private int sort;
	/**
	 * 是否是妇幼保健院 0:不是；1 ：是
	 */
	private int isbjy;
	/**
	 * 联系人
	 */
	private String contacts;

	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 邮政编码
	 */
	private String zipcode;
	/**
	 * 单位性质 1:国企 2：私企
	 */
	private int hosProperty;

	/**
	 * 医院等级 关联字典表id
	 */
	private int level;
	/**
	 * 单位分类：可选 新筛中心 产筛中心等字典id，用逗号分隔
	 */
	private String classifyCode;
	/**
	 * 短信账号
	 */
	private String smsAccount;
	/**
	 * 短信密码
	 */
	private String smsPassword;
	/**
	 * 备注
	 */
	private String remark;

	/********* 页面显示使用 **************************/
	// 地区名
	private String regionName;

	public String getHosCode() {
		return hosCode;
	}

	public void setHosCode(String hosCode) {
		this.hosCode = hosCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getExtAddress() {
		return extAddress;
	}

	public void setExtAddress(String extAddress) {
		this.extAddress = extAddress;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getIsbjy() {
		return isbjy;
	}

	public void setIsbjy(int isbjy) {
		this.isbjy = isbjy;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getClassifyCode() {
		return classifyCode;
	}

	public void setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
	}

	public String getSmsAccount() {
		return smsAccount;
	}

	public void setSmsAccount(String smsAccount) {
		this.smsAccount = smsAccount;
	}

	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getHosProperty() {
		return hosProperty;
	}

	public void setHosProperty(int hosProperty) {
		this.hosProperty = hosProperty;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}
