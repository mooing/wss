/*
 * $Id: UrlUtil.java 2463 2011-10-10 09:28:53Z zijing.zhang $ 
 *
 */
package com.mooing.wss.common.taglib;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Url util to generate url string on HTML.
 *
 */
public final class UrlUtil {

    /**
     * Link number on one side.
     */
    public static final int SIDE_SIZE = 10;


    private UrlUtil() {
        super();
    }

    /**
     * 基于约定用controller、action和参数生成url.
     * 
     * @param path
     * @param controller
     * @param action
     * @param params
     * @return
     */
    public static String actionUrl(String controller, String action, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(controller);
        urlBuilder.append("/");
        urlBuilder.append(action);

        urlBuilder.append(UrlUtil.searchPart(params));

        return urlBuilder.toString();
    }

    /**
     * 生成请求参数字符串?key1=value1&key2=value2.
     * <p/>
     * 如value为null则此
     *
     * @param param
     * @return
     */
    public static String searchPart(Map<String, String> param) {

        if (param == null) {
            return "";
        }

        StringBuilder paramUrl = new StringBuilder();
        Map<String, String> usefulParam = Maps.filterEntries(param, new Predicate<Map.Entry<String, String>>() {

            @Override
            public boolean apply(Map.Entry<String, String> item) {
                if(item==null){
                    return false;
                }
                return !Strings.isNullOrEmpty(item.getValue());
            }
        });

        paramUrl.append("?");

        paramUrl = Joiner.on("&").withKeyValueSeparator("=").appendTo(paramUrl, usefulParam);

        return paramUrl.toString().replace("%","%25");
    }

}
