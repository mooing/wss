/*
 *  $Id: HtmlUtil.java 2462 2011-10-10 09:27:59Z zijing.zhang $
 * 
 *  Copyright (c) 2011 mooing.com. All Rights Reserved.
 */
package com.mooing.wss.common.util;

import java.util.regex.Pattern;

/**
 * @author zhangzijing
 */
public class HtmlUtil {

    public static String filterHtml(String body) {
        if (body == null) {
            return "";
        }
        body = body.replace(" ", "&nbsp;").replace("\r", "<BR/>").replace("\n", "<BR/>").replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&quot;");
        return body;
    }

    public static String filterHtmlTag(String body) {
        if (body == null) {
            return "";
        }
        body = body.replaceAll("FRAME", "");
        body = body.replaceAll("frame", "");
        body = body.replaceAll("Frame", "");
        body = body.replaceAll("APPLET", "");
        body = body.replaceAll("applet", "");
        body = body.replaceAll("Applet", "");
        body = body.replaceAll("<SCRIPT", "&lt;SCRIPT");
        body = body.replaceAll("<Script", "&lt;SCRIPT");
        body = body.replaceAll("<script", "&lt;SCRIPT");
        body = body.replaceAll("<BODY>", "&lt;body&gt;");
        body = body.replaceAll("<Body>", "&lt;body&gt;");
        body = body.replaceAll("<body>", "&lt;body&gt;");
        body = body.replaceAll("<form>", "&lt;form&gt;");
        body = body.replaceAll("<FORM>", "&lt;form&gt;");
        body = body.replaceAll("<Form>", "&lt;form&gt;");
        return body;
    }

    /**
     * 过滤 html标签 比如
     * zhangxin 20091016
     * 
     * @param htmlStr
     * @return
     */
    public static String truncateHtml(String htmlStr) {
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
                                                                                                     // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
                                                                                                  // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;

    }

    public static void main(String[] args) {
        String str = "<p>点水放松放<span>松地</span><br />方</p>";
        System.out.println(truncateHtml(str));
    }
}
