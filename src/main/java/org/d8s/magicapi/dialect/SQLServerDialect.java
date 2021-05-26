package org.d8s.magicapi.dialect;


import org.d8s.magicapi.modules.BoundSql;

public class SQLServerDialect implements Dialect {
	@Override
	public boolean match(String jdbcUrl) {
		return jdbcUrl.contains(":sqlserver2012:");
	}

	@Override
	public String getPageSql(String sql, BoundSql boundSql, long offset, long limit) {
		boundSql.addParameter(offset);
		boundSql.addParameter(limit);
		return sql + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	}
}
