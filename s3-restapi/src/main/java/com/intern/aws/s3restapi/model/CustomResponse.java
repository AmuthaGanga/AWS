package com.intern.aws.s3restapi.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CustomResponse {
	public CustomResponse() {
	}
	
	
	public CustomResponse(String status, String message, Date timestamp, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.data = data;
	}


	private String status;
	private String message;
	private Date timestamp;
	private Object data;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the timestamp
	 */

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}


	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "CustomResponse [status=" + status + ", message=" + message + ", timestamp=" + timestamp + ", data="
				+ data + "]";
	}
	
}
