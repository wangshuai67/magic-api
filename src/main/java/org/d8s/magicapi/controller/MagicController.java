package org.d8s.magicapi.controller;

import org.apache.commons.lang3.StringUtils;
import org.d8s.magicapi.interceptor.Authorization;
import org.d8s.magicapi.interceptor.MagicUser;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.d8s.magicapi.config.MagicConfiguration;
import org.d8s.magicapi.config.Valid;
import org.d8s.magicapi.exception.InvalidArgumentException;
import org.d8s.magicapi.exception.MagicLoginException;
import org.d8s.magicapi.model.Constants;
import org.d8s.magicapi.model.JsonBean;
import org.d8s.magicapi.model.JsonCode;
import org.d8s.magicapi.model.JsonCodeConstants;

import javax.servlet.http.HttpServletRequest;

public class MagicController implements JsonCodeConstants {

	MagicConfiguration configuration;

	MagicController(MagicConfiguration configuration) {
		this.configuration = configuration;
	}

	public void doValid(HttpServletRequest request, Valid valid) {
		if (valid != null) {
			if (!valid.readonly() && configuration.getWorkspace().readonly()) {
				throw new InvalidArgumentException(IS_READ_ONLY);
			}
			if (valid.authorization() != Authorization.NONE && !allowVisit(request, valid.authorization())) {
				throw new InvalidArgumentException(PERMISSION_INVALID);
			}
		}
	}

	public void notNull(Object value, JsonCode jsonCode) {
		if (value == null) {
			throw new InvalidArgumentException(jsonCode);
		}
	}

	public void isTrue(boolean value, JsonCode jsonCode) {
		if (!value) {
			throw new InvalidArgumentException(jsonCode);
		}
	}

	public void notBlank(String value, JsonCode jsonCode) {
		isTrue(StringUtils.isNotBlank(value), jsonCode);
	}

	/**
	 * 判断是否有权限访问按钮
	 */
	boolean allowVisit(HttpServletRequest request, Authorization authorization) {
		if (authorization == null) {
			return true;
		}
		MagicUser magicUser = (MagicUser) request.getAttribute(Constants.ATTRIBUTE_MAGIC_USER);
		return configuration.getAuthorizationInterceptor().allowVisit(magicUser, request, authorization);
	}

	@ExceptionHandler(MagicLoginException.class)
	@ResponseBody
	public JsonBean<Void> invalidLogin(MagicLoginException exception) {
		return new JsonBean<>(-1, exception.getMessage());
	}
}
