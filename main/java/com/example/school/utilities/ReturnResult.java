package com.example.school.utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReturnResult{
	private boolean isSuccesful;
	
	private String message;
	
	private List<Serializable> data;

	public ReturnResult() {
		this.setData(new ArrayList<Serializable>());
	}
	
	public boolean isSuccesful() {
		return isSuccesful;
	}

	public void setSuccesful(boolean isSuccesful) {
		this.isSuccesful = isSuccesful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Serializable> getData() {
		return data;
	}

	public void setData(List<Serializable> data) {
		this.data = data;
	}
	
	
}
