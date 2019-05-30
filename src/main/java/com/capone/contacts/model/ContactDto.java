package com.capone.contacts.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class ContactDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Name can't be empty")
	private Name name;
	@NotEmpty(message="Address can't be empty")
	private Address address;
	@NotEmpty(message="Phone can't be empty")
	private List<Phone> phone;
	@NotEmpty(message="email can't be empty")
	private String email;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Phone> getPhone() {
		return phone;
	}
	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
