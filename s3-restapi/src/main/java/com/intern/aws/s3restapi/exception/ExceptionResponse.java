package com.intern.aws.s3restapi.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timeStamp;
	private String status;
	private String error;
	private String message;
	private String details;
	
	public ExceptionResponse() {
	}

	public ExceptionResponse(Date timeStamp, String status, String error, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.details = details;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

}
