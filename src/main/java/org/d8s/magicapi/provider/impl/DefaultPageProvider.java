package org.d8s.magicapi.provider.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.d8s.magicapi.model.Page;
import org.d8s.magicapi.provider.PageProvider;
import org.d8s.script.MagicScriptContext;

/**
 * 分页对象默认提取接口
 */
public class DefaultPageProvider implements PageProvider {

	/**
	 * page参数名
	 */
	private final String pageName;

	/**
	 * pageSize参数名
	 */
	private final String pageSize;

	/**
	 * 默认分页大小
	 */
	private long defaultPageSize = 10;

	/**
	 * 默认页数
	 */
	private long defaultPage = 1;

	public DefaultPageProvider(String pageName, String pageSize) {
		this.pageName = pageName;
		this.pageSize = pageSize;
	}

	public DefaultPageProvider(String pageName, String pageSize, long defaultPage, long defaultPageSize) {
		this.pageName = pageName;
		this.pageSize = pageSize;
		this.defaultPageSize = defaultPageSize;
		this.defaultPage = defaultPage;
	}


	@Override
	public Page getPage(MagicScriptContext context) {
		// 从Request中提取page以及pageSize
		long page = NumberUtils.toLong(context.getString(this.pageName), this.defaultPage);
		long pageSize = NumberUtils.toLong(context.getString(this.pageSize), this.defaultPageSize);
		// 计算limit以及offset
		return new Page(pageSize, (page - 1) * pageSize);

	}
}
