package com.learning.userservice.model;

public class GenericAPIResponse {

	private String type;
	private String message;
	
	public GenericAPIResponse() {}
	
	
	public GenericAPIResponse(String type, String message) {
		super();
		this.type = type;
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
