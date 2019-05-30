package com.capone.contacts.error;

public class ContactNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContactNotFoundException(Long id) {
        super("Contact id not found : " + id);
    }

}
