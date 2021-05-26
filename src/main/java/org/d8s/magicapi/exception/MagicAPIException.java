package org.d8s.magicapi.exception;

public class MagicAPIException extends RuntimeException {

	public MagicAPIException(String message) {
		super(message);
	}

	public MagicAPIException(String message, Throwable cause) {
		super(message, cause);
	}
}
