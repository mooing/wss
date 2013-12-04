/*
 *  $Id: PaginationTag.java 2463 2011-10-10 09:28:53Z zijing.zhang $.
 *
 *  Copyright (c) 2011 Qunar.com. All Rights Reserved.
 */
package com.mooing.wss.common.taglib;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;


/**
 * 分页标签.
 * 
 * @author <a href="mailto:li.su@qunar.com">Su Li</a>
 */
public class PaginationTag<E> extends SimpleTagSupport {
    private String controller;
    private String action;
    private com.mooing.wss.common.util.Pagination<E> page;
    private Map<String,String> params;


    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }


    /**
     * @param controller the controller to set
     */
    public void setController(String controller) {
        this.controller = controller;
    }


    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }


    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }


    /**
     * @return the page
     */
    public com.mooing.wss.common.util.Pagination<E> getPage() {
        return page;
    }


    /**
     * @param page the page to set
     */
    public void setPage(com.mooing.wss.common.util.Pagination<E> page) {
        this.page = page;
    }


    /**
     * @return the params
     */
    public Map<String, String> getParams() {
        return params;
    }


    /**
     * @param params the params to set
     */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }


    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().print(PaginationUtil.pageCompoent(controller, action, page, params));
    }
    
}