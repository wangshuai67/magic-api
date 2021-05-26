package org.d8s.magicapi.config;

import org.d8s.magicapi.controller.MagicController;
import org.d8s.magicapi.exception.MagicLoginException;
import org.d8s.magicapi.interceptor.AuthorizationInterceptor;
import org.d8s.magicapi.model.Constants;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * /magic/web相关接口的拦截器
 */
public class MagicWebRequestInterceptor implements HandlerInterceptor {

	private final MagicCorsFilter magicCorsFilter;

	private AuthorizationInterceptor authorizationInterceptor;

	public MagicWebRequestInterceptor(MagicCorsFilter magicCorsFilter, AuthorizationInterceptor authorizationInterceptor) {
		this.magicCorsFilter = magicCorsFilter;
		this.authorizationInterceptor = authorizationInterceptor;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws MagicLoginException {
		HandlerMethod handlerMethod;
		if (handler instanceof HandlerMethod) {
			handlerMethod = (HandlerMethod) handler;
			handler = handlerMethod.getBean();
			if (handler instanceof MagicController) {
				if (magicCorsFilter != null) {
					magicCorsFilter.process(request, response);
				}
				Valid valid = handlerMethod.getMethodAnnotation(Valid.class);
				if (authorizationInterceptor.requireLogin() && (valid == null || valid.requireLogin())) {
					request.setAttribute(Constants.ATTRIBUTE_MAGIC_USER, authorizationInterceptor.getUserByToken(request.getHeader(Constants.MAGIC_TOKEN_HEADER)));
				}
				((MagicController) handler).doValid(request, valid);
			}
		}
		return true;
	}
}
