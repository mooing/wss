package com.mooing.wss.common.util;

import org.owasp.validator.html.Policy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class PolicyInstance {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyInstance.class);
	private static Policy instance = null;
	
	private PolicyInstance(){}
	
	 public static Policy getInstance(){
		if(instance == null){
			synchronized(PolicyInstance.class){
				if(instance == null){
					ClassPathResource resource = new ClassPathResource("antisamy-slashdot-1.4.4.xml");
					try {
						instance = Policy.getInstance(resource.getFile());
					} catch(Exception e) {
						e.printStackTrace();
						logger.error("policy initialize failure", e);
					} 
				}
			}
		}
		return instance;
	}
}
