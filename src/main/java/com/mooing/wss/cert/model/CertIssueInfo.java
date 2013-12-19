package com.mooing.wss.cert.model;

import java.util.Date;

/**
 * 
 * 儿童出生证签发信息实体
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-18
 */
public class CertIssueInfo {
	private int id;
	// 单位id
	private int unitId;
	// 出生证编号
	private String certCode;
	// 新出生证编号
	private String newCertCode;
	// 出生证库存id
	private int certId;
	// 儿童姓名
	private String childName;
	// 性别 1男 2女
	private int sex;
	// 出生日期
	private Date birthday;
	// 母亲姓名
	private String mName;
	// 母亲身份证
	private String mIdcard;
	// 接生人
	private String deliver;
	// 住院号
	private String admissionNumber;
	// 家庭住址省code
	private String proCode;
	// 家庭住址市code
	private String cityCode;
	// 家庭住址县code
	private String countryCode;
	// 详细地址
	private String address;
	// 联系电话
	private String phone;
	// 签发状态
	private int issueStatus;
	// 打印状态
	private int printStatus;
	// 签发日期
	private Date issueTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	public String getNewCertCode() {
		return newCertCode;
	}

	public void setNewCertCode(String newCertCode) {
		this.newCertCode = newCertCode;
	}

	public int getCertId() {
		return certId;
	}

	public void setCertId(int certId) {
		this.certId = certId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmIdcard() {
		return mIdcard;
	}

	public void setmIdcard(String mIdcard) {
		this.mIdcard = mIdcard;
	}

	public String getDeliver() {
		return deliver;
	}

	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(int issueStatus) {
		this.issueStatus = issueStatus;
	}

	public int getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(int printStatus) {
		this.printStatus = printStatus;
	}

	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
}
