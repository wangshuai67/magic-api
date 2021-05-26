package org.d8s.magicapi.provider.impl;

import org.d8s.magicapi.provider.ColumnMapperProvider;

/**
 * 全小写命名
 */
public class LowerColumnMapperProvider implements ColumnMapperProvider {

	@Override
	public String name() {
		return "lower";
	}

	@Override
	public String mapping(String columnName) {
		return columnName == null ? null : columnName.toLowerCase();
	}
}
