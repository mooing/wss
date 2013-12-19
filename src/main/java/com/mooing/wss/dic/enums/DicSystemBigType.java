package com.mooing.wss.dic.enums;

/**
 * 共用字典 大类
 * 
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-19 下午11:04:13
 */
public enum DicSystemBigType {

	common(1, "公共");
	private int type;
	private String text;

	private DicSystemBigType(int type, String text) {
		this.type = type;
		this.text = text;
	}

	public static String getTextByType(int type) {
		for (DicSystemBigType bigType : values()) {
			if (bigType.getType() == type) {
				return bigType.getText();
			}
		}
		return "";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
