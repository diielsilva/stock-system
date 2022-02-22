package com.example.invetory_system.helpers;

import java.util.ArrayList;
import java.util.List;

public class ResponseValue {
	private String message;
	private List<?> result = new ArrayList<>();

	public ResponseValue() {

	}

	public ResponseValue(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

}
