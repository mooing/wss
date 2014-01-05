package com.mooing.wss.system.model;

import java.util.Date;
import java.util.List;

import com.mooing.wss.common.model.BaseModel;

/**
 * @version $id$
 * 
 */

public class User extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户锁定状态
	 */
	public static final int STATUS_LOCKED = 0;
	/**
	 * 登录名
	 */
	private String username;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 注册时间
	 */
	private Date regtime;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户类型 1：管理员 2：医生
	 */
	private Integer usertype;

	private String ip;
	/**
	 * 随机数,用户加密时使用
	 */
	private String random;

	/************** 以下供页面使用 **************/
	// 医院id
	private int hospitalId;
	// 所属地区code
	private String regionCode;
	// 医院名称
	private String hospitalName;
	// 医院地址
	private String hospitalAddress;
	/**
	 * 用户类型 1：管理员 2：医生
	 */
	private String userTypeName;
	private String userStatus;

	// 当前用户角色id list
	private List<Integer> roleIds;
	// 当前用户模块id list
	private List<Integer> moduleIds;
	// 当前 用户所有模块权限,包括角色对应的权限
	private List<String> moduleAuthList;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public List<Integer> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public List<String> getModuleAuthList() {
		return moduleAuthList;
	}

	public void setModuleAuthList(List<String> moduleAuthList) {
		this.moduleAuthList = moduleAuthList;
	}

}
