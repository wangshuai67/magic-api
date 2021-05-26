package org.d8s.magicapi.provider.impl;

import org.d8s.magicapi.provider.ColumnMapperProvider;

/**
 * 全大写命名
 */
public class UpperColumnMapperProvider implements ColumnMapperProvider {

	@Override
	public String name() {
		return "upper";
	}

	@Override
	public String mapping(String columnName) {
		return columnName == null ? null : columnName.toUpperCase();
	}

}
