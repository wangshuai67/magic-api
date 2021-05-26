package org.d8s.magicapi.provider;

import org.d8s.magicapi.model.Page;
import org.d8s.script.MagicScriptContext;

/**
 * 分页对象提取接口
 */
public interface PageProvider {

	/**
	 * 从请求中获取分页对象
	 */
	public Page getPage(MagicScriptContext context);
}
