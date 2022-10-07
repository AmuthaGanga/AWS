package com.intern.aws.s3restapi.model;

import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class ObjectData {
	
	
	public ObjectData() {
		
	}
	
	public ObjectData(String etag, URL presignedURL) {
		this.etag = etag;
		this.presignedURL = presignedURL;
	}
	
	private String etag;
	private URL presignedURL;
	
	/**
	 * @return the etag
	 */
	public String getEtag() {
		return etag;
	}
	/**
	 * @param etag the etag to set
	 */
	public void setEtag(String etag) {
		this.etag = etag;
	}
	/**
	 * @return the presignedURL
	 */
	public URL getPresignedURL() {
		return presignedURL;
	}
	/**
	 * @param presignedURL the presignedURL to set
	 */
	public void setPresignedURL(URL presignedURL) {
		this.presignedURL = presignedURL;
	}

	@Override
	public String toString() {
		return "ObjectData [etag=" + etag + ", presignedURL=" + presignedURL + "]";
	}
	
}
