package com.mooing.wss.common.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * 
 * json提示
 * 
 * 
 * Title: CommonJson
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-15
 */
public class CommonJson {
	/**
	 * 成功返回结果
	 * 
	 * @return
	 */
	public static String success(String navTabId) {
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "success");
		msg.put("navTabId", navTabId);
//		msg.put("rel", "systemUser");
		msg.put("callbackType", "closeCurrent");
		msg.put("forwardUrl", "");
//		msg.put("confirmMsg", "");
		msg.put("statusCode", "200");
		msg.put("status", "1");
		return JSON.toJSONString(msg);
	}

	/**
	 * 失败提示信息
	 * 
	 * @param msg
	 *            提示信息
	 * @return
	 */
	public static String fail(String msg) {
		Map<String, Object> msgMap = Maps.newHashMap();
		msgMap.put("statusCode", "300");
		msgMap.put("message", msg);
		msgMap.put("navTabId", "");
		msgMap.put("rel", "");
		msgMap.put("callbackType", "");
		msgMap.put("forwardUrl", "");
		msgMap.put("confirmMsg", "");
		return JSON.toJSONString(msgMap);
	}
}
