package com.mooing.wss.common.util;

public class CommonProperties {
	public static String getProperty(String key) {
		return Configuration.getInstance("common.properties").getProperty(key);
	}
}
