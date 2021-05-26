package org.d8s.magicapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.d8s.magicapi.exception.InvalidArgumentException;
import org.d8s.magicapi.model.JsonBean;

public interface MagicExceptionHandler {

	Logger logger = LoggerFactory.getLogger(MagicExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	default Object exceptionHandler(Exception e) {
		logger.error("magic-api调用接口出错", e);
		return new JsonBean<>(-1, e.getMessage());
	}

	@ExceptionHandler(InvalidArgumentException.class)
	@ResponseBody
	default Object exceptionHandler(InvalidArgumentException e) {
		return new JsonBean<>(e.getCode(), e.getMessage());
	}
}
