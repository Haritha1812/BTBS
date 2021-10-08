package com.busticketbooking.response;

import java.util.HashMap;
import java.util.Map;

public class HttpResponseStatus {

	private int statusCode;
	private String message;
	private Object data;

  private  Map<String, String> errors = new HashMap<>();
	public HttpResponseStatus(int statusCode, String message, Object data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public HttpResponseStatus(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public HttpResponseStatus(int statusCode, Map errors) {
		this.statusCode = statusCode;
		this.errors=errors;
	}
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
