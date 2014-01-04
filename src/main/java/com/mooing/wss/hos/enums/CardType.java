package com.mooing.wss.hos.enums;

/**
 * 证件类型
*
*
*
* @author kaiming.chi
*
* @date 2014-1-1 下午6:10:35
 */
public enum CardType {
	ID_CARD(1, "居民身份证"),
	HK_ID_CARD(2, "港澳居民身份证"),
	PASSPORT(3, "护照"),
	OFFER(4, "军官证(士兵证)");
	
	private int type;
	private String text;

	private CardType(int type, String text) {
		this.type = type;
		this.text = text;
	}

	public static String getTextByType(int type) {
		for (CardType cardType : values()) {
			if (cardType.getType() == type) {
				return cardType.getText();
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
