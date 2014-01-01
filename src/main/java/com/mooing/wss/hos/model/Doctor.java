package com.mooing.wss.hos.model;

import java.sql.Date;

import com.mooing.wss.common.model.BaseModel;

/**
 * 医生实体
 * 
 * @author kaiming.chi
 * 
 */
public class Doctor extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4182283425835533653L;
	/**
	 * 用户id 对应用户表
	 */
	private int userid;
	/**
	 * 医生用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 医生姓名
	 */
	private String name;
	/**
	 * birthday
	 */
	private Date birthday;
	/**
	 * 性别 1 男；2 女
	 */
	private int sex;
	/**
	 * 医院id
	 */
	private int hospitalId;
	/**
	 * 证件类型 1.居民身份证 ，2.港澳居民身份证，3.护照，4.军官证(士兵证)
	 */
	private int cardType;
	/**
	 * 证件编号
	 */
	private String cardCode;
	/**
	 * 证件是否有效 0。无效 ，1.有效
	 */
	private int isValid;

	/**
	 * 是否医生 0:不是；1：是医生
	 */
	private int isdoctor;
	/**
	 * 是否接生人 0:不是；1：是
	 */
	private int isdeliver;
	/**
	 * 所在科室
	 */
	private String officeCode;
	/**
	 * 职称
	 */
	private String offerTitle;
	/**
	 * phone
	 */
	private String phone;
	/**
	 * email
	 */
	private String email;
	/**
	 * 即时通讯方式
	 */
	private String im;

	/**
	 * ca证书名
	 */
	private String caName;

	/**
	 * ca证书有效期
	 */
	private Date caExpire;
	/**
	 * ca证书编码
	 */
	private String caCode;

	/**
	 * 读卡器类型
	 */
	private int readcardType;

	/**
	 * 读卡器序号
	 */
	private String readcardCode;
	/**
	 * 状态 0:无效；1：有效 默认有效
	 */
	private int status;

	private String remark;

	/******* 以下页面显示使用 ******************/
	// 医院名称
	private String hospitalName;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public int getIsdoctor() {
		return isdoctor;
	}

	public void setIsdoctor(int isdoctor) {
		this.isdoctor = isdoctor;
	}

	public int getIsdeliver() {
		return isdeliver;
	}

	public void setIsdeliver(int isdeliver) {
		this.isdeliver = isdeliver;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfferTitle() {
		return offerTitle;
	}

	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCaName() {
		return caName;
	}

	public void setCaName(String caName) {
		this.caName = caName;
	}

	public Date getCaExpire() {
		return caExpire;
	}

	public void setCaExpire(Date caExpire) {
		this.caExpire = caExpire;
	}

	public String getCaCode() {
		return caCode;
	}

	public void setCaCode(String caCode) {
		this.caCode = caCode;
	}

	public int getReadcardType() {
		return readcardType;
	}

	public void setReadcardType(int readcardType) {
		this.readcardType = readcardType;
	}

	public String getReadcardCode() {
		return readcardCode;
	}

	public void setReadcardCode(String readcardCode) {
		this.readcardCode = readcardCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

}
