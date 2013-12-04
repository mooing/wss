package com.mooing.wss.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 付亮亮 liangliang.fu@qunar.com
 * @version $id$
 *
 */

public class Configuration {
	private Properties pros = new Properties();
	private static ConcurrentHashMap<String, Configuration> instanceMap = new ConcurrentHashMap<String, Configuration>();
	
	public static Configuration getInstance(String path){
		Configuration configuration = instanceMap.get(path);
		if(configuration == null){
			configuration = new Configuration(path);
			instanceMap.put(path, configuration);
		}
		return configuration;
	}
	
	private Configuration(String path){
		InputStream input = null;
		try{
			input = Configuration.class.getClassLoader().getResourceAsStream(path);
			pros.load(input);
		}catch(IOException e){
			throw new ExceptionInInitializerError(e);
		}finally{
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					throw new ExceptionInInitializerError(e);
				}
			}
		}
	}
	
	public String getProperty(String key){
		return pros.getProperty(key);
	}
	
	public String getProperty(String key, String defaultValue){
		return pros.getProperty(key, defaultValue);
	}
	
	public static void main(String[] args){
		Configuration config = Configuration.getInstance("component.properties");
		System.out.println(config.getProperty("admin.jdbc.url"));
		System.out.println(PropertyUtil.getProperty("admin.path"));
	}
}
