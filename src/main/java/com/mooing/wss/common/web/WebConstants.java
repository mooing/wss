package com.mooing.wss.common.web;

import com.mooing.wss.common.util.PropertyUtil;

/**
 * Web 常量定义
 * 
 * @author kaiming.chi
 * 
 */
public class WebConstants {

	/** web context path **/
	public static final String PATH = PropertyUtil.getProperty("admin.path");

	/** controller返回失败状态码 **/
	public static final int RESPONSE_STATUS_FAIL = 1;
	/** controller返回成功状态码 **/
	public static final int RESPONSE_STATUS_SUCC = 0;

	/** 默认分页大小 **/
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int MIDDLE_PAGE_SIZE = 50;
	public static final String DEFAULT_PAGE_SIZE_STR = "10";
	public static final String MIDDLE_PAGE_SIZE_STR = "50";
	public static final String MEDIUM_PAGE_SIZE_STR = "100";

	/** 页面js验证文件的controller **/
	public static final String JS_VALIDATOR_CONTROLLER = "JS_VALIDATOR_CONTROLLER";
	/** 页面js验证文件的action **/
	public static final String JS_VALIDATOR_ACTION = "JS_VALIDATOR_ACTION";

	/** Controller后缀 **/
	public static final String CONTROLLER_SUFFIX = "Controller";
	/** validator formId key **/
	public static final String VALI_FORM_ID_KEY = "valiFormId";
}
