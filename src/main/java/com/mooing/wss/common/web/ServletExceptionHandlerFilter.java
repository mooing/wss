package com.mooing.wss.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于ServletException的Filter。
 * Spring MVC的Handlerexceptionresolver只能处理在Controller抛出的异常，如果是在视图渲染阶段抛出的ServletException，Spring MVC就不能处理。
 * 此Filter目前用于处理这种情况
 * 
 * @author shizhi.zhu
 * @date 2013-11-6
 * 
 */
public class ServletExceptionHandlerFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(ServletExceptionHandlerFilter.class);

    /**
     * XML视图解析失败的exception message
     */
    private static final String EMPTY_MODEL_PREFIX = "Unable to locate object to be marshalled in model";
    
    /**
     * 没有找到相应的视图解析器的exception message
     */
    private static final String NOT_RESOLVE_VIEW_PREFIX = "Could not resolve view with name";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        try {
            chain.doFilter(req, resp);
        } catch (ServletException e) {

            // XML视图解析失败或没有找到相应的视图解析器
            if (e.getMessage().startsWith(EMPTY_MODEL_PREFIX) || e.getMessage().startsWith(NOT_RESOLVE_VIEW_PREFIX)) {
                logger.warn("exception message match : {} ", e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);

                // 其他情况按原来方式处理
            } else {
                throw e;
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
