package com.mooing.wss.common.spring;

import java.beans.PropertyEditorSupport;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntiSamyEditor extends PropertyEditorSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(AntiSamyEditor.class);
	private Policy policy;

	public AntiSamyEditor() {
		super();
	}

	public AntiSamyEditor(Policy policy) {
		this.policy = policy;
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return (value != null ? value.toString() : "");
	}

	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (!"".equals(text)) {
				AntiSamy as = new AntiSamy(policy);
				try {
					CleanResults cr = as.scan(text, policy);
					value = cr.getCleanHTML();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("AntiSamyEditor failure", e);
				}
			}
			setValue(value);
		}
	}

}
