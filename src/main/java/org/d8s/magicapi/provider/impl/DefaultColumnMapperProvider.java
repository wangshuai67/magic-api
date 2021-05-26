package org.d8s.magicapi.provider.impl;

import org.d8s.magicapi.provider.ColumnMapperProvider;

/**
 * 默认命名（保持原样）
 */
public class DefaultColumnMapperProvider implements ColumnMapperProvider {

	@Override
	public String name() {
		return "default";
	}

	@Override
	public String mapping(String columnName) {
		return columnName;
	}
}
