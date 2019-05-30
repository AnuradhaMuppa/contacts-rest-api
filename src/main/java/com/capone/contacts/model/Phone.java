package com.capone.contacts.model;

import java.io.Serializable;

public class Phone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int number;
	private String type;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}