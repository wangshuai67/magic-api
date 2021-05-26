package org.d8s.magicapi.interceptor;

import org.d8s.magicapi.model.RequestEntity;
import org.d8s.magicapi.modules.BoundSql;

/**
 * SQL 拦截器
 */
public interface SQLInterceptor {

	/**
	 * 1.1.1 新增，
	 */
	default void preHandle(BoundSql boundSql, RequestEntity requestEntity) {
		preHandle(boundSql);
	}


	/**
	 * @see SQLInterceptor#preHandle(BoundSql, RequestEntity)
	 */
	@Deprecated
	void preHandle(BoundSql boundSql);

}
