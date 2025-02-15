package org.ssssssss.magicapi.model;

import java.util.Objects;

public class BaseDefinition {

	/**
	 * 名
	 */
	private String name;

	/**
	 * 值
	 */
	private String value;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 是否必填
	 */
	private boolean required;

	/**
	 * 数据类型
	 */
	private DataType dataType;

	/**
	 * 类型，函数专用
	 */
	private String type;

	/**
	 * 默认值
	 */
	private String defaultValue;

	/**
	 * 验证类型
	 */
	private String validateType;

	/**
	 * 验证说明
	 */
	private String error;

	/**
	 * 验证表达式
	 */
	private String expression;

	public BaseDefinition() {
	}

	public BaseDefinition(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public DataType getDataType() {
		return dataType == null ? DataType.String : dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaseDefinition)) return false;
		BaseDefinition that = (BaseDefinition) o;
		return required == that.required && Objects.equals(name, that.name) && Objects.equals(value, that.value) && Objects.equals(description, that.description) && dataType == that.dataType && Objects.equals(defaultValue, that.defaultValue) && Objects.equals(validateType, that.validateType) && Objects.equals(error, that.error) && Objects.equals(expression, that.expression);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, value, description, required, dataType, defaultValue, validateType, error, expression);
	}
}
