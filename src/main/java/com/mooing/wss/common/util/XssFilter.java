/*
 *  $Id: XssFilter.java 2494 2011-10-13 02:55:33Z zijing.zhang $
 * 
 *  Copyright (c) 2011 Qunar.com. All Rights Reserved.
 */
package com.mooing.wss.common.util;

import org.springframework.web.util.HtmlUtils;

/**
 * HTML代码转义
 * 
 * @author zhangzijing
 * @date 2011-9-19 - 下午2:21:25
 */
public class XssFilter {

    /**
     * <p>
     * 转义危险代码
     * </p>
     * 
     * @param body
     * @return
     */
    public static String escape(String body) {
        // 转义html
        body = filterHtml(body);
        // TODO

        return body;
    }

    /**
     * <p>
     * 剔除危险代码
     * </p>
     * 
     * @param body
     * @return
     */
    public static String filter(String body) {
        // TODO
        return null;
    }

    /**
     * 转义HTML代码
     * 
     * @param body
     * @return
     */
    private static String filterHtml(String value) {
        if (value == null) {
            return "";
        }

        StringBuilder result = new StringBuilder(value.length());
        for (int i = 0; i < value.length(); ++i) {
            switch (value.charAt(i)) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                case '\'':
                    result.append("&#39;");
                    break;
                case '%':
                    result.append("&#37;");
                    break;
                case ';':
                    result.append("&#59;");
                    break;
                case '(':
                    result.append("&#40;");
                    break;
                case ')':
                    result.append("&#41;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '+':
                    result.append("&#43;");
                    break;
                default:
                    result.append(value.charAt(i));
                    break;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String str = "<p>点水放松放<span>松地</span><br />方</p><script>alert(111);</script><iframe src='' ";
        System.out.println(escape(str));
        System.out.println(HtmlUtils.htmlEscape(str));
    }
}
