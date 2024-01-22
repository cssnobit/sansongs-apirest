package com.sansongs.sansongs.exception;

public class ErrorInDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ErrorInDataException(String message) {
		super(message);
	}
}
