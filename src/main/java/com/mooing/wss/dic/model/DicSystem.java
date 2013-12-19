package com.mooing.wss.dic.model;

import com.mooing.wss.common.model.BaseModel;

/**
 * 字典管理->系统常用字典
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-19 下午10:54:06
 */
public class DicSystem extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1719973217145823607L;
	/**
	 * 大类id
	 */
	private int bigTypeId;
	/**
	 * 大类名称
	 */
	private String bigTypeName;
	/**
	 * 小类id
	 */
	private int childTypeId;
	/**
	 * 小类名称
	 */
	private String childTypeName;

	/**
	 * 名称
	 */
	private String text;

	/**
	 * 编号 用户每个小类编号
	 */
	private int code;

	/**
	 * 备注
	 */
	private String remark;

	public int getBigTypeId() {
		return bigTypeId;
	}

	public void setBigTypeId(int bigTypeId) {
		this.bigTypeId = bigTypeId;
	}

	public String getBigTypeName() {
		return bigTypeName;
	}

	public void setBigTypeName(String bigTypeName) {
		this.bigTypeName = bigTypeName;
	}

	public int getChildTypeId() {
		return childTypeId;
	}

	public void setChildTypeId(int childTypeId) {
		this.childTypeId = childTypeId;
	}

	public String getChildTypeName() {
		return childTypeName;
	}

	public void setChildTypeName(String childTypeName) {
		this.childTypeName = childTypeName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
