package org.d8s.magicapi.model;

public enum Options {

	WRAP_REQUEST_PARAMETERS("包装请求参数到一个变量中", "wrap_request_parameter"),
	PERMISSION("允许拥有该权限的访问", "permission"),
	ROLE("允许拥有该角色的访问", "role"),
	REQUIRE_LOGIN("该接口需要登录才允许访问", "require_login", "true"),
	ANONYMOUS("该接口需要不登录也可访问", "anonymous", "true");

	private final String name;
	private final String value;
	private final String defaultValue;

	Options(String name, String value) {
		this(name, value, null);
	}

	Options(String name, String value, String defaultValue) {
		this.name = name;
		this.value = value;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
}
