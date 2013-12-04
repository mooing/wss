package com.mooing.wss.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangzijing
 */
public class ResponseUtil {

    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);
    private static final SimpleDateFormat mFormatGMT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz", Locale.US);
    private static final ThreadLocal<SimpleDateFormat> uniqueNum = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return mFormatGMT;
        }

    };

    public static void sendMessageNoCache(HttpServletResponse response, String message) {
        response.setContentType("text/html;charset=UTF-8");
        // response.setHeader("Content-Length", message.getBytes().length + "");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        try {
            response.getWriter().print(message);
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            ioe.printStackTrace();
        }
    }

    public static void sendMessageWithCache(HttpServletResponse response, String message) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Expires", uniqueNum.get().format(new Date(System.currentTimeMillis() + 600000)));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Cache-Control", "max-age=600");
        try {
            response.getWriter().print(message);
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            ioe.printStackTrace();
        }
    }

    public static void sendJsonNoCache(HttpServletResponse response, String message) {
        response.setContentType("application/json;charset=UTF-8");
        // response.setHeader("Content-Length", message.getBytes().length + "");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        try {
            response.getWriter().print(message);
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            ioe.printStackTrace();
        }
    }

    public static void sendJsonWithCache(HttpServletResponse response, String message) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Expires", uniqueNum.get().format(new Date(System.currentTimeMillis() + 600000)));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Cache-Control", "max-age=600");
        try {
            response.getWriter().print(message);
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            ioe.printStackTrace();
        }
    }

    public static void sendXmlMessageNoCache(HttpServletResponse response, String message) {
        response.setContentType("application/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        StringBuilder sb = new StringBuilder();
        try {
            PrintWriter writer = response.getWriter();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sb.append(message);
            writer.print(sb.toString());
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            ioe.printStackTrace();
        }
    }

    public static void sendXmlMessageWithCache(HttpServletResponse response, String message) {
        response.setContentType("application/xml;charset=UTF-8");
        response.setHeader("Expires", uniqueNum.get().format(new Date(System.currentTimeMillis() + 300000)));
        response.setHeader("Cache-Control", "public");
        response.setHeader("Cache-Control", "max-age=300");
        StringBuilder sb = new StringBuilder();
        try {
            PrintWriter writer = response.getWriter();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sb.append(message);
            writer.print(sb.toString());
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            ioe.printStackTrace();
        }
    }

}
