package com.intern.aws.s3restapi.exception;

public class UnableToProcessInputFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnableToProcessInputFileException(String message) {
		super(message);
	}
	
}
