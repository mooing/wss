package com.mooing.wss.common.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @version $id$ 所有模型的基类，提供的ID，可序列化接口，方便查看信息的toString方法
 */

public class BaseModel implements Serializable {
	private static final long serialVersionUID = 7896330288942779458L;
	/**
	 * 模型的id，对应数据库的主键
	 */
	protected int id;
	/**
	 * 状态:0:不可用；1：可用 默认可用; DEFAULT '0'
	 */
	protected int status;

	public BaseModel() {
	}

	public BaseModel(Integer id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
