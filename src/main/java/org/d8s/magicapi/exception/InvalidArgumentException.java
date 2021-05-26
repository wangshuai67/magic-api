package org.d8s.magicapi.exception;

import org.d8s.magicapi.model.JsonCode;

public class InvalidArgumentException extends RuntimeException {

	private final JsonCode jsonCode;

	public InvalidArgumentException(JsonCode jsonCode) {
		super(jsonCode.getMessage());
		this.jsonCode = jsonCode;
	}

	public int getCode() {
		return jsonCode.getCode();
	}
}
