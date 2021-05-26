package org.d8s.magicapi.config;

import org.d8s.magicapi.interceptor.Authorization;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Valid {

	/**
	 * 验证是否有该权限
	 */
	Authorization authorization() default Authorization.NONE;

	/**
	 * 验证是否是只读模式
	 */
	boolean readonly() default true;

	/**
	 * 验证是否需要登录
	 */
	boolean requireLogin() default true;
}
