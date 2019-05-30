package com.capone.contacts.validator;


import java.util.List;

import org.springframework.util.CollectionUtils;

import com.capone.contacts.model.Phone;

public class PhoneTypeValidator {
	
	
	public static boolean isValid(List<Phone> phones) {
		if(CollectionUtils.isEmpty(phones)) {
			return false;
		}
		//TODO create enum phone types
		for (Phone phone : phones) {
			if(!(phone.getType().contentEquals("HOME") 
					|| phone.getType().contentEquals("WORK")
					|| phone.getType().contentEquals("MOBILE"))) {
				return false;
			}
		}
		
		return true;
	}

}
