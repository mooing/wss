package com.mooing.wss.common.web;

import com.mooing.wss.common.util.PropertyUtil;

/**
 * cms签署地址
 * @author andy
 *
 */
public class CmsPath {
	
	//cms签署（确认）地址，要记得自己组装参数
	public static final String CMS_PRODUCT_SIGN_URL = PropertyUtil.getProperty("cms.sign.path");

	//读取pdf
	public static final String CMS_PRODUCT_PDF_URL = PropertyUtil.getProperty("cms.download.path");

	
	
}
