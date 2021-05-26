package org.d8s.magicapi.dialect;


import org.d8s.magicapi.modules.BoundSql;

public class PostgreSQLDialect implements Dialect {
	@Override
	public boolean match(String jdbcUrl) {
		return jdbcUrl.contains(":postgresql:");
	}

	@Override
	public String getPageSql(String sql, BoundSql boundSql, long offset, long limit) {
		boundSql.addParameter(limit);
		boundSql.addParameter(offset);
		return sql + " limit ? offset ?";
	}
}
