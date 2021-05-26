package org.d8s.magicapi.dialect;

public class ClickhouseDialect extends MySQLDialect {

	@Override
	public boolean match(String jdbcUrl) {
		return jdbcUrl.contains(":clickhouse:");
	}
}
