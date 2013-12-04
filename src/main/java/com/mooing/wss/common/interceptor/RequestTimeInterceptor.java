package com.mooing.wss.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

/**
 * 打印每个controller请求时间
 * 
 * @author zhangzijing
 */
public class RequestTimeInterceptor implements HandlerInterceptor {

	public static final String AUTHORIZATION_THRU_KEY = "AUTHORIZATION_THRU_KEY";

	/**
	 * DI容器中获取Controller的实现类时元素&lt;bean&gt;的属性id使用的默认值。
	 */
	public static final String DEFAULT_AUTHORIZATION_BEAN_ID = "authorizationController";

	/**
	 * 权限检测的Controller生成失败时的错误代码。
	 */
	private static final String AUTHORIZATION_CONTROLLER_ERROR = "errors.authorization.controller";

	/**
	 * 接受访问权限控制委托的具体的Controller类。
	 */
	private static final Class AUTHORIZATION_CONTROLLER_CLASS = RequestTimeInterceptor.class;
	
    private static final Logger log = LoggerFactory.getLogger(RequestTimeInterceptor.class);

    private static final MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    

    /**
     * 超过2000毫秒的请求则输出日志
     */
    private static final long logQueryTime = 2000;
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        long startTime = threadLocal.get().longValue();
//        if (log.isInfoEnabled()) {
//            long costTime = System.currentTimeMillis() - startTime;
//            if (costTime > logQueryTime) {
//                String className = handler.getClass().getSimpleName();
//                int spi = className.indexOf("Controller");
//                if (spi != -1) {
//                    String baseStr = className.substring(0, spi);
//                    String controller = baseStr.substring(0, 1).toLowerCase() + baseStr.substring(1);
//                    String action = methodNameResolver.getHandlerMethodName(request);
//
//                    log.info("found slow request! controller: {}, action: {}, spend time: {}", new Object[] {
//                            controller, action, costTime });
//                }
//            }
//        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    /**
     * <p>
     * </p>
     * 
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        threadLocal.set(System.currentTimeMillis());

//		// 判断请求是否可直接通过Filter
//		if (req.getAttribute(AUTHORIZATION_THRU_KEY) == null) {
//
//			// 标示请求已通过Filter检测
//			req.setAttribute(AUTHORIZATION_THRU_KEY, "true");
//
//			// 判断请求是否需要进行访问权限验证
//			if (controller.isCheckRequired(req)) {
//
//				// 访问权限验证
//				if (!controller.isAuthorized(pathInfo, req)) {
//					if (log.isDebugEnabled()) {
//						log.debug("isAuthorized() failed.");
//					}
//					HttpServletRequest request = (HttpServletRequest) req;
//					HttpServletResponse response = (HttpServletResponse) res;
//
//					if (StringUtils.endsWith(pathInfo, REQ_JSON_SUFFIX)) {
//						ResponseUtil.sendJsonNoCache(response, JSON
//								.toJSONString(ImmutableMap.of("status",
//										WebConstants.RESPONSE_STATUS_FAIL,
//										"msg", "您没有该资源的访问权限!")));
//					} else {
//						request.getRequestDispatcher("/error/page403")
//								.forward(request, response);
//					}
//				}
//			}
//		}
        
        return true;
    }
}
