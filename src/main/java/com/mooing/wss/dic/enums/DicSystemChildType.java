package com.mooing.wss.dic.enums;

/**
 * 共用字典 小类
 * 
 * 
 * 
 * @author kaiming.chi
 * 
 * @date 2013-12-19 下午11:04:13
 */
public enum DicSystemChildType {

	nation(1, "民族"),
	citizen(2, "国籍");
	private int type;
	private String text;

	private DicSystemChildType(int type, String text) {
		this.type = type;
		this.text = text;
	}

	public static String getTextByType(int type) {
		for (DicSystemChildType bigType : values()) {
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
